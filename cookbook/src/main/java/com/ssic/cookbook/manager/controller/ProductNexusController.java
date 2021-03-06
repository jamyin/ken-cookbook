/**
 * 
 */
package com.ssic.cookbook.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssic.cookbook.manager.dto.ProductNexusDto;
import com.ssic.cookbook.manager.service.ProductNexusService;

/**		
* <p>Title: ProductNexusController </p>
* <p>Description: 类描述</p>
* <p>Copyright (c) 2016 </p>
* <p>Company: 上海天坊信息科技有限公司</p>
* @author：张亚伟	
* @Date：2016年1月18日 下午4:47:24	
* @Version 1.0
* <p>修改人：</p>张亚伟
* <p>修改时间：</p>2016年1月18日 下午4:47:24 
* <p>修改备注：</v> 
*/
@Controller
@RequestMapping("productNexus")
public class ProductNexusController {

	@Autowired
	ProductNexusService productNexusService;

	@RequestMapping("findById")
	@ResponseBody
	public ProductNexusDto finProductNexusByProductId(String id) {
		ProductNexusDto productNexusDto=new ProductNexusDto();
		productNexusDto.setId(id);
		return productNexusService.finProductNexusByProductId(productNexusDto);
	}
}
