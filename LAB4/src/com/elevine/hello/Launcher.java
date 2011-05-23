package com.elevine.hello;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Launcher extends Activity {
	private static final String TAG = "Launcher";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.launcher);
		Log.d(TAG, "onCreate");
		
	}
}
