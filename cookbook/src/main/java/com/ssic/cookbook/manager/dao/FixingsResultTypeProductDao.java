/**
 * 
 */
package com.ssic.cookbook.manager.dao;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ssic.cookbook.manager.dto.FixingsResultTypeProductDto;
import com.ssic.cookbook.manager.mapper.FixingsResultTypeProductMapper;
import com.ssic.cookbook.manager.pojo.FixingsResultTypeProduct;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.util.BeanUtils;

/**
 * @author wk.s
 *
 */
@Repository
public class FixingsResultTypeProductDao {

	@Autowired
	private FixingsResultTypeProductMapper mapper;
	
	/**
	 * 新增
	 * @param dto
	 * @return
	 */
	public boolean add(FixingsResultTypeProductDto dto){
		
		boolean flag = false;
		try {
			
			FixingsResultTypeProduct record = BeanUtils.createBeanByTarget(dto, FixingsResultTypeProduct.class);
			record.setCreateTime(new Date());
			record.setStat(CookbookFields.Enable);
			int i = mapper.insertSelective(record);
			if(i == 1) flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
