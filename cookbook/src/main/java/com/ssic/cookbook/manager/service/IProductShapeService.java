/**
 * 
 */
package com.ssic.cookbook.manager.service;

import java.util.List;

import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.dto.ProductCuisineDto;
import com.ssic.cookbook.manager.dto.ProductDto;
import com.ssic.cookbook.manager.dto.ProductShapeDto;

/**		
 * <p>Title: IProductShapeService </p>
 * <p>Description: 成品菜形状Service层</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2015年12月16日 下午3:50:53	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月16日 下午3:50:53</p>
 * <p>修改备注：</p>
 */
public interface IProductShapeService
{
    /**     
     * findListByPage：查找所有的成品菜形状
     * @param productShapeDto
     * @param limitPageDto
     * @return
     * @exception   
     * @author 刘博
     * @date 2015年12月16日 下午3:09:22   
     */
    public List<ProductShapeDto> findListByPage(ProductShapeDto productShapeDto,LimitPageDto limitPageDto);

    /**     
     * findById：根据成品菜形状id查找对应的成品菜形状
     * @param ProductShapeId
     * @return
     * @exception   
     * @author 刘博
     * @date 2015年12月16日 下午3:09:22   
     */
    public ProductShapeDto findById(String productShapeId);
    
    /**     
     * add：一句话描述方法功能
     * @param productShapeDto
     * @exception   
     * @author 刘博
     * @date 2015年8月6日 下午4:27:36     
     */
    void add(ProductShapeDto productShapeDto);
    
    /**     
     * edit：一句话描述方法功能
     * @param productShapeDto
     * @exception   
     * @author 刘博
     * @date 2015年8月6日 下午4:27:36     
     */
    void edit(ProductShapeDto productShapeDto);
    
    /**     
     * delete：一句话描述方法功能
     * @param productShapeDto
     * @exception   
     * @author 刘博
     * @date 2015年8月6日 下午4:27:36     
     */
    void delete(ProductShapeDto productShapeDto);
    
    /**     
     * findByName：通过形状名称查找成品菜-形状
     * @param name
     * @return
     * @exception   
     * @author 刘博
     * @date 2015年12月22日 上午9:30:34   
     */
    public ProductShapeDto findByName(String name);
    
    /**     
     * findCount：查找成品菜-形状数量
     * @param productShapeDto
     * @return
     * @exception   
     * @author 刘博
     * @date 2015年12月22日 上午10:01:34  
     */
    public int findCount(ProductShapeDto productShapeDto);
}
