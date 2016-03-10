package com.example.quickindex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.quickindex.adapter.HaohanAdapter;
import com.example.quickindex.bean.HaohanPeople;
import com.example.quickindex.ui.QuickIndexBar;
import com.example.quickindex.ui.QuickIndexBar.OnLetterUpdateListener;
import com.example.quickindex.util.Cheeses;
import com.example.quickindex.util.Utils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private ListView mListView;
    private List<HaohanPeople> list;
    private TextView mCenter;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QuickIndexBar mQuickBar = (QuickIndexBar) findViewById(R.id.qib);
        mQuickBar.setOnLetterUpdateListener(new OnLetterUpdateListener() {
			
			@Override
			public void letterUpdate(String letter) {
//				Utils.showToast(MainActivity.this, letter+"");
				
				showLetter(letter);
				
				for (int i = 0; i < list.size(); i++) {
					HaohanPeople p = list.get(i);
					String l = p.getPinyin().charAt(0) + "";
					if(TextUtils.equals(letter, l)){
						mListView.setSelection(i);
						break;
					}
				}
			}
		});
        mListView = (ListView) findViewById(R.id.lv_name);
        
        
        list = new ArrayList<HaohanPeople>();
        //填充数据然后排序
        fillAndSortData(list);
        HaohanAdapter adapter = new HaohanAdapter(MainActivity.this, list);
        mListView.setAdapter(adapter);
        
        mCenter = (TextView) findViewById(R.id.tv_center);
    }
	
	private Handler mHandler = new Handler();

	protected void showLetter(String letter) {
		mCenter.setVisibility(View.VISIBLE);
		mCenter.setText(letter);
		
		mHandler.removeCallbacksAndMessages(null);
		mHandler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				mCenter.setVisibility(View.GONE);
			}
		}, 1000);
	}

	private void fillAndSortData(List<HaohanPeople> list2) {
		//填充数据
		for (int i = 0; i < Cheeses.NAMES.length; i++) {
			String name = Cheeses.NAMES[i];
			list.add(new HaohanPeople(name));
		}
		Collections.sort(list);
	}
}
