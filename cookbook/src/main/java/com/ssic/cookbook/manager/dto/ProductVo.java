/**
 * 
 */
package com.ssic.cookbook.manager.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wk.s
 * 该类用于比较符合过滤条件的成菜
 */
public class ProductVo implements Comparable<ProductVo> {

	@Getter
	@Setter
	private String id;
	
	@Getter
	@Setter
	private Integer count;
	
	/**
	 * 颜色
	 */
	@Getter
	@Setter
	private String productColorId;
	
	/**
	 * 烹饪方式
	 */
	@Getter
	@Setter
	private String productCuisineId;
	
	/**
	 * 菜系
	 */
    @Getter
    @Setter
    private String productStyleId;
    
    /**
     * 形状
     */
    @Getter
    @Setter
    private String productShapeId;
    
    /**
     * 口味
     */
    @Getter
    @Setter
    private String productTasteId;
    
    @Getter
    @Setter
    private String productCategoryId;

    /**
     * 每份定价
     */
    @Getter
    @Setter
    private Long eachPricing;
    
    /**
     * 单份成本
     */
    @Getter
    @Setter
    private Double singleCost;
    
    /**
     * 是否包含敏感食材
     * <br>1表示要求含有；0表示要求不含有
     */
    @Getter
    @Setter
    private Integer isSensitiveMaterial;
    
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
    
	public int compareTo(ProductVo o) {
		
		return this.count.compareTo(o.getCount());
	}

}
