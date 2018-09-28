package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.shradha.jokeactivity.JokeActivity;


public class MainActivity extends AppCompatActivity {

    private EndpointsAsyncTask.CompletionListener requestCompletionListener;
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                tellJoke();
            }
        });

        findViewById(R.id.bt_tell_joke).setOnClickListener(v -> showAdAndTellJoke());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        requestCompletionListener = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showAdAndTellJoke() {
        if (interstitialAd.isLoaded()) interstitialAd.show();
        else tellJoke();
    }

    private void tellJoke() {
        requestCompletionListener = result -> {
            //Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
            startActivity(JokeActivity.createIntent(MainActivity.this, result));
        };

        new EndpointsAsyncTask().execute(requestCompletionListener);
    }
}
