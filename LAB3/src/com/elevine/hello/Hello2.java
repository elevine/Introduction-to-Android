package com.elevine.hello;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Hello2 extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView tv = new TextView(this);
		tv.setText(R.string.helloName);
		
		setContentView(tv);
		
		
	}
}
