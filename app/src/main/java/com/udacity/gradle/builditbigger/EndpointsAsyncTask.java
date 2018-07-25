package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.util.Log;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<EndpointsAsyncTask.JokeLoadedCallback, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private JokeLoadedCallback mJokeLoadedCallback;

    public EndpointsAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(JokeLoadedCallback... jokeLoadedCallbacks) {
        if(jokeLoadedCallbacks.length > 0)
            mJokeLoadedCallback = jokeLoadedCallbacks[0];

        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://192.168.1.12:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            return myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            Log.w(getClass().getSimpleName(), "Failed to load joke: " + e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if(MainActivity.mToast != null)
            MainActivity.mToast.cancel();
        MainActivity.mToast = Toast.makeText(context, result, Toast.LENGTH_LONG);
        MainActivity.mToast.show();

        if(mJokeLoadedCallback != null)
            mJokeLoadedCallback.loaded(result);
    }

    public interface JokeLoadedCallback{
        void loaded(String result);
    }
}