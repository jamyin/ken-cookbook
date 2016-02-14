package com.ssic.cookbook.manager.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class ParamConfigDto implements Serializable{

	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		@Getter
	    @Setter
	    private String id;
	    @Getter
	    @Setter
	    private String paramId;
	    @Getter
	    @Setter
	    private String paramName;
	    @Getter
	    @Setter
	    private String name;
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
	    private String paramType;
	    @Getter
	    @Setter
	    private String remark;
	    @Getter
	    @Setter
	    private List<String> ids;
	    @Getter
	    @Setter
	    private Integer limitStar;
	    @Getter
	    @Setter
	    private Integer limitEnd;
	
}
