package com.ssic.cookbook.manager.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class NutritionDto implements Serializable{

	    
	 /**   
	 * serialVersionUID: （一句话描述这个变量表示什么）      
	 */   
	
	private static final long serialVersionUID = 1L;
		@Getter
	    @Setter
	    private String id;
	    @Getter
	    @Setter
	    private String name;
	    @Getter
	    @Setter
	    private Long content;  //含量
	    
	    @Getter
	    @Setter
	    private Double con;  //含量 Double型
	    
	    @Getter
	    @Setter
	    private String contentstr;
	    @Getter
	    @Setter
	    private String unit;  //单位
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
	    private String paramId;  //营养参数ID
	    @Getter
	    @Setter
	    private String paramType;   //营养参数类型
	
	
}
