package com.example.quickindex.adapter;

import java.util.List;

import com.example.quickindex.R;
import com.example.quickindex.bean.HaohanPeople;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HaohanAdapter extends BaseAdapter {

	private List<HaohanPeople> list;
	private Context context;
	
	public HaohanAdapter(Context context, List<HaohanPeople> list) {
		this.list = list;
		this.context = context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = convertView;
		if(convertView == null){
			view = view.inflate(context, R.layout.item_list, null);
		}
		
		ViewHolder mViewHolder = ViewHolder.getViewHolder(view);
		
		HaohanPeople p = list.get(position);
		String str = null;
		String currentLetter = p.getPinyin().charAt(0)+ "";
		
		//根据上一个首字母决定当前字母是否显示字母
		if(position == 0){
			str = currentLetter;
		}else {
			//上一个人的拼音的首字母
			String preLetter = list.get(position - 1).getPinyin().charAt(0)+"";
			if(!TextUtils.equals(preLetter, currentLetter)){
				str = currentLetter;
			}
		}
		
		//根据str是否为空，决定是否显示索引栏 
		mViewHolder.mIndex.setVisibility(str == null?View.GONE:View.VISIBLE);
		mViewHolder.mIndex.setText(currentLetter);
		mViewHolder.mName.setText(p.getName());
		
		
		return view;
	}
	
	static class ViewHolder{
		TextView mIndex;
		TextView mName;
		
		public static ViewHolder getViewHolder(View view){
			Object tag = view.getTag();
			if(tag != null ){
				return (ViewHolder) tag;
			}else {
				ViewHolder viewHolder = new ViewHolder();
				viewHolder.mIndex = (TextView) view.findViewById(R.id.head);
				viewHolder.mName = (TextView) view.findViewById(R.id.name);
				return viewHolder;
			}
			
		}
		
		
		
	}

}
