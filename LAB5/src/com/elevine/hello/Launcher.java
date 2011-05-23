package com.elevine.hello;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Launcher extends Activity {
	private static final String TAG = "Launcher";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.launcher);
		Log.d(TAG, "onCreate");
		
		
	}
	
	public void onButton1Click(View target){
		Toast.makeText(getApplicationContext(), "Hello World",
				Toast.LENGTH_LONG).show();
	}
	
	public void onButton2Click(View v){
		Toast.makeText(getApplicationContext(), "Hello World 2",
				Toast.LENGTH_LONG).show();
	}
	
}
