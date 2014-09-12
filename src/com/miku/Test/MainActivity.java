package com.miku.Test;

import java.util.ArrayList;
import java.util.List;

import com.miku.framelite.widget.R;
import com.miku.framelite.widget.R.id;
import com.miku.framelite.widget.R.layout;
import com.miku.framelite.widget.R.menu;
import com.miku.framelite.widget.SwipeRefreshLayout.OnRefreshListener;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final String TAG = MainActivity.class.getSimpleName();
	private ListView listview;
	private PrivateAdapter mPrivateAdapter;
	private static List<Class< ? extends Activity>> mData;
	static{
		mData = new ArrayList<Class< ? extends Activity>>();
		mData.add(ListViewTestActivity.class);
		mData.add(ScollViewTestActivity.class);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listview = (ListView) findViewById(R.id.listview);
		mPrivateAdapter = new PrivateAdapter();
		listview.setAdapter(mPrivateAdapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent it=new Intent(MainActivity.this,mData.get(position));
				startActivity(it);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	

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
			TextView tv = new TextView(MainActivity.this);
			tv.setTextColor(Color.BLACK);
			tv.setTextSize(30);
			tv.setText(mData.get(position).getSimpleName());
			return tv;
		}

	}

}
