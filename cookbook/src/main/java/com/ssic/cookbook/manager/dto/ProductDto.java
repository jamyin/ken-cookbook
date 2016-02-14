/**
 * 
 */
package com.ssic.cookbook.manager.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssic.base.redis.RedisKeyPrefix;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**		
 * <p>Title: ProductDto </p>
 * <p>Description: 成品菜Dto</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2015年12月16日 上午9:48:05	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月16日 上午9:48:05</p>
 * <p>修改备注：</p>
 */
@ToString
public class ProductDto implements Serializable
{

    /**   
    * serialVersionUID: （一句话描述这个变量表示什么）      
    */

    private static final long serialVersionUID = -1038317511679079993L;
    @Getter
    @Setter
    private String id;

    /**
     * 页面跳转信息:choose(1):添加页面进入编辑页面进入;add(1)：新增页面进入;edit:编辑页面进入/添加原料
     */
    @Getter
    @Setter
    private String pageInfo;

    /**
     * 原料id
     */
    @Getter
    @Setter
    private List<String> materialIdList;

    /**
     * 原料名称
     */
    @Getter
    @Setter
    private List<String> materialNameList;

    /**
      * 营养名称集合
      */
    @Getter
    @Setter
    private List<String> nutritionNameList;

    /**
      * 成品菜名称
      */
    @Getter
    @Setter
    private String name;

    /**
     * 所属类别id
     */
    @Getter
    @Setter
    private String productCategoryId;
    /**
     * 所属类别名称
     */
    @Getter
    @Setter
    private String productCategoryName;

    /**
     * 所属口味id
     */
    @Getter
    @Setter
    private String productTasteId;
    /**
    * 口味名称
    */
    @Getter
    @Setter
    private String productTasteName;

    /**
     * 图片地址
     */
    @Getter
    @Setter
    private String imgUrl;

    /**
     *所属颜色id
     */
    @Getter
    @Setter
    private String productColorId;
    /**
     *颜色名称
     */
    @Getter
    @Setter
    private String productColorName;

    /**
     * 所属烹饪方式id
     */
    @Getter
    @Setter
    private String productCuisineId;
    /**
     * 烹饪方式名称
     */
    @Getter
    @Setter
    private String productCuisineName;

    /**
     * 所属菜系id
     */
    @Getter
    @Setter
    private String productStyleId;
    /**
     * 菜系名称
     */
    @Getter
    @Setter
    private String productStyleName;

    /**
     * 所属形状id
     */
    @Getter
    @Setter
    private String productShapeId;
    /**
     * 形状名称
     */
    @Getter
    @Setter
    private String productShapeName;

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
     * 单份克重
     */
    @Getter
    @Setter
    private Integer singleWeight;

    /**
     * 需求数量
     */
    @Getter
    @Setter
    private Integer demandNumber;

    /**
     * 总成本
     */
    @Getter
    @Setter
    private Double totalCost;

    /**
     * 备注
     */
    @Getter
    @Setter
    private String remark;

    /**
     * 创建时间
     */
    @Getter
    @Setter
    private Date createTime;

    /**
     * 更新时间
     */
    @Getter
    @Setter
    private Date lastUpdateTime;

    /**
     * 是否有效: 1是;0否
     */
    @Getter
    @Setter
    private Integer stat;

    /**
     * 原料克重
     */
    @Getter
    @Setter
    private Integer materialWeight;

    /**
     * 原料克重集合
     */
    @Getter
    @Setter
    private List<Integer> materialWeightList;

    @Getter
    @Setter
    private String materialCompare;   //原料比较符
    
    @Getter
    @Setter
    private Integer count;
    
    /**
     * 下面这些属性兼容张亚伟的dto
     */
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
}
