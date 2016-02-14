package com.ssic.cookbook.manager.dao;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ssic.cookbook.manager.dto.ShapeDto;
import com.ssic.cookbook.manager.mapper.IntelligentFixingsShapeExMapper;
import com.ssic.cookbook.manager.mapper.IntelligentFixingsShapeMapper;
import com.ssic.cookbook.manager.pojo.IntelligentFixingsShape;
import com.ssic.cookbook.manager.pojo.IntelligentFixingsShapeExample;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.util.BeanUtils;
import com.ssic.util.base.MyBatisBaseDao;

/**
 * 智能配菜形状关系表
 * @author YIn
 * @time:2016年1月6日 下午5:19:57
 * @ClassName: ShapeDao
 * @Description: TODO
 * @
 */
@Repository
public class ShapeDao extends MyBatisBaseDao<IntelligentFixingsShape>
{
	@Autowired
    @Getter
    private IntelligentFixingsShapeMapper mapper;
	
	@Autowired
	@Getter
	private IntelligentFixingsShapeExMapper mappers;
    /**
     * 
     * @author YIn
     * @time:2016年1月6日 下午5:20:12
     * @param c
     * @return
     */
	public boolean add(ShapeDto c) {
		boolean flag = false;
		IntelligentFixingsShape record = BeanUtils.createBeanByTarget(c, IntelligentFixingsShape.class);
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
	 * @time:2016年1月8日 下午2:23:19
	 * @param id
	 * @return
	 */
	public List<IntelligentFixingsShape> findShape(String id) {
		IntelligentFixingsShapeExample example = new IntelligentFixingsShapeExample();
		IntelligentFixingsShapeExample.Criteria criteria = example.createCriteria();
		criteria.andStatEqualTo(CookbookFields.Enable);
		if (!StringUtils.isEmpty(id)){
		criteria.andIntelligentFixingsIdEqualTo(id);
		}
		example.setOrderByClause(" create_time DESC");
		List<IntelligentFixingsShape> list = mapper.selectByExample(example);
		return list;
	}

	/**
	 * @author YIn
	 * @time:2016年2月1日 下午8:55:33
	 */
	public boolean addList(List<ShapeDto> shape) {
		
		return mappers.addList(shape);
	}
}
