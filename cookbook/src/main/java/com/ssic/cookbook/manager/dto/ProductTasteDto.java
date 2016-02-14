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
 * <p>Title: ProductTasteDto </p>
 * <p>Description: 成品菜口味Dto</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2015年12月16日 上午10:19:07	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月16日 上午10:19:07</p>
 * <p>修改备注：</p>
 */
@ToString
@RedisKeyPrefix(prefixValue="cookbook.manager.dto.ProductTasteDtoList")
public class ProductTasteDto implements Serializable
{

    
     /**   
     * serialVersionUID: （一句话描述这个变量表示什么）      
     */   
    
    private static final long serialVersionUID = 3721828683662417594L;

    /**
     * 口味id
     */
    @Getter
    @Setter
    private String id;

    /**
     * 口味名称
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

