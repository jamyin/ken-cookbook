/**
 * 
 */
package com.ssic.cookbook.manager.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssic.cookbook.manager.dto.ProductNexusDto;
import com.ssic.cookbook.manager.mapper.ProductExMapper;

/**		
* <p>Title: ProductNexusDao </p>
* <p>Description: 类描述</p>
* <p>Copyright (c) 2016 </p>
* <p>Company: 上海天坊信息科技有限公司</p>
* @author：张亚伟	
* @Date：2016年1月18日 下午4:54:12	
* @Version 1.0
* <p>修改人：</p>张亚伟
* <p>修改时间：</p>2016年1月18日 下午4:54:12 
* <p>修改备注：</v> 
*/
@Repository
public class ProductNexusDao {

	@Autowired
	private ProductExMapper exMapper;

	public List<ProductNexusDto> finProductNexusByProductId(
			ProductNexusDto productNexusDto) {

		if (productNexusDto.getId() == null
				&& productNexusDto.getId().length() < 1) {
			return null;
		}

		return exMapper.findProductNexus(productNexusDto);
	}

}
