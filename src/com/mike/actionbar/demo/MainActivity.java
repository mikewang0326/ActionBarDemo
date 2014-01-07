package com.mike.actionbar.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		View view = findViewById(R.id.to_second);
		view.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
       Intent intent = new Intent();	
       intent.setClass(this, SecondActivity.class);
       startActivity(intent);
	}

	
}
