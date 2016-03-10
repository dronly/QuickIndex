package com.example.quickindex.util;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;


public class PinyinUtils {

	/*
	 * ���ݴ�����ַ���(��������),�õ�ƴ��
	 * ���� -> HEIMA
	 * ��  ��*& -> HEIMA
	 * ����f5 -> HEIMA
	 * @param str �ַ���
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
				//�϶����Ǻ���
				sb.append(c);
			} else {
				String s = "";
				//ͨ��char�õ�ƴ������ �� ->dan,shan
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
