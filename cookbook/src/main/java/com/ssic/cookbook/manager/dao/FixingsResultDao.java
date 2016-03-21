package com.ssic.cookbook.manager.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssic.cookbook.manager.dto.FixingsResultDto;
import com.ssic.cookbook.manager.mapper.FixingsResultMapper;
import com.ssic.cookbook.manager.pojo.FixingsResult;
import com.ssic.cookbook.manager.pojo.FixingsResultExample;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.util.BeanUtils;
import com.ssic.util.StringUtils;
import com.ssic.util.constants.DataStatus;

@Repository
public class FixingsResultDao   {

	
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

	/**
	 * @author YIn
	 * @time:2016年3月21日 下午3:21:13
	 */
	public Integer updateFixingsResult(FixingsResult fixingsResult) {
		return mapper.updateByPrimaryKeySelective(fixingsResult);
	}

	/**
	 * @author YIn
	 * @time:2016年3月21日 下午3:28:20
	 */
	public FixingsResult findFixingsResultById(FixingsResult fixingsResult) {
        FixingsResultExample example = new FixingsResultExample();
        FixingsResultExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(fixingsResult.getId())) {
            criteria.andIdEqualTo(fixingsResult.getId());
        }
        criteria.andStatEqualTo(DataStatus.ENABLED);
        List<FixingsResult> list = mapper.selectByExample(example);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
}

