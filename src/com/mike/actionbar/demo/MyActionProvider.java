package com.mike.actionbar.demo;

import android.content.Context;
import android.util.Log;
import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MyActionProvider extends ActionProvider{
	private Context mContext;

	public MyActionProvider(Context context) {
		super(context);
		mContext = context;
	}

	@Override
	public View onCreateActionView() {
		 // Inflate the action view to be shown on the action bar.
	    LayoutInflater layoutInflater = LayoutInflater.from(mContext);
	    View view = layoutInflater.inflate(R.layout.action_provider, null);
	    Button button = (Button) view.findViewById(R.id.button);
	    button.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	            // Do something...
	        	Toast.makeText(mContext, "MyActionProvider button onClick()", Toast.LENGTH_SHORT).show();
	        }
	    });
	    return view;
	}

}
