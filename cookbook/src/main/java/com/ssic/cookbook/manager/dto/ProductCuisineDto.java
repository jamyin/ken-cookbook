/**
 * 
 */
package com.ssic.cookbook.manager.dto;

import java.io.Serializable;
import java.util.Date;

import com.ssic.base.redis.RedisKeyPrefix;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**		
 * <p>Title: ProductCuisineDto </p>
 * <p>Description: 成品菜烹饪方式Dto</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author Administrator	
 * @date 2015年12月16日 上午10:05:11	
 * @version 1.0
 * <p>修改人：Administrator</p>
 * <p>修改时间：2015年12月16日 上午10:05:11</p>
 * <p>修改备注：</p>
 */
@ToString
@RedisKeyPrefix(prefixValue="cookbook.manager.dto.ProductCuisineDtoList")
public class ProductCuisineDto implements Serializable
{
    
     /**   
     * serialVersionUID: （一句话描述这个变量表示什么）      
     */   
    
    private static final long serialVersionUID = 9129644801723767261L;

    /**
     * 烹饪方式id
     */
    @Getter
    @Setter
    private String id;

    /**
     * 烹饪方式名称
     */
    @Getter
    @Setter
    private String name;

    /**
     * 创建时间
     */
    @Getter
    @Setter
    private Date createTime;
    
    /**
     * 创建时间字符串
     */
    @Getter
    @Setter
    private String createTimeString;

    /**
     * 更新时间
     */
    @Getter
    @Setter
    private Date lastUpdateTime;

    /**
     * 是否有效:1是,0:否;
     */
    @Getter
    @Setter
    private Integer stat;
}

