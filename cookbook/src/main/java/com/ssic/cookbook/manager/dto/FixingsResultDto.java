package com.ssic.cookbook.manager.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@ToString
public class FixingsResultDto {
	
	//primary Key
    @Getter
    @Setter
    private String id;

    //配菜id
    @Getter
    @Setter
    private String fixingsId;
    
    //配菜名称
    @Getter
    @Setter
    private String fixingsName;
    
    // 总成本运算符
    @Getter
    @Setter
    private String  totalCostOperator;
    // 总成本
    @Getter
    @Setter
    private Integer totalCost;
    // 总定价
    @Getter
    @Setter
    private  Integer  totalFixedPrice;
    // 总定价运算符
    @Getter
    @Setter
    private String totalFixedPriceOperator;
                        
    // 类型名称
    @Getter
    @Setter
    private String typeName;
    // 菜品类型
    @Getter
    @Setter
    private String type;
    // 菜品名称
    @Getter
    @Setter
    private String productName;
    // 套餐名称
    @Getter
    @Setter
    private  String  mealName;
    
    
    // 成品菜对应类别id
    @Getter
    @Setter
    private String productCategoryId;
    // 类别id
    @Getter
    @Setter
    private String categoryId;
    //配菜方式：1:自主,2:智能,3:指定套餐
    @Getter
    @Setter
    private Integer fixingsType;

    //创建时间
    @Getter
    @Setter
    private Date createTime;

    //更新时间
    @Getter
    @Setter
    private Date lastUpdateTime;

    //是否有效:1是
    @Getter
    @Setter
    private Integer stat;
    
    @Getter
    @Setter
    private Integer star;
    @Getter
    @Setter
    private Integer end;

   
}