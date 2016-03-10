package com.example.quickindex.util;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;


public class PinyinUtils {

	/*
	 * 根据传入的字符串(包含汉字),得到拼音
	 * 黑马 -> HEIMA
	 * 黑  马*& -> HEIMA
	 * 黑马f5 -> HEIMA
	 * @param str 字符串
	 */
	
	public static String getPinYin(String string) {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		
		StringBuilder sb = new StringBuilder();
		char[] charArray = string.toCharArray();
		for (int i = 0; i<charArray.length; i++){
			char c = charArray[i];
			if(Character.isWhitespace(c)){
				continue;
			}
			if(c>=-127 &&c<=128){
				//肯定不是汉字
				sb.append(c);
			} else {
				String s = "";
				//通过char得到拼音集合 单 ->dan,shan
				try {
					s = PinyinHelper.toHanyuPinyinStringArray(c,format)[0];
					sb.append(s);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

}
