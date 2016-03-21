package com.ssic.cookbook.manager.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ssic.base.redis.RedisKeyPrefix;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@RedisKeyPrefix(prefixValue="cookbook.manager.dto.IntelligentFixingsDtoList")
public class IntelligentFixingsDto  implements Serializable {

	
     /**   
     * serialVersionUID: （一句话描述这个变量表示什么）      
     */   
    
    private static final long serialVersionUID = 5520549131892512190L;

    // primary Key
	@Getter
	@Setter
	private String id;

	// 套餐名称
	@Getter
	@Setter
	private String setMealName;
	
	// 配菜名称
	@Getter
	@Setter
	private String fixingsName;
	
	// 所属配菜主表id
	@Getter
	@Setter
	private String fixingsMasterId;

	// 所属配菜名称
	@Getter
	@Setter
	private String fixingsMasterName;
	
	// 配菜类别
	@Getter
	@Setter
	private Integer fixingsType;
	
	
	// 午餐数量
	@Getter
	@Setter
	private Integer lunchCount;

	// 晚餐数量
	@Getter
	@Setter
	private Integer dinnerCount;

	// 所属口味id
	@Getter
	@Setter
	private String productTasteId;

	// 所属口味名称
	@Getter
	@Setter
	private String productTasteName;
	
	// 所属颜色id
	@Getter
	@Setter
	private String productColorId;

	// 所属颜色名称
	@Getter
	@Setter
	private String productColorName;
	
	
	// 所属烹饪方式id
	@Getter
	@Setter
	private String productCuisineId;

	// 所属烹饪方式名称
	@Getter
	@Setter
	private String productCuisineName;
	
	// 所属菜系id
	@Getter
	@Setter
	private String productStyleId;
	
	// 所属菜系名称
	@Getter
	@Setter
	private String productStyleName;

	// 所属形状id
	@Getter
	@Setter
	private String productShapeId;
	
	// 所属形状名称
	@Getter
	@Setter
	private String productShapeName;

	// 总成本运算符
	@Getter
	@Setter
	private String totalCostOperator;

	// 总成本
	@Getter
	@Setter
	private Long totalCost;
	
	// 总成本显示
	@Getter
	@Setter
	private String totalCostStr;

	// 总定价运算符
	@Getter
	@Setter
	private String totalFixedPriceOperator;

	// 总定价
	@Getter
	@Setter
	private Long totalFixedPrice;
	
	// 总定价显示
	@Getter
	@Setter
	private String totalFixedPriceStr;

	// 是否敏感食材
	@Getter
	@Setter
	private Integer isSensitiveIngredients;
	
	// 配菜开始日期
	@Getter
	@Setter
	private Date fixingsStartTime;
	
	// 配菜结束日期
	@Getter
	@Setter
	private Date fixingsEndTime;
	
    @Getter
    @Setter
    private List<CategoryDto> category = new ArrayList<CategoryDto>(); //种类
    
	@Getter
	@Setter
	private String[] categoryId ; //种类Id
	
	@Getter
	@Setter
	private String[] categoryCount ; //种类对应的数量
    
    @Getter
    @Setter
    private List<SmartNutritionDto> nutrition = new ArrayList<SmartNutritionDto>(); //营养    
    
	@Getter
	@Setter
	private String[] nutritionId ; //营养Id
    
    @Getter
    @Setter
    private String[] nutritionOperator ; //营养运算符
	
	@Getter
	@Setter
	private String[] nutritionContent  ; //种类对应的数量
    
	// 创建时间
	@Getter
	@Setter
	private Date createTime;
	
	// 创建时间显示
	@Getter
	@Setter
	private String createTimeStr;

	// 更新时间
	@Getter
	@Setter
	private Date lastUpdateTime;
	
	// 更新时间显示
	@Getter
	@Setter
	private String lastUpdateTimeStr;

	// 是否有效:1是
	@Getter
	@Setter
	private Integer stat;
	
	@Getter
	@Setter
	private String fixingsStartTimeStr; //配菜开始日期
	
	@Getter
	@Setter
	private String fixingsEndTimeStr; //配菜结束日期
	
	@Getter
	@Setter
	private String[] styleId ; //菜系Id
	
	@Getter
	@Setter
	private String[] shapeId ; //形状Id
	
	@Getter
	@Setter
	private String[] tasteId ; //口味Id
	
	@Getter
	@Setter
	private String[] cuisineId ; //烹饪Id
	
	@Getter
	@Setter
	private String[] colorId ; //颜色Id
	
    @Getter
    @Setter
    private List<StyleDto> style = new ArrayList<StyleDto>(); 
    
    @Getter
    @Setter
    private List<ShapeDto> shape = new ArrayList<ShapeDto>(); 
    
    @Getter
    @Setter
    private List<TasteDto> taste = new ArrayList<TasteDto>(); 
    
    @Getter
    @Setter
    private List<CuisineDto> cuisine = new ArrayList<CuisineDto>(); 
    
    @Getter
    @Setter
    private List<ColorDto> color = new ArrayList<ColorDto>(); 
    
    //	分页开始
	@Getter
	@Setter
	private int star;
	
	//	分页结束
	@Getter
	@Setter
	private int end;
	
	//	分页总条数
	@Getter
	@Setter
	private int totalPage;
	
	@Getter
	@Setter
	private String productId;
	

}