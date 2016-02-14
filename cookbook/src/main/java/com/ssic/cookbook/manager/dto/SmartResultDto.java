package com.ssic.cookbook.manager.dto;

import lombok.Getter;
import lombok.Setter;
/**
 * @author YIn
 * @time:2016年2月1日 下午5:36:12
 * @ClassName: SmartResultDto
 * @Description: TODO
 * @
 */
public class SmartResultDto {
	
	@Getter
	@Setter
	private String productId;
	
	@Getter
	@Setter
    private String smartId;
	
	@Getter
	@Setter
	private Integer lunchCount;
	
	@Getter
	@Setter
	private Integer dinnerCount;
	
	
}