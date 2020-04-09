package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.krinotech.jokeandroidlib.MainJokeActivity;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyApi myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver
            Log.d("AsyncTask", "doInBackground: ");
            myApiService = builder.build();
            Log.d("AsyncTask", "doInBackground: Complete");
        }

        context = params[0].first;
        String name = params[0].second;

        Log.d("Async", myApiService.toString());

        try {
            Log.d("Async", "doInBackground: TRY");
            return myApiService.getJoker().execute().getRandomJoke();
        } catch (IOException e) {
            Log.d("Async", "doInBackground: IOEXCEPTION");
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        launchJokeActivity(result);
    }

    public void launchJokeActivity(String joke) {
        Intent intent = new Intent(context, MainJokeActivity.class);
        intent.putExtra(MainJokeActivity.EXTRA_JOKE, joke);

        context.startActivity(intent);
    }
}
