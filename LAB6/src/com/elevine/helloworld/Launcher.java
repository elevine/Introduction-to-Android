package com.elevine.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Launcher extends Activity {
	private static final String TAG = "Launcher Activity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.launcher);
        Button button = (Button)findViewById(R.id.hello1_button);
        
        button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), Globe.class));
			}
		});
        
        button = (Button)findViewById(R.id.hello2_button);
        button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), Hello.class));
				
			}
		});
        
        button = (Button)findViewById(R.id.hello3_button);
        button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://developer.android.com")));
				
			}
		});
        
        
	}
}
