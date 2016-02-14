package com.ssic.cookbook.manager.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class ProductNexusDto implements Serializable {

	/**   
	* serialVersionUID: （一句话描述这个变量表示什么）      
	*/

	private static final long serialVersionUID = 1L;

	//成品菜ID
	@Getter
	@Setter
	private String id;

	//成品菜名称
	@Getter
	@Setter
	private String productName;

	//原料名称
	@Getter
	@Setter
	private String materialName;

	//营养ID
	@Getter
	@Setter
	private String nutrId;

	//营养名称
	@Getter
	@Setter
	private String nutrName;

	//营养含量
	@Getter
	@Setter
	private String nutrContent;

	//营养单位
	@Getter
	@Setter
	private String nutrUnit;

	//成品菜所含营养
	@Getter
	@Setter
	private List<NutritionDto> nutritionDtoList;

	//是否有敏感食材
	@Getter
	@Setter
	private Integer isSensitiveMaterial = 0;

}
