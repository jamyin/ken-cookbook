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
 * <p>Title: ProductMaterialDto </p>
 * <p>Description: 成品菜原料关系Dto</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2015年12月16日 上午10:28:39	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月16日 上午10:28:39</p>
 * <p>修改备注：</p>
 */
@ToString
@RedisKeyPrefix(prefixValue="cookbook.manager.dto.ProductMaterialDtoList")
public class ProductMaterialDto implements Serializable
{

    /**   
    * serialVersionUID: （一句话描述这个变量表示什么）      
    */

    private static final long serialVersionUID = -6203051633092388360L;

    /**
     * id
     */
    @Getter
    @Setter
    private String id;

    /**
     * 成品菜id
     */
    @Getter
    @Setter
    private String productId;

    /**
     * 原料id
     */
    @Getter
    @Setter
    private String materialId;
    
    /**
     * 原料克重
     */
    @Getter
    @Setter
    private Integer materialWeight;

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
     * 是否有效
     */
    @Getter
    @Setter
    private Integer stat;
}
