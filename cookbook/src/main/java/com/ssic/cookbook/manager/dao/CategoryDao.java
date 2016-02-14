package com.ssic.cookbook.manager.dao;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ssic.cookbook.manager.dto.CategoryDto;
import com.ssic.cookbook.manager.mapper.IntelligentFixingsCategoryExMapper;
import com.ssic.cookbook.manager.mapper.IntelligentFixingsCategoryMapper;
import com.ssic.cookbook.manager.pojo.IntelligentFixingsCategory;
import com.ssic.cookbook.manager.pojo.IntelligentFixingsCategoryExample;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.util.BeanUtils;
import com.ssic.util.base.MyBatisBaseDao;

/**
 * 智能配菜种类关系表
 * @author YIn
 * @time:2015年12月31日 下午7:50:53
 * @ClassName: SmartNutritionDao
 * @Description: TODO
 * @
 */
@Repository
public class CategoryDao extends MyBatisBaseDao<IntelligentFixingsCategory>
{
	@Autowired
    @Getter
    private IntelligentFixingsCategoryMapper mapper;
	
	@Autowired
	@Getter
	private IntelligentFixingsCategoryExMapper mappers;
    /**
	 * @author YIn
	 * @time:2015年12月31日 下午8:00:21
	 */
	public boolean add(CategoryDto c) {
		boolean flag = false;
		IntelligentFixingsCategory record = BeanUtils.createBeanByTarget(c, IntelligentFixingsCategory.class);
			record.setId(UUID.randomUUID()+"");
			record.setCreateTime(new Date());
			record.setStat(1);
			int i = mapper.insertSelective(record);
			if(i == 1){
					flag = true;
				}else{
					flag = false;	
				}
			return flag;
	}
	
	/**
	 * @author YIn
	 * @time:2016年1月8日 下午1:20:48
	 */
	public List<IntelligentFixingsCategory> findCategory(String id) {
		IntelligentFixingsCategoryExample example = new IntelligentFixingsCategoryExample();
		IntelligentFixingsCategoryExample.Criteria criteria = example.createCriteria();
		criteria.andStatEqualTo(CookbookFields.Enable);
		if (!StringUtils.isEmpty(id)){
		criteria.andIntelligentFixingsIdEqualTo(id);
		}
		example.setOrderByClause(" create_time DESC");
		List<IntelligentFixingsCategory> list = mapper.selectByExample(example);
		return list;
	}

	/**
	 * @author YIn
	 * @time:2016年2月1日 下午8:25:20
	 */
	public boolean addList(List<CategoryDto> category) {
		return mappers.addList(category);
	}



}
