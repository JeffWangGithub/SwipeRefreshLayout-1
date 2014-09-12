package com.miku.Test;

import java.util.ArrayList;
import java.util.List;

import com.miku.framelite.widget.R;
import com.miku.framelite.widget.SwipeRefreshLayout;
import com.miku.framelite.widget.R.id;
import com.miku.framelite.widget.R.layout;
import com.miku.framelite.widget.R.menu;
import com.miku.framelite.widget.SwipeRefreshLayout.OnRefreshListener;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewTestActivity extends Activity {
	private static final String TAG = ListViewTestActivity.class.getSimpleName();
	SwipeRefreshLayout swipeLayout;
	ListView listview;
	PrivateAdapter mPrivateAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_test);
		swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
		listview = (ListView) findViewById(R.id.listview);
		mPrivateAdapter = new PrivateAdapter();
		listview.setAdapter(mPrivateAdapter);

//		swipeLayout.setSwipeMode(SwipeRefreshLayout.MODE_SWIPE_DOWN);
		swipeLayout.setColorScheme(Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW);
		swipeLayout.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				Log.e(TAG, "onSwipeDownRefresh REFRESH ");
				
				
				new AsyncTask<Void, Integer, Void>() {

					@Override
					protected Void doInBackground(Void... params) {
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return null;
					}

					@Override
					protected void onPostExecute(Void result) {
						super.onPostExecute(result);
						swipeLayout.setRefreshing(false);
						List<String> list = getMoreData(5);
						if(swipeLayout.getSwipeMode()==SwipeRefreshLayout.MODE_SWIPE_DOWN){
							mData.addAll(0,list);
						}else{
							mData.addAll(list);						}
						mPrivateAdapter.notifyDataSetChanged();
					}
					
					
				}.execute();

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	int current = 0;

	List<String> getMoreData(int size) {
		List<String> sList = new ArrayList<String>();
		for (int i = 0; i < size; i++) {
			sList.add(String.valueOf(current++));
		}
		return sList;
	}

	List<String> mData = new ArrayList<String>();

	class PrivateAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mData.size();
		}

		@Override
		public Object getItem(int position) {
			return mData.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView tv = new TextView(ListViewTestActivity.this);
			tv.setTextColor(Color.BLACK);
			tv.setTextSize(20);
			tv.setText(mData.get(position));
			return tv;
		}

	}

}
