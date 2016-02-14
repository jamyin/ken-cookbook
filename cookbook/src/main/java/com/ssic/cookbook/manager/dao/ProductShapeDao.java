/**
 * 
 */
package com.ssic.cookbook.manager.dao;

import java.util.Date;
import java.util.List;

import lombok.Getter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.mapper.ProductShapeMapper;
import com.ssic.cookbook.manager.pojo.ProductCuisine;
import com.ssic.cookbook.manager.pojo.ProductCuisineExample;
import com.ssic.cookbook.manager.pojo.ProductShape;
import com.ssic.cookbook.manager.pojo.ProductShapeExample;
import com.ssic.cookbook.manager.pojo.ProductTaste;
import com.ssic.cookbook.manager.pojo.ProductTasteExample;
import com.ssic.cookbook.manager.pojo.ProductShapeExample.Criteria;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.util.base.MyBatisBaseDao;

/**		
 * <p>Title: ProductShapeDao </p>
 * <p>Description:成品菜形状Dao</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2015年12月16日 上午11:11:05	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月16日 上午11:11:05</p>
 * <p>修改备注：</p>
 */
@Repository
public class ProductShapeDao extends MyBatisBaseDao<ProductShape>
{
    @Autowired
    @Getter
    private ProductShapeMapper mapper;

    private static final Logger log = Logger.getLogger(ProductShapeDao.class);

    public List<ProductShape> findForList(ProductShape param, LimitPageDto limitPageDto)
    {
        if (param == null)
        {
            log.error("参数ProductShape为空");
        }
        ProductShapeExample example = new ProductShapeExample();
        Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(param.getId()))
        {
            criteria.andIdEqualTo(param.getId());
        }
        //颜色名称
        if (!StringUtils.isEmpty(param.getName()))
        {
            criteria.andNameLike("%" + param.getName() + "%");
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

    public ProductShape findById(String id)
    {
        return mapper.selectByPrimaryKey(id);
    }

    public void updateById(ProductShape param)
    {
        mapper.updateByPrimaryKeySelective(param);
    }

    public void insertBrand(ProductShape param)
    {
        mapper.insert(param);
    }

    /**     
     * update：一句话描述方法功能
     * @param productShape
     * @exception	
     * @author 刘博
     * @date 2015年12月17日 下午4:48:25	 
     */
    public void update(ProductShape productShape)
    {
        if (productShape != null && !StringUtils.isEmpty(productShape.getId()))
        {
            productShape.setLastUpdateTime(new Date());
            mapper.updateByPrimaryKeySelective(productShape);
        }
    }

    /**     
     * findByName：通过形状名称查找形状对象
     * @param taste
     * @return
     * @exception   
     * @author 刘博
     * @date 2015年12月22日 上午9:45:00   
     */
    public List<ProductShape> findByName(ProductShape productShape)
    {
        ProductShapeExample example = new ProductShapeExample();
        Criteria criteria = example.createCriteria();
        criteria.andStatEqualTo(CookbookFields.Enable);
        if (!StringUtils.isEmpty(productShape.getName()))
        {
            criteria.andNameEqualTo(productShape.getName());
        }
        return mapper.selectByExample(example);
    }
    
    /**     
     * findCount：查找数量
     * @param category
     * @return
     * @exception   
     * @author 刘博
     * @date 2015年12月22日 上午10:02:29  
     */
    public int findCount(ProductShape param)
    {
        if (param == null)
        {
            log.error("参数ProductShape为空");
        }
        ProductShapeExample example = new ProductShapeExample();
        Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(param.getId()))
        {
            criteria.andIdEqualTo(param.getId());
        }
        //颜色名称
        if (!StringUtils.isEmpty(param.getName()))
        {
            criteria.andNameLike("%" + param.getName() + "%");
        }
       
        criteria.andStatEqualTo(CookbookFields.Enable);
        return mapper.countByExample(example);
    }
}
