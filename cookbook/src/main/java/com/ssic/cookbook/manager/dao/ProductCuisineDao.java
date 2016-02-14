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
import com.ssic.cookbook.manager.mapper.ProductCuisineMapper;
import com.ssic.cookbook.manager.pojo.ProductColor;
import com.ssic.cookbook.manager.pojo.ProductColorExample;
import com.ssic.cookbook.manager.pojo.ProductCuisine;
import com.ssic.cookbook.manager.pojo.ProductCuisineExample;
import com.ssic.cookbook.manager.pojo.ProductShape;
import com.ssic.cookbook.manager.pojo.ProductShapeExample;
import com.ssic.cookbook.manager.pojo.ProductCuisineExample.Criteria;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.util.base.MyBatisBaseDao;

/**		
 * <p>Title: ProductCuisineDao </p>
 * <p>Description: 成品菜所属烹饪方式Dao</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2015年12月16日 上午11:19:20	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月16日 上午11:19:20</p>
 * <p>修改备注：</p>
 */
@Repository
public class ProductCuisineDao extends MyBatisBaseDao<ProductCuisine>
{
    @Autowired
    @Getter
    private ProductCuisineMapper mapper;

    private static final Logger log = Logger.getLogger(ProductCuisine.class);

    public List<ProductCuisine> findForList(ProductCuisine param, LimitPageDto limitPageDto)
    {
        if (param == null)
        {
            log.error("参数ProductCuisine为空");
        }
        ProductCuisineExample example = new ProductCuisineExample();
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

    public ProductCuisine findById(String id)
    {
        return mapper.selectByPrimaryKey(id);
    }

    public void updateById(ProductCuisine param)
    {
        mapper.updateByPrimaryKeySelective(param);
    }

    public void insertBrand(ProductCuisine param)
    {
        mapper.insert(param);
    }

    /**     
     * update：一句话描述方法功能
     * @param productCuisine
     * @exception	
     * @author 刘博
     * @date 2015年12月17日 下午4:49:51	 
     */
    public void update(ProductCuisine productCuisine)
    {
        if (productCuisine != null && !StringUtils.isEmpty(productCuisine.getId()))
        {
            productCuisine.setLastUpdateTime(new Date());
            mapper.updateByPrimaryKeySelective(productCuisine);
        }

    }

    /**     
     * findByName：通过烹饪名称查找烹饪对象
     * @param productCuisine
     * @return
     * @exception   
     * @author 刘博
     * @date 2015年12月22日 上午9:45:00   
     */
    public List<ProductCuisine> findByName(ProductCuisine productCuisine)
    {
        ProductCuisineExample example = new ProductCuisineExample();
        Criteria criteria = example.createCriteria();
        criteria.andStatEqualTo(CookbookFields.Enable);
        if (!StringUtils.isEmpty(productCuisine.getName()))
        {
            criteria.andNameEqualTo(productCuisine.getName());
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
    public int findCount(ProductCuisine param)
    {
        if (param == null)
        {
            log.error("参数ProductCuisine为空");
        }
        ProductCuisineExample example = new ProductCuisineExample();
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
