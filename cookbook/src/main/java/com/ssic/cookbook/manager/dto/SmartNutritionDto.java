package com.ssic.cookbook.manager.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
/**
 * 智能配菜营养DTO
 * @author YIn
 * @time:2015年12月31日 上午11:38:06
 * @ClassName: CategoryDto
 * @Description: TODO
 * @
 */
public class SmartNutritionDto {
	
	@Getter
	@Setter
	private String id;
	
	@Getter
	@Setter
    private String nutritionId;
	
	@Getter
	@Setter
    private String nutritionOperator;
	
	@Getter
	@Setter
    private Long nutritionContent;
	
	//营养含量显示
	@Getter
	@Setter
	private String nutritionContentStr;
	
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