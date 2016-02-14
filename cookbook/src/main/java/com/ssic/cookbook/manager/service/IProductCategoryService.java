/**
 * 
 */
package com.ssic.cookbook.manager.service;

import java.util.List;

import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.dto.ProductCategoryDto;
import com.ssic.cookbook.manager.dto.ProductColorDto;

/**		
 * <p>Title: IProductCategoryService </p>
 * <p>Description: 成品菜所属类别Service层</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2015年12月16日 下午4:16:41	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月16日 下午4:16:41</p>
 * <p>修改备注：</p>
 */
public interface IProductCategoryService
{
    /**     
     * findListByPage：查找所有的成品菜类别
     * @param productCategoryDto
     * @param limitPageDto
     * @return
     * @exception   
     * @author 刘博
     * @date 2015年12月16日 下午3:09:22   
     */
    public List<ProductCategoryDto> findListByPage(ProductCategoryDto productCategoryDto,
        LimitPageDto limitPageDto);

    /**     
     * findById：根据成品菜类别id查找对应的成品菜类别
     * @param productCategoryId
     * @return
     * @exception   
     * @author 刘博
     * @date 2015年12月16日 下午3:09:22   
     */
    public ProductCategoryDto findById(String productCategoryId);
    
    /**     
     * add：一句话描述方法功能
     * @param productCategoryDto
     * @exception   
     * @author 刘博
     * @date 2015年8月6日 下午4:27:36     
     */
    void add(ProductCategoryDto productCategoryDto);
    
    /**     
     * edit：一句话描述方法功能
     * @param productCategoryDto
     * @exception   
     * @author 刘博
     * @date 2015年8月6日 下午4:27:36     
     */
    void edit(ProductCategoryDto productCategoryDto);
    
    /**     
     * delete：一句话描述方法功能
     * @param productCategoryDto
     * @exception   
     * @author 刘博
     * @date 2015年8月6日 下午4:27:36     
     */
    void delete(ProductCategoryDto productCategoryDto);

    
    /**     
     * findByName：通过类别名称查找成品菜类别
     * @param name
     * @return
     * @exception	
     * @author 刘博
     * @date 2015年12月22日 上午9:30:34	 
     */
    public ProductCategoryDto findByName(String name);

    
    /**     
     * findCount：查找成品菜-类别数量
     * @param productCategoryDto
     * @return
     * @exception	
     * @author 刘博
     * @date 2015年12月22日 上午10:01:34	 
     */
    public int findCount(ProductCategoryDto productCategoryDto);
}
