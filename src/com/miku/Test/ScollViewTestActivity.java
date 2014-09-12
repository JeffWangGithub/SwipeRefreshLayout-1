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
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScollViewTestActivity extends Activity {
	private static final String TAG = ScollViewTestActivity.class.getSimpleName();
	SwipeRefreshLayout swipeLayout;
	LinearLayout mContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scollview_test);
		swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
		mContent = (LinearLayout) findViewById(R.id.content);

		// swipeLayout.setSwipeMode(SwipeRefreshLayout.MODE_SWIPE_DOWN);
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
						List<String> list = getMoreData(5);
						for (int i = 0; i < list.size(); i++) {
							TextView tv = new TextView(ScollViewTestActivity.this);
							tv.setTextColor(Color.BLACK);
							tv.setTextSize(20);
							tv.setText(list.get(i));
							mContent.addView(tv);
						}
						swipeLayout.setRefreshing(false);
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

}
