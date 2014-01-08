package com.mike.actionbar.demo;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.ShareActionProvider;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends Activity {

	private Context mContext;
	private ShareActionProvider mShareActionProvider;
	private ActionBar mActionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		mContext = this;
		
		mActionBar = getActionBar();
		mActionBar.setDisplayHomeAsUpEnabled(true);
		
		
//		initActionBarTabs();
		
		initSpinnerList();

		// initFragmentTabs();
	}


	private void initSpinnerList() {
		// init actionbar spinner list
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.action_list,
		          android.R.layout.simple_spinner_dropdown_item);
		
		mActionBar.setListNavigationCallbacks(mSpinnerAdapter, mNavigationCallback);
	}
	
	
	private OnNavigationListener mNavigationCallback  = new OnNavigationListener() {
		
		@Override
		public boolean onNavigationItemSelected(int itemPosition, long itemId) {
			String[] strings = mContext.getResources().getStringArray(R.array.action_list);
			Toast.makeText(mContext, strings[itemPosition], Toast.LENGTH_SHORT).show();
			
			// Create new fragment from our own Fragment class
		    ListContentFragment newFragment = new ListContentFragment();
		    FragmentTransaction ft = openFragmentTransaction();
		    // Replace whatever is in the fragment container with this fragment
		    //  and give the fragment a tag name equal to the string at the position selected
		    ft.replace(R.id.fragment_container, newFragment, strings[itemPosition]);
		    // Apply changes
		    ft.commit();
		    return true;
		}
	};
	
	private FragmentTransaction openFragmentTransaction () {
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		return fragmentTransaction;
	}
	
	
	
	private void initActionBarTabs() {
		// init actionbar tabs
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	    mActionBar.setDisplayShowTitleEnabled(false);

		Tab tab = mActionBar
				.newTab()
				.setText(R.string.artist)
				.setTabListener(
						new MyTabListener<ArtistFragment>(this, "artist",
								ArtistFragment.class));
		mActionBar.addTab(tab);

		tab = mActionBar
				.newTab()
				.setText(R.string.album)
				.setTabListener(
						new MyTabListener<AlbumFragment>(this, "album",
								AlbumFragment.class));
		mActionBar.addTab(tab);
	}

	private void initFragmentTabs() {
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();

		ArtistFragment fragment1 = new ArtistFragment();
		fragmentTransaction.add(R.id.fragment_container, fragment1);
		fragmentTransaction.commit();

		AlbumFragment fragment2 = new AlbumFragment();
		fragmentTransaction.add(R.id.fragment_container, fragment2);
		fragmentTransaction.commit();
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
		mShareActionProvider = (ShareActionProvider) shareItem
				.getActionProvider();
		mShareActionProvider.setShareIntent(getDefaultIntent());
		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * Defines a default (dummy) share intent to initialize the action provider.
	 * However, as soon as the actual content to be used in the intent is known
	 * or changes, you must update the share intent by again calling
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
