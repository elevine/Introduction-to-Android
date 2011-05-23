package com.elevine.twitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

public class MainActivity extends Activity {
	private static final String TWITTER_URL = "http://api.twitter.com/1/statuses/public_timeline.json";
	private static final String LOCAL = "http://..../Android/twitter.txt";
	private static final String TAG = "MainActivity";
	private static final String TWEET_KEY = "text";
	
	private ProgressDialog pd = null;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		new FetchTimelineTask().execute();
	}
	
	private class FetchTimelineTask extends AsyncTask<Void, Integer, Void> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			pd = ProgressDialog.show(MainActivity.this, "Getting Tweets",
					"Fetching timeline!", false);
		}

		@Override
		protected Void doInBackground(Void... params) {
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(LOCAL);
			HttpResponse response;
			try {
				response = client.execute(request);
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(response.getEntity().getContent()));

				StringBuilder responseBody = new StringBuilder();
				String nextLine = null;

				while ((nextLine = reader.readLine()) != null) {
					responseBody.append(nextLine);

				}
				JSONArray jsonResponse = new JSONArray(responseBody.toString());

				for (int index = 0; index < jsonResponse.length(); index++) {
					try {
						get(100, TimeUnit.MILLISECONDS);
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (TimeoutException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					publishProgress(index, jsonResponse.length());
				}

			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			pd.dismiss();
			pd = null;

		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			if (values[0] == 0) {
				pd.hide();
				pd = new ProgressDialog(MainActivity.this);
				pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				pd.setMessage("Processing tweets");
				pd.setCancelable(false);
				pd.setMax(values[1]);
				pd.show();
			}

			pd.setProgress(values[0]);

		}

	}

}