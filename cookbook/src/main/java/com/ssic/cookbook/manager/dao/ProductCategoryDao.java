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
import com.ssic.cookbook.manager.mapper.ProductCategoryMapper;
import com.ssic.cookbook.manager.pojo.ProductCategory;
import com.ssic.cookbook.manager.pojo.ProductCategoryExample;
import com.ssic.cookbook.manager.pojo.ProductCategoryExample.Criteria;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.util.base.MyBatisBaseDao;


/**		
 * <p>Title: ProductCategoryDao </p>
 * <p>Description: 成品菜所属类别Dao</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2015年12月16日 上午11:17:26	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月16日 上午11:17:26</p>
 * <p>修改备注：</p>
 */
@Repository
public class ProductCategoryDao extends MyBatisBaseDao<ProductCategory>
{
    @Autowired
    @Getter
    private ProductCategoryMapper mapper;

    private static final Logger log = Logger.getLogger(ProductTasteDao.class);

    public List<ProductCategory> findForList(ProductCategory param, LimitPageDto limitPageDto)
    {
        if (param == null)
        {
            log.error("参数ProductCategory为空");
        }
        ProductCategoryExample example = new ProductCategoryExample();
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

    public ProductCategory findById(String id)
    {
        return mapper.selectByPrimaryKey(id);
    }

    public void updateById(ProductCategory param)
    {
        mapper.updateByPrimaryKeySelective(param);
    }

    public void insertBrand(ProductCategory param)
    {
        mapper.insert(param);
    }

    /**     
     * update：一句话描述方法功能
     * @param productCategory
     * @exception	
     * @author 刘博
     * @date 2015年12月17日 下午4:51:34	 
     */
    public void update(ProductCategory productCategory)
    {
        if (productCategory != null && !StringUtils.isEmpty(productCategory.getId()))
        {
            productCategory.setLastUpdateTime(new Date());
            mapper.updateByPrimaryKeySelective(productCategory);
        }

    }

    /**     
     * findByName：通过成品菜类别名称查找类别
     * @param productCategory
     * @return
     * @exception   
     * @author 刘博
     * @date 2015年10月29日 上午11:50:25  
     */
    public List<ProductCategory> findByName(ProductCategory productCategory)
    {
        ProductCategoryExample example = new ProductCategoryExample();
        Criteria criteria = example.createCriteria();
        criteria.andStatEqualTo(CookbookFields.Enable);
        if (!StringUtils.isEmpty(productCategory.getName()))
        {
            criteria.andNameEqualTo(productCategory.getName());
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
    public int findCount(ProductCategory param)
    {
        if (param == null)
        {
            log.error("参数ProductCategory为空");
        }
        ProductCategoryExample example = new ProductCategoryExample();
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
