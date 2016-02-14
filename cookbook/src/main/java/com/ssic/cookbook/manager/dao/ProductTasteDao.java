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
import com.ssic.cookbook.manager.mapper.ProductTasteMapper;
import com.ssic.cookbook.manager.pojo.ProductTasteExample.Criteria;
import com.ssic.cookbook.manager.pojo.ProductColor;
import com.ssic.cookbook.manager.pojo.ProductColorExample;
import com.ssic.cookbook.manager.pojo.ProductStyle;
import com.ssic.cookbook.manager.pojo.ProductStyleExample;
import com.ssic.cookbook.manager.pojo.ProductTaste;
import com.ssic.cookbook.manager.pojo.ProductTasteExample;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.util.base.MyBatisBaseDao;

/**		
 * <p>Title: ProductTasteDao </p>
 * <p>Description:成品菜所属口味Dao</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2015年12月16日 上午11:08:09	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月16日 上午11:08:09</p>
 * <p>修改备注：</p>
 */
@Repository
public class ProductTasteDao extends MyBatisBaseDao<ProductTaste>
{
    @Autowired
    @Getter
    private ProductTasteMapper mapper;

    private static final Logger log = Logger.getLogger(ProductTasteDao.class);

    public List<ProductTaste> findForList(ProductTaste param, LimitPageDto limitPageDto)
    {
        if (param == null)
        {
            log.error("参数ProductTaste为空");
        }
        ProductTasteExample example = new ProductTasteExample();
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

    public ProductTaste findById(String id)
    {
        return mapper.selectByPrimaryKey(id);
    }

    public void updateById(ProductTaste param)
    {
        mapper.updateByPrimaryKeySelective(param);
    }

    public void insertBrand(ProductTaste param)
    {
        mapper.insert(param);
    }

    /**     
     * update：一句话描述方法功能
     * @param productTaste
     * @exception	
     * @author 刘博
     * @date 2015年12月17日 下午4:46:12	 
     */
    public void update(ProductTaste productTaste)
    {
        if (productTaste != null && !StringUtils.isEmpty(productTaste.getId()))
        {
            productTaste.setLastUpdateTime(new Date());
            mapper.updateByPrimaryKeySelective(productTaste);
        }

    }

    /**     
     * findByName：通过品味名称查找品味对象
     * @param taste
     * @return
     * @exception   
     * @author 刘博
     * @date 2015年12月22日 上午9:45:00   
     */
    public List<ProductTaste> findByName(ProductTaste taste)
    {
        ProductTasteExample example = new ProductTasteExample();
        Criteria criteria = example.createCriteria();
        criteria.andStatEqualTo(CookbookFields.Enable);
        if (!StringUtils.isEmpty(taste.getName()))
        {
            criteria.andNameEqualTo(taste.getName());
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
    public int findCount(ProductTaste param)
    {
        if (param == null)
        {
            log.error("参数ProductTaste为空");
        }
        ProductTasteExample example = new ProductTasteExample();
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
