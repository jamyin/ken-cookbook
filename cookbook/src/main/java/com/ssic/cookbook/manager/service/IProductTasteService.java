/**
 * 
 */
package com.ssic.cookbook.manager.service;

import java.util.List;

import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.dto.ProductStyleDto;
import com.ssic.cookbook.manager.dto.ProductTasteDto;
import com.ssic.cookbook.manager.pojo.ProductTaste;

/**		
 * <p>Title: IProductTasteService </p>
 * <p>Description: 成品菜口味Service层实现类</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 刘博	
 * @date 2015年12月16日 下午4:02:39	
 * @version 1.0
 * <p>修改人：刘博</p>
 * <p>修改时间：2015年12月16日 下午4:02:39</p>
 * <p>修改备注：</p>
 */
public interface IProductTasteService
{
    /**     
     * findListByPage：查找所有的成品菜品味
     * @param ProductTaste
     * @param limitPageDto
     * @return
     * @exception   
     * @author 刘博
     * @date 2015年12月16日 下午3:09:22   
     */
    public List<ProductTasteDto> findListByPage(ProductTasteDto ProductTasteDto,LimitPageDto limitPageDto);

    /**     
     * findById：根据成品菜品味id查找对应的成品菜品味
     * @param productTasteId
     * @return
     * @exception   
     * @author 刘博
     * @date 2015年12月16日 下午3:09:22   
     */
    public ProductTasteDto findById(String productTasteId);
    
    /**     
     * add：一句话描述方法功能
     * @param productTasteDto
     * @exception   
     * @author 刘博
     * @date 2015年8月6日 下午4:27:36     
     */
    void add(ProductTasteDto productTasteDto);
    
    /**     
     * edit：一句话描述方法功能
     * @param productTasteDto
     * @exception   
     * @author 刘博
     * @date 2015年8月6日 下午4:27:36     
     */
    void edit(ProductTasteDto productTasteDto);
    
    /**     
     * add：一句话描述方法功能
     * @param productTasteDto
     * @exception   
     * @author 刘博
     * @date 2015年8月6日 下午4:27:36     
     */
    void delete(ProductTasteDto productTasteDto);
    
    /**     
     * findByName：通过品味名称查找成品菜-品味
     * @param name
     * @return
     * @exception   
     * @author 刘博
     * @date 2015年12月22日 上午9:30:34   
     */
    public ProductTasteDto findByName(String name);
    
    /**     
     * findCount：查找成品菜-品味数量
     * @param productTasteDto
     * @return
     * @exception   
     * @author 刘博
     * @date 2015年12月22日 上午10:01:34  
     */
    public int findCount(ProductTasteDto productTasteDto);
}
