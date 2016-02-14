/**
 * 
 */
package com.ssic.cookbook.manager.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**		
 * <p>Title: MaterialNutritionDto </p>
 * <p>Description: 原料营养关系Dto</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2015年12月22日 下午5:20:07	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月22日 下午5:20:07</p>
 * <p>修改备注：</p>
 */
public class MaterialNutritionDto implements Serializable
{
    
     /**   
     * serialVersionUID: （一句话描述这个变量表示什么）      
     */   
    
    private static final long serialVersionUID = -58010405122335926L;
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private String brandId;
    @Getter
    @Setter
    private String nutritionId;
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
    private String materialId;
}

