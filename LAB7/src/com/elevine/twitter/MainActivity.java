package com.elevine.twitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {
    private static final String TWITTER_URL = "http://api.twitter.com/1/statuses/public_timeline.json"; 
    private static final String LOCAL = "http://..../Android/twitter.txt";
    private static final String TAG = "MainActivity";

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
	        
	        Log.d(TAG, builder.toString());
	        TextView tv = (TextView)findViewById(R.id.textview);
	        tv.setText(builder.toString());
	        
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
}