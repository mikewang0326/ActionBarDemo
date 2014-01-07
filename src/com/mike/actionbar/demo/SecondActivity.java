package com.mike.actionbar.demo;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends Activity{
	
    private Context mContext;
	private ShareActionProvider mShareActionProvider;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		mContext = this;
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_activity_actions, menu);
	    MenuItem searchItem = menu.findItem(R.id.action_search);
	    
	    SearchView searchView = (SearchView) searchItem.getActionView();
	    
	    Log.d("tt", "searchView =  " + searchView);
	    searchView.setBackgroundColor(0xffff0000);
	    searchView.setIconifiedByDefault(true);
	    TextView textView = new TextView(this);
	    textView.setText("this is search layout");
	    searchView.addView(textView);
	    
	    
	 // Set up ShareActionProvider's default share intent
	    MenuItem shareItem = menu.findItem(R.id.action_share);
	    mShareActionProvider = (ShareActionProvider)
	    		shareItem.getActionProvider();
	    mShareActionProvider.setShareIntent(getDefaultIntent());
	    return super.onCreateOptionsMenu(menu);
	}
	
	/** Defines a default (dummy) share intent to initialize the action provider.
	  * However, as soon as the actual content to be used in the intent
	  * is known or changes, you must update the share intent by again calling
	  * mShareActionProvider.setShareIntent()
	  */
	private Intent getDefaultIntent() {
	    Intent intent = new Intent(Intent.ACTION_SEND);
	    intent.setType("image/*");
	    return intent;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		 switch (item.getItemId()) {
	        case R.id.action_search:
	            openSearch();
	            return true;
	        case R.id.action_compose:
	            composeMessage();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	

	private void openSearch() {
		Toast.makeText(mContext, "openSearch()", Toast.LENGTH_SHORT).show();
	}
	
	private void composeMessage() {
		Toast.makeText(mContext, "composeMessage()", Toast.LENGTH_SHORT).show();
	}
	
}
