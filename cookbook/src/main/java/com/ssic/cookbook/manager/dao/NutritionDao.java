package com.ssic.cookbook.manager.dao;


import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Getter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.dto.NutritionDto;
import com.ssic.cookbook.manager.dto.SmartNutritionDto;
import com.ssic.cookbook.manager.mapper.IntelligentFixingsNutritionExMapper;
import com.ssic.cookbook.manager.mapper.IntelligentFixingsNutritionMapper;
import com.ssic.cookbook.manager.mapper.NutritionExMapper;
import com.ssic.cookbook.manager.mapper.NutritionMapper;
import com.ssic.cookbook.manager.pojo.IntelligentFixingsNutrition;
import com.ssic.cookbook.manager.pojo.IntelligentFixingsNutritionExample;
import com.ssic.cookbook.manager.pojo.Nutrition;
import com.ssic.cookbook.manager.pojo.NutritionExample;
import com.ssic.cookbook.manager.pojo.NutritionExample.Criteria;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.util.BeanUtils;

@Repository
public class NutritionDao {

	@Autowired
	private NutritionMapper mapper;
	
	@Autowired
	private NutritionExMapper exmapper;
	
    @Autowired
    @Getter
    private IntelligentFixingsNutritionMapper mappers;
    
    @Autowired
    @Getter
    private IntelligentFixingsNutritionExMapper exMappers;

	public List<Nutrition> findForList(Nutrition param){
		NutritionExample example = new NutritionExample();
		Criteria criteria = example.createCriteria();
		if(!StringUtils.isEmpty(param.getId())){
			criteria.andIdEqualTo(param.getId());
		}
		if(!StringUtils.isEmpty(param.getContent())){
			criteria.andContentEqualTo(param.getContent());
		}
		if(!StringUtils.isEmpty(param.getUnit())){
			criteria.andUnitEqualTo(param.getUnit());
		}
		criteria.andStatEqualTo(CookbookFields.Enable);
		return mapper.selectByExample(example);
	}
	
	public Nutrition findById(String id){
		return mapper.selectByPrimaryKey(id);
	}
	
	public void updateById(Nutrition param){
		param.setStat(CookbookFields.Enable);
		mapper.updateByPrimaryKeySelective(param);
	}
	
	public void insertNut(Nutrition param){
		param.setStat(CookbookFields.Enable);
		mapper.insert(param);
	}
	
	public void deleteNutr(Nutrition param){
		mapper.updateByPrimaryKeySelective(param);
	}

	/**
	 * @author YIn
	 * @time:2015年12月30日 下午2:59:43
	 */
	private static final Logger log = Logger.getLogger(Nutrition.class);
	public List<Nutrition> findListByPage(Nutrition nutrition,
			LimitPageDto limitPageDto) {
		if (nutrition == null)
        {
            log.error("参数nutrition为空");
        }
		NutritionExample example = new NutritionExample();
        Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(nutrition.getId()))
        {
            criteria.andIdEqualTo(nutrition.getId());
        }
        if (!StringUtils.isEmpty(nutrition.getName()))
        {
            criteria.andNameLike("%" + nutrition.getName() + "%");
        }
        //如果有分页对象;
        if (limitPageDto != null && !StringUtils.isEmpty(limitPageDto.getStar())
            && !StringUtils.isEmpty(limitPageDto.getEnd()))
        {
            example.setOrderByClause("create_time desc limit " + limitPageDto.getStar() + ","
                + limitPageDto.getEnd());
        }
        criteria.andStatEqualTo(CookbookFields.Enable);
        return mapper.selectByExample(example);
	}
	
	   /**
		 * @author YIn
		 * @time:2015年12月31日 下午8:00:21
		 */
		public boolean add(SmartNutritionDto n) {
			boolean flag = false;
			IntelligentFixingsNutrition record = BeanUtils.createBeanByTarget(n, IntelligentFixingsNutrition.class);
				record.setId(UUID.randomUUID()+"");
				record.setCreateTime(new Date());
				record.setStat(1);
				int i = mappers.insertSelective(record);
				if(i == 1){
						flag = true;
					}else{
						flag = false;	
					}
				return flag;
		}
		
		/**
		 * 
		 * @author YIn
		 * @time:2016年1月8日 下午2:18:55
		 * @param id
		 * @return
		 */
		public List<IntelligentFixingsNutrition> findNutrition(String id) {
			IntelligentFixingsNutritionExample example = new IntelligentFixingsNutritionExample();
			IntelligentFixingsNutritionExample.Criteria criteria = example.createCriteria();
			criteria.andStatEqualTo(CookbookFields.Enable);
			if (!StringUtils.isEmpty(id)){
			criteria.andIntelligentFixingsIdEqualTo(id);
			}
			example.setOrderByClause(" create_time DESC");
			List<IntelligentFixingsNutrition> list = mappers.selectByExample(example);
			return list;
		}
	
		public void updateNutrByPid(NutritionDto nutritionDto){
			exmapper.updateNutrByPid(nutritionDto);
		}

		/**
		 * @author YIn
		 * @time:2016年2月2日 上午9:01:58
		 */
		public boolean addList(List<SmartNutritionDto> nutrition) {
			
			return exMappers.addList(nutrition);
		}
}
