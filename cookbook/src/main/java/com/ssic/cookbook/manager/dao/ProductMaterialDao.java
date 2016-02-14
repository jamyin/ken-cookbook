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
import com.ssic.cookbook.manager.mapper.ProductMaterialMapper;
import com.ssic.cookbook.manager.pojo.ProductMaterialExample.Criteria;
import com.ssic.cookbook.manager.pojo.ProductColor;
import com.ssic.cookbook.manager.pojo.ProductColorExample;
import com.ssic.cookbook.manager.pojo.ProductMaterial;
import com.ssic.cookbook.manager.pojo.ProductMaterialExample;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.util.base.MyBatisBaseDao;

/**		
 * <p>Title: ProductMaterialDao </p>
 * <p>Description:成品菜-原料Dao</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2015年12月24日 下午3:31:43	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月24日 下午3:31:43</p>
 * <p>修改备注：</p>
 */
@Repository
public class ProductMaterialDao extends MyBatisBaseDao<ProductMaterial>
{
    @Autowired
    @Getter
    private ProductMaterialMapper mapper;

    private static final Logger log = Logger.getLogger(ProductMaterialDao.class);

    public List<ProductMaterial> findForList(ProductMaterial param, LimitPageDto limitPageDto)
    {
        if (param == null)
        {
            log.error("参数ProductColor为空");
        }
        ProductMaterialExample example = new ProductMaterialExample();
        Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(param.getId()))
        {
            criteria.andIdEqualTo(param.getId());
        }

        if (!StringUtils.isEmpty(param.getProductId()))
        {
            criteria.andProductIdEqualTo(param.getProductId());
        }
        if (!StringUtils.isEmpty(param.getMaterialId()))
        {
            criteria.andMaterialIdEqualTo(param.getMaterialId());
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
     * findCount：查找数量
     * @param param
     * @return
     * @exception   
     * @author 刘博
     * @date 2015年12月22日 上午10:02:29  
     */
    public int findCount(ProductMaterial param)
    {
        if (param == null)
        {
            log.error("参数ProductMaterial为空");
        }
        ProductMaterialExample example = new ProductMaterialExample();
        Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(param.getId()))
        {
            criteria.andIdEqualTo(param.getId());
        }
        if (!StringUtils.isEmpty(param.getProductId()))
        {
            criteria.andProductIdEqualTo(param.getProductId());
        }
        if (!StringUtils.isEmpty(param.getMaterialId()))
        {
            criteria.andMaterialIdEqualTo(param.getMaterialId());
        }
        criteria.andStatEqualTo(CookbookFields.Enable);
        return mapper.countByExample(example);
    }

    public void insertProductMaterial(ProductMaterial param)
    {
        mapper.insert(param);
    }

    /**     
     * update：一句话描述方法功能
     * @param productMaterial
     * @exception   
     * @author 刘博
     * @date 2015年12月17日 下午4:50:39   
     */
    public void update(ProductMaterial productMaterial)
    {
        if (productMaterial != null && !StringUtils.isEmpty(productMaterial.getId()))
        {
            productMaterial.setLastUpdateTime(new Date());
            mapper.updateByPrimaryKeySelective(productMaterial);
        }
    }
}
