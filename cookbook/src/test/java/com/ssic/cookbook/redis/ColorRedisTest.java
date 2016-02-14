/**
 * 
 */
package com.ssic.cookbook.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssic.cookbook.base.BaseTestCase;
import com.ssic.cookbook.manager.dto.ProductColorDto;
import com.ssic.cookbook.manager.service.IProductColorService;


/**		
 * <p>Title: ColorRedisTest </p>
 * <p>Description: 类描述</p>
 * <p>Copyright (c) 2016 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2016年1月7日 上午10:29:50	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2016年1月7日 上午10:29:50</p>
 * <p>修改备注：</p>
 */
public class ColorRedisTest extends BaseTestCase
{
    protected static final Log logger = LogFactory.getLog(ColorRedisTest.class);
    @Autowired
    private IProductColorService productColorService;

    @Test
    public void testColorRedis()
    {
        ProductColorDto colorDto = productColorService.findById("1");
        logger.info("redis颜色:---" + colorDto.getName());
    }

}
