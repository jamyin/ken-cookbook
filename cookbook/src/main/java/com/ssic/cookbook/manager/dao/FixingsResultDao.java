package com.ssic.cookbook.manager.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssic.cookbook.base.BaseTestCase;
import com.ssic.cookbook.manager.dto.FixingsResultDto;
import com.ssic.cookbook.manager.mapper.FixingsResultMapper;
import com.ssic.cookbook.manager.pojo.FixingsResult;
import com.ssic.cookbook.manager.pojo.FixingsResultExample;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.util.BeanUtils;

@Repository
public class FixingsResultDao{

	
	@Autowired
	private FixingsResultMapper mapper;
	
	
	/**
	 * findFixingsResultCount：获取配菜结果总数
	 * @param page
	 * @return
	 * @exception	
	 * @author 张亚伟
	 * @date 2015年12月19日 下午1:59:40
	 */
	public Integer findFixingsResultCount() {
		FixingsResultExample  example= new FixingsResultExample();
		return mapper.countByExample(example);
	}
	
	/**
	 * 新增
	 * @param dto
	 * @return
	 */
	public boolean add(FixingsResultDto dto){
		
		boolean flag = true;
		try {
			FixingsResult record = BeanUtils.createBeanByTarget(dto, FixingsResult.class);
			record.setCreateTime(new Date());
			record.setStat(CookbookFields.Enable);
			int i = mapper.insertSelective(record);
			if(i != 1) flag = true;
		} catch (Exception e) {
			flag = false;
			throw new RuntimeException("新增FixingsResult数据发生异常");
		}
		return flag;
	}
}

