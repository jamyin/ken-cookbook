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
 * <p>Title: ProductColorDto </p>
 * <p>Description: 成品菜所属颜色Dto</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2015年12月16日 上午10:06:58	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月16日 上午10:06:58</p>
 * <p>修改备注：</p>
 */
@ToString
@RedisKeyPrefix(prefixValue="cookbook.manager.dto.ProductColorDtoList")
public class ProductColorDto implements Serializable
{

    /**   
    * serialVersionUID: （一句话描述这个变量表示什么）      
    */

    private static final long serialVersionUID = -2456764494448514683L;

    /**
     * 颜色id
     */
    @Getter
    @Setter
    private String id;

    /**
     * 颜色名称
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
