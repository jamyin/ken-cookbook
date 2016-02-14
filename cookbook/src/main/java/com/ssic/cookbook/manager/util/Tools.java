/**
 * 
 */
package com.ssic.cookbook.manager.util;

/**
 * @author wk.s
 *
 */
public class Tools {

	/**
	 * 切割字符串
	 * @param str
	 * @param regex
	 * @return
	 */
	public static String[] toStrArr(String str, String regex){
		
		str = str.substring(0, str.length()-1);
		String[] strArr = null;
		strArr = str.split(regex);
		return strArr;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
