package com.example.quickindex.bean;

import com.example.quickindex.util.PinyinUtils;

public class HaohanPeople implements Comparable<HaohanPeople>{
	
	private String name;
	private String pinyin;
	public HaohanPeople(String name) {
		this.name = name;
		this.pinyin = PinyinUtils.getPinYin(name);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	@Override
	public int compareTo(HaohanPeople arg0) {
		return this.pinyin.compareTo(arg0.pinyin);
	}
	
}
