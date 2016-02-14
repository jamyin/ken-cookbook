/**
 * 
 */
package com.ssic.cookbook.manager.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssic.cookbook.manager.dao.ProductNexusDao;
import com.ssic.cookbook.manager.dto.NutritionDto;
import com.ssic.cookbook.manager.dto.ProductNexusDto;
import com.ssic.cookbook.manager.service.ProductNexusService;

/**		
* <p>Title: ProductNexusServiceImpl </p>
* <p>Description: 类描述</p>
* <p>Copyright (c) 2016 </p>
* <p>Company: 上海天坊信息科技有限公司</p>
* @author：张亚伟	
* @Date：2016年1月18日 下午4:51:46	
* @Version 1.0
* <p>修改人：</p>张亚伟
* <p>修改时间：</p>2016年1月18日 下午4:51:46 
* <p>修改备注：</v> 
*/
@Service
public class ProductNexusServiceImpl implements ProductNexusService {

	@Autowired
	ProductNexusDao productNexusDao;

	public ProductNexusDto finProductNexusByProductId(
			ProductNexusDto productNexusDto) {

		if (productNexusDto.getId() == null
				&& productNexusDto.getId().length() < 1) {
			return null;
		}

		List<ProductNexusDto> productNexusList = productNexusDao
				.finProductNexusByProductId(productNexusDto);

		if (productNexusList == null) {
			return null;
		}

		HashMap<String, NutritionDto> nutritionDtoMap = new HashMap<String, NutritionDto>();
		ProductNexusDto productData=new ProductNexusDto();
		productData.setId(productNexusDto.getId());
		for (ProductNexusDto productNexus : productNexusList) {
			if (productNexus.getIsSensitiveMaterial()==1) {
				productData.setIsSensitiveMaterial(1);
			}
			NutritionDto nutritionDto = nutritionDtoMap.get(productNexus
					.getNutrName());
			if (nutritionDto == null) {
				nutritionDto = new NutritionDto();
				nutritionDto.setName(productNexus.getNutrName());
				nutritionDto.setCon(Double.parseDouble(productNexus
						.getNutrContent()) / 100);
				nutritionDto.setUnit(productNexus.getNutrUnit());
				if (productNexus.getNutrUnit()==null) {
					System.out.println("productNexus.getNutrId()"+"ID为空："+productNexus.getNutrUnit());
				}
				nutritionDto.setId(productNexus.getNutrId());
				nutritionDtoMap.put(productNexus.getNutrName(), nutritionDto);
			} else {
				nutritionDto.setCon((Double.parseDouble(productNexus
						.getNutrContent()) / 100)+nutritionDto.getCon());
			}
		}
		
		List<NutritionDto> nutritionDtoList=new ArrayList<NutritionDto>();
		if (nutritionDtoMap.size()>0) {
			for (String nutrName : nutritionDtoMap.keySet()) {
				nutritionDtoList.add(nutritionDtoMap.get(nutrName));
			}
		}
		productData.setNutritionDtoList(nutritionDtoList);
		return productData;
	}

}
