package com.ssic.cookbook.manager.dao;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ssic.cookbook.manager.dto.CuisineDto;
import com.ssic.cookbook.manager.mapper.IntelligentFixingsCuisineExMapper;
import com.ssic.cookbook.manager.mapper.IntelligentFixingsCuisineMapper;
import com.ssic.cookbook.manager.pojo.IntelligentFixingsCuisine;
import com.ssic.cookbook.manager.pojo.IntelligentFixingsCuisineExample;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.util.BeanUtils;
import com.ssic.util.base.MyBatisBaseDao;

/**
 * 智能配菜烹饪关系表
 * @author YIn
 * @time:2016年1月6日 下午5:19:57
 * @ClassName: CuisineDao
 * @Description: TODO
 * @
 */
@Repository
public class CuisineDao extends MyBatisBaseDao<IntelligentFixingsCuisine>
{
	@Autowired
    @Getter
    private IntelligentFixingsCuisineMapper mapper;
	
	@Autowired
	@Getter
	private IntelligentFixingsCuisineExMapper mappers;
    /**
     * 
     * @author YIn
     * @time:2016年1月6日 下午5:20:12
     * @param c
     * @return
     */
	public boolean add(CuisineDto c) {
		boolean flag = false;
		IntelligentFixingsCuisine record = BeanUtils.createBeanByTarget(c, IntelligentFixingsCuisine.class);
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
	 * @time:2016年1月8日 下午2:24:09
	 * @param id
	 * @return
	 */
	public List<IntelligentFixingsCuisine> findCuisine(String id) {
		IntelligentFixingsCuisineExample example = new IntelligentFixingsCuisineExample();
		IntelligentFixingsCuisineExample.Criteria criteria = example.createCriteria();
		criteria.andStatEqualTo(CookbookFields.Enable);
		if (!StringUtils.isEmpty(id)){
		criteria.andIntelligentFixingsIdEqualTo(id);
		}
		example.setOrderByClause(" create_time DESC");
		List<IntelligentFixingsCuisine> list = mapper.selectByExample(example);
		return list;
	}

	/**
	 * @author YIn
	 * @time:2016年2月1日 下午9:10:02
	 */
	public boolean addList(List<CuisineDto> cuisine) {

		return mappers.addList(cuisine);
	}
}
