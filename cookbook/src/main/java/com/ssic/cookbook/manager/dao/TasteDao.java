package com.ssic.cookbook.manager.dao;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ssic.cookbook.manager.dto.TasteDto;
import com.ssic.cookbook.manager.mapper.IntelligentFixingsTasteExMapper;
import com.ssic.cookbook.manager.mapper.IntelligentFixingsTasteMapper;
import com.ssic.cookbook.manager.pojo.IntelligentFixingsTaste;
import com.ssic.cookbook.manager.pojo.IntelligentFixingsTasteExample;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.util.BeanUtils;
import com.ssic.util.base.MyBatisBaseDao;

/**
 * 智能配菜口味关系表
 * @author YIn
 * @time:2016年1月6日 下午5:19:57
 * @ClassName: TasteDao
 * @Description: TODO
 * @
 */
@Repository
public class TasteDao extends MyBatisBaseDao<IntelligentFixingsTaste>
{
	@Autowired
    @Getter
    private IntelligentFixingsTasteMapper mapper;
	
	@Autowired
	@Getter
	private IntelligentFixingsTasteExMapper mappers;
    /**
     * 
     * @author YIn
     * @time:2016年1月6日 下午5:20:12
     * @param c
     * @return
     */
	public boolean add(TasteDto c) {
		boolean flag = false;
		IntelligentFixingsTaste record = BeanUtils.createBeanByTarget(c, IntelligentFixingsTaste.class);
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
	public List<IntelligentFixingsTaste> findTaste(String id) {
		IntelligentFixingsTasteExample example = new IntelligentFixingsTasteExample();
		IntelligentFixingsTasteExample.Criteria criteria = example.createCriteria();
		criteria.andStatEqualTo(CookbookFields.Enable);
		if (!StringUtils.isEmpty(id)){
		criteria.andIntelligentFixingsIdEqualTo(id);
		}
		example.setOrderByClause(" create_time DESC");
		List<IntelligentFixingsTaste> list = mapper.selectByExample(example);
		return list;
	}

	/**
	 * @author YIn
	 * @time:2016年2月1日 下午9:07:08
	 */
	public boolean addList(List<TasteDto> taste) {

		return mappers.addList(taste);
	}
}
