/**
 * 
 */
package com.ssic.cookbook.manager.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wk.s
 *
 */
public class CookVo {
	
	@Getter
	@Setter
	private String rid; // 配菜结果集id
	
	@Getter
	@Setter
	private Integer lunchCount; // 午餐数量
	
	@Getter
	@Setter
	private Integer dinnerCount; // 晚餐数量

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
