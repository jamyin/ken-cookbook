/**
 * 
 */
package com.ssic.cookbook.manager.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wk.s
 * <br>时间处理工具类
 */
public class DateToolKit {

	static Logger log = LoggerFactory.getLogger(DateToolKit.class);
	
	/**
	 * 将字符串格式时间戳格式化为Date数据
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	static public Date parseStr(String dateStr, String pattern){
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			date = sdf.parse(dateStr);
		} catch (Exception e) {
			log.error("字符串转时间发生异常", e);
		}
		return date;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Date date = parseStr("2015-10-12 09:56:10", "yyyy-MM-dd");
		System.out.println(date);
	}

}
