package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest {

    @Test
    public void testAsyncTaskRequestReturnsJoke() throws InterruptedException {

        final CountDownLatch signal = new CountDownLatch(1);

        new EndpointsAsyncTask().execute((EndpointsAsyncTask.CompletionListener) result -> {
            signal.countDown();
            assertNotNull(result);
            assertFalse(result.isEmpty());
        });

        signal.await();
    }
}