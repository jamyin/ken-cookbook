package com.ssic.cookbook.manager.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
/**
 * 智能配菜烹饪DTO
 * @author YIn
 * @time:2016年1月6日 下午5:11:02
 * @ClassName: CuisineDto
 * @Description: TODO
 * @
 */
public class CuisineDto {
	
	@Getter
	@Setter
	private String id;
	
	@Getter
	@Setter
    private String cuisineId;
	
	@Getter
	@Setter
    private String intelligentFixingsId;
	
	@Getter
	@Setter
    private Date createTime;
	
	@Getter
	@Setter
    private Date lastUpdateTime;
	
	@Getter
	@Setter
    private Integer stat;
}