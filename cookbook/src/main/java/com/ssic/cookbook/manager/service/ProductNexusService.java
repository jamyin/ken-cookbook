/**
 * 
 */
package com.ssic.cookbook.manager.service;

import java.util.List;

import com.ssic.cookbook.manager.dto.ProductNexusDto;

/**		
* <p>Title: ProductNexusService </p>
* <p>Description: 类描述</p>
* <p>Copyright (c) 2016 </p>
* <p>Company: 上海天坊信息科技有限公司</p>
* @author：张亚伟	
* @Date：2016年1月18日 下午4:48:43	
* @Version 1.0
* <p>修改人：</p>张亚伟
* <p>修改时间：</p>2016年1月18日 下午4:48:43 
* <p>修改备注：</v> 
*/
public interface ProductNexusService {

	public ProductNexusDto finProductNexusByProductId(ProductNexusDto productNexusDto);
}
