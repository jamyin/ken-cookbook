/**
 * 
 */
package com.ssic.cookbook.manager.dao;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ssic.cookbook.manager.dto.FixingsResultTypeDto;
import com.ssic.cookbook.manager.mapper.FixingsResultTypeMapper;
import com.ssic.cookbook.manager.pojo.FixingsResultType;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.util.BeanUtils;

/**
 * @author wk.s
 *
 */
@Repository
public class FixingsResultTypeDao {

	@Autowired
	private FixingsResultTypeMapper fRMapper;
	
	/**
	 * 新增
	 * @param dto
	 * @return
	 */
	public boolean add(FixingsResultTypeDto dto){
		
		boolean flag = false;
		try {
			FixingsResultType record = BeanUtils.createBeanByTarget(dto, FixingsResultType.class);
			record.setCreateTime(new Date());
			record.setStat(CookbookFields.Enable);
			int i = fRMapper.insertSelective(record);
			if(i == 1){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
