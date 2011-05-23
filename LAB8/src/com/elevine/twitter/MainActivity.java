package com.elevine.twitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {
    private static final String TWITTER_URL = "http://api.twitter.com/1/statuses/public_timeline.json"; 
    private static final String LOCAL = "http://..../Android/twitter.txt";
    private static final String TAG = "MainActivity";
    private static final String TWEET_KEY = "text";
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(LOCAL);
        HttpResponse response;
		try {
			response = client.execute(request);
	        
			BufferedReader reader = new BufferedReader(new InputStreamReader (response.getEntity().getContent()));
	        String line= null;
	        StringBuilder builder = new StringBuilder();
	        while( (line = reader.readLine()) != null){
	        	builder.append(line); 
	        }
	        JSONArray json = new JSONArray(builder.toString());
	        for(int i= 0; i < json.length(); i++){
	        	Log.d(TAG, json.getJSONObject(i).get(TWEET_KEY).toString());
	        }
	        
	        Log.d(TAG, json.toString());
	        TextView tv = (TextView)findViewById(R.id.textview);
	        tv.setText(json.toString());
	        
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
        
    }
}