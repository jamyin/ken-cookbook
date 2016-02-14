/**
 * 
 */
package com.ssic.cookbook.manager.dao;

import java.util.Date;

import lombok.Getter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssic.cookbook.manager.dto.IndependentFixingsDto;
import com.ssic.cookbook.manager.mapper.IndependentFixingsMapper;
import com.ssic.cookbook.manager.pojo.IndependentFixings;
import com.ssic.cookbook.manager.pojo.IndependentFixingsExample;
import com.ssic.cookbook.manager.pojo.IndependentFixingsExample.Criteria;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.util.BeanUtils;
import com.ssic.util.StringUtils;
import com.ssic.util.base.MyBatisBaseDao;


/**
 * @author wk.s
 *
 */
@Repository
public class IndependentFixingsDao{
	
	@Autowired
	private IndependentFixingsMapper iFixingsMapper;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 新增
	 * @param dto
	 * @return
	 */
	public boolean add(IndependentFixingsDto dto){
		
		boolean flag = false;
		try {
			IndependentFixings record = BeanUtils.createBeanByTarget(dto, IndependentFixings.class);
			record.setStat(CookbookFields.Enable);
			record.setCreateTime(new Date());
			int i = iFixingsMapper.insertSelective(record);
			if(i == 1) flag = true;
		} catch (Exception e) {
			log.error("新增IndependentFixings数据发生异常", e);
		}
		return flag;
	}
	
	/**
	 * <b>更新</b>
	 * <br>id为条件，其它field为参数
	 * @param dto
	 * @return
	 */
	public boolean updateById(IndependentFixingsDto dto){
	
		boolean flag = false;
		try {
			IndependentFixings param = BeanUtils.createBeanByTarget(dto, IndependentFixings.class);
			param.setLastUpdateTime(new Date());
			int i = iFixingsMapper.updateByPrimaryKeySelective(param);
			if(i == 1) flag = true;
		} catch (Exception e) {
			log.error("根据id更新IndependentFixings数据过程异常", e);
		}
		return flag;
	}
	
	/**
	 * 根据条件逻辑删除
	 * @param dto
	 * @return
	 */
	public boolean delete(IndependentFixingsDto dto){
	
		boolean flag = false;
		try {
			IndependentFixings cons = BeanUtils.createBeanByTarget(dto, IndependentFixings.class);
			IndependentFixingsExample example = new IndependentFixingsExample();
			Criteria criteria = example.createCriteria();
			assembleCons(cons, criteria);
			IndependentFixings record = new IndependentFixings();
			record.setStat(CookbookFields.DisEnable);
			record.setLastUpdateTime(new Date());
			iFixingsMapper.updateByExampleSelective(record, example);
			flag = true;
		} catch (Exception e) {
			log.error("逻辑删除数据发生异常", e);
		}
		return flag;
	}
	
	/**
	 * 查询条件组装(只支持equeal to)
	 * @param dto
	 * @param criteria
	 */
	public void assembleCons(IndependentFixings dto, Criteria criteria){
		
		String id = dto.getId();
		if(StringUtils.isNotBlank(id)) criteria.andIdEqualTo(id);
		
		Date createTime = dto.getCreateTime();
		if(createTime != null) criteria.andCreateTimeEqualTo(createTime);
		
		Integer dinnerCount = dto.getDinnerCount();
		if(dinnerCount != null) criteria.andDinnerCountEqualTo(dinnerCount);
		
		Date fixingsTime = dto.getFixingsTime();
		if(fixingsTime != null) criteria.andFixingsTimeEqualTo(fixingsTime);
		
		String fixingsMasterId = dto.getFixingsMasterId();
		if(StringUtils.isNotBlank(fixingsMasterId)) criteria.andFixingsMasterIdEqualTo(fixingsMasterId);
		
		Date lastUpdateTime = dto.getLastUpdateTime();
		if(lastUpdateTime != null) criteria.andLastUpdateTimeEqualTo(lastUpdateTime);
		
		Integer lunchCount = dto.getLunchCount();
		if(lunchCount != null) criteria.andLunchCountEqualTo(lunchCount);
		
		Integer stat = dto.getStat();
		if(stat != null) criteria.andStatEqualTo(stat);
	}

}
