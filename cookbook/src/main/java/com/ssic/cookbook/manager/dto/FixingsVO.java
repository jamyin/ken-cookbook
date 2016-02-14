/**
 * 
 */
package com.ssic.cookbook.manager.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wk.s
 * 数据传输
 */
public class FixingsVO {

	@Getter
	@Setter
	private String bcidsVO;
	
	@Getter
	@Setter
	private String pidsVO;
	
	@Getter
	@Setter
	private String priceVO;
	
	@Getter
	@Setter
	private String foodType; //午餐/晚餐/。。。。传对应类型的value值,如果有多个，则以逗号','分隔,例如:1,2,3,
}
