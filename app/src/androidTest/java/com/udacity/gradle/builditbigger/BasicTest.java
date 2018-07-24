package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class BasicTest extends ApplicationTestCase<Application> implements EndpointsAsyncTask.JokeLoadedCallback {

    CountDownLatch signal;
    String result;

    public BasicTest() {
        super(Application.class);
    }

    @Test
    public void checkIfJokeIsNotNull() {
        try {
            signal = new CountDownLatch(1);

            new EndpointsAsyncTask(getContext()).execute();

            signal.await(20, TimeUnit.SECONDS);

            assertNotNull("received joke is null", result);
            assertFalse("received joke is empty", result.isEmpty());
        } catch (Exception e) {
            fail();
        }
    }

    @Override
    public void loaded(String result) {
        this.result = result;
        signal.countDown();
    }
}