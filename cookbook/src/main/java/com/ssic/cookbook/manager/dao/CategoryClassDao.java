/**
 * 
 */
package com.ssic.cookbook.manager.dao;

import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ssic.cookbook.manager.dto.CategoryClassDto;
import com.ssic.cookbook.manager.mapper.CategoryClassMapper;
import com.ssic.cookbook.manager.pojo.CategoryClass;
import com.ssic.cookbook.manager.pojo.CategoryClassExample;
import com.ssic.cookbook.manager.pojo.CategoryClassExample.Criteria;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.util.BeanUtils;

/**
 * @author wk.s
 *
 */
@Repository
public class CategoryClassDao {

	@Autowired
	private CategoryClassMapper categoryClassMapper;
	private Logger log = LoggerFactory.getLogger(getClass());
	
	/**
	 * 新增
	 * @param dto
	 * @return
	 */
	public boolean add(CategoryClassDto dto){
		
		boolean flag = false;
		try {
			CategoryClass record = BeanUtils.createBeanByTarget(dto, CategoryClass.class);
			record.setCreateTime(new Date());
			record.setStat(CookbookFields.Enable);
			int i = categoryClassMapper.insertSelective(record);
			if(i == 1) flag = true;
		} catch (Exception e) {
			log.error("新增CategoryClass数据发生异常", e);
		}
		return flag;
	}
	
	/**
	 * <b>根据id更新</b>
	 * <br>以id为条件，其它fields为参数
	 * @param dto
	 * @return
	 */
	public boolean updateById(CategoryClassDto dto){
		
		boolean flag = false;
		try {
			CategoryClass record = BeanUtils.createBeanByTarget(dto, CategoryClass.class);
			record.setLastUpdateTime(new Date());
			int i = categoryClassMapper.updateByPrimaryKeySelective(record);
			if(i == 1) flag = true;
		} catch (Exception e) {
			log.error("更新CategoryClass数据发生异常", e);
		}
		return flag;
	}
	
	/**
	 * 逻辑删除
	 * @param dto
	 * @return
	 */
	public boolean delete(CategoryClassDto dto){
	
		boolean flag = false;
		try {
			CategoryClass record = new CategoryClass();
			record.setStat(CookbookFields.DisEnable);
			record.setLastUpdateTime(new Date());
			CategoryClass param = BeanUtils.createBeanByTarget(dto, CategoryClass.class);
			CategoryClassExample example = new CategoryClassExample();
			Criteria criteria = example.createCriteria();
			assembleCons(param, criteria);
			categoryClassMapper.updateByExample(record, example);
		} catch (Exception e) {
			log.error("逻辑删除CategoryClass数据发生异常", e);
		}
		return flag;
	}
	
	/**
	 * 组装条件
	 * @param param
	 * @param criteria
	 */
	public void assembleCons(CategoryClass param, Criteria criteria){
		
		String id = param.getId();
		if(StringUtils.isNotBlank(id)) criteria.andIdEqualTo(id);
		
		String bigCategoryId = param.getBigCategoryId();
		if(StringUtils.isNotBlank(bigCategoryId)) criteria.andBigCategoryIdEqualTo(bigCategoryId);
		
		Long cost = param.getCost();
		if(cost != null) criteria.andCostEqualTo(cost);
		
		Date createTime = param.getCreateTime();
		if(createTime != null) criteria.andCreateTimeEqualTo(createTime);
		
		String independentFixingsId = param.getIndependentFixingsId();
		if(StringUtils.isNotBlank(independentFixingsId)) criteria.andIndependentFixingsIdEqualTo(independentFixingsId);
		
		Date lastUpdateTime = param.getLastUpdateTime();
		if(lastUpdateTime != null) criteria.andLastUpdateTimeEqualTo(lastUpdateTime);
		
		Integer stat = param.getStat();
		if(stat != null) criteria.andStatEqualTo(stat);
		
	}
}
