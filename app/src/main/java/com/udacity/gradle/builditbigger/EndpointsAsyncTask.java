package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;
import java.lang.ref.WeakReference;

public class EndpointsAsyncTask extends AsyncTask<EndpointsAsyncTask.CompletionListener, Void, String> {
    private static MyApi myApiService = null;
    private WeakReference<CompletionListener> listenerReference;

    @Override
    protected final String doInBackground(CompletionListener... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://192.168.0.100:8077/_ah/api/")
                    .setGoogleClientRequestInitializer(abstractGoogleClientRequest -> abstractGoogleClientRequest.setDisableGZipContent(true));
            // end options for devappserver

            myApiService = builder.build();
        }

        listenerReference = new WeakReference<>(params[0]);

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        CompletionListener listener = listenerReference.get();
        if (listener != null) listener.onComplete(result);
    }

    interface CompletionListener {
        void onComplete(String result);
    }
}
