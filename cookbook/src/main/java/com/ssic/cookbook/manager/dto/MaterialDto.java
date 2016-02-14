package com.ssic.cookbook.manager.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class MaterialDto implements Serializable{

	    @Getter
	    @Setter
	    private String id;
	    @Getter
	    @Setter
	    private String imgUrl;
	    @Getter
	    @Setter
	    private String name;
	    @Getter
	    @Setter
	    private Integer type;
	    @Getter
	    @Setter
	    private Long cost;
	    @Getter
	    @Setter
	    private String strcost;
	    @Getter
	    @Setter
	    private String bigCategoryId;
	    @Getter
	    @Setter
	    private String brandId;
	    @Getter
	    @Setter
	    private Integer isSensitiveMaterial;
	    @Getter
	    @Setter
	    private String remark;
	    @Getter
	    @Setter
	    private Date createTime;
	    @Getter
	    @Setter
	    private Date lastUpdateTime;
	    @Getter
	    @Setter
	    private Integer stat;
	    @Getter
	    @Setter
	    private String typeName;
	    @Getter
	    @Setter
	    private String isSensitiveName;
	    @Getter
	    @Setter
	    private String bigCategoryName;
	    @Getter
	    @Setter
	    private String brandName;
	    @Getter
	    @Setter
	    private Integer limitStar;
	    @Getter
	    @Setter
	    private Integer limitEnd;
	    @Getter
	    @Setter
	    private List<NutritionDto> listNu;    //营养列表
	    
	    
}
