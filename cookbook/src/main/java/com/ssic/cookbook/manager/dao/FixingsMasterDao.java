/**
 * 
 */
package com.ssic.cookbook.manager.dao;

import java.util.Date;
import lombok.Getter;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ssic.cookbook.manager.dto.FixingsMasterDto;
import com.ssic.cookbook.manager.mapper.FixingsMasterMapper;
import com.ssic.cookbook.manager.pojo.FixingsMaster;
import com.ssic.cookbook.manager.pojo.FixingsMasterExample;
import com.ssic.cookbook.manager.pojo.FixingsMasterExample.Criteria;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.util.BeanUtils;



/**
 * @author wk.s
 *
 */

@Repository
public class FixingsMasterDao {

	@Autowired
	private FixingsMasterMapper fixingsMasterMapper;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 新增
	 * @param dto
	 * @return
	 */
	public boolean add(FixingsMasterDto dto){
		
		boolean flag = false;
		try {
			FixingsMaster record = BeanUtils.createBeanByTarget(dto, FixingsMaster.class);
			record.setCreateTime(new Date());
			record.setStat(CookbookFields.Enable);
			int i = fixingsMasterMapper.insertSelective(record);
			if(i == 1) flag = true;
		} catch (Exception e) {
			log.error("新增FixingsMaster数据发生异常", e);
		}
		return flag;
	}
	
	/**
	 * 根据id更新
	 * @param dto
	 * @return
	 */
	public boolean updateById(FixingsMasterDto dto){
		
		boolean flag = false;
		try {
			FixingsMaster record = BeanUtils.createBeanByTarget(dto, FixingsMaster.class);
			record.setLastUpdateTime(new Date());
			int i = fixingsMasterMapper.updateByPrimaryKeySelective(record);
			if(i == 1) flag = true;
		} catch (Exception e) {
			log.error("更新FixingsMaster数据发生异常", e);
		}
		return flag;
	}
	
	/**
	 * 逻辑删除
	 * @param dto
	 * @return
	 */
	public boolean delete(FixingsMasterDto dto){
		
		boolean flag = false;
		try {
			FixingsMaster record = new FixingsMaster();
			record.setStat(CookbookFields.DisEnable);
			record.setLastUpdateTime(new Date());
			FixingsMasterExample cons = new FixingsMasterExample();
			Criteria criteria = cons.createCriteria();
			FixingsMaster param = BeanUtils.createBeanByTarget(dto, FixingsMaster.class);
			assembleCons(param, criteria);
			fixingsMasterMapper.updateByExampleSelective(record, cons);
			flag = true;
		} catch (Exception e) {
			log.error("逻辑删除FixingsMaster数据发生异常", e);
		}
		return flag;
	}
	
	/**
	 * 组装条件
	 * @param param
	 * @param criteria
	 */
	public void assembleCons(FixingsMaster param, Criteria criteria){
		
		String id = param.getId();
		if(StringUtils.isNotBlank(id)) criteria.andIdEqualTo(id);
		
		Date createTime = param.getCreateTime();
		if(createTime != null) criteria.andCreateTimeEqualTo(createTime);
		
		String fixingsName = param.getFixingsName();
		if(StringUtils.isNotBlank(fixingsName)) criteria.andFixingsNameEqualTo(fixingsName);
		
		Integer fixingsType = param.getFixingsType();
		if(fixingsType != null) criteria.andFixingsTypeEqualTo(fixingsType);
		
		Date lastUpdateTime = param.getLastUpdateTime();
		if(lastUpdateTime != null) criteria.andLastUpdateTimeEqualTo(lastUpdateTime);
		
		Integer stat = param.getStat();
		if(stat != null) criteria.andStatEqualTo(stat);
	}
}
