/**
 * 
 */
package com.ssic.cookbook.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**		
 * <p>Title: BaseTestCase </p>
 * <p>Description: 类描述</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2015年12月19日 下午2:39:44	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月19日 下午2:39:44</p>
 * <p>修改备注：</p>
 */
@RunWith(SpringJUnit4ClassRunner.class)  
@WebAppConfiguration
@ContextConfiguration(locations={"classpath*:/spring/ApplicationContext.xml"})
public class BaseTestCase {

    protected static final Log logger = LogFactory.getLog(BaseTestCase.class);


}

