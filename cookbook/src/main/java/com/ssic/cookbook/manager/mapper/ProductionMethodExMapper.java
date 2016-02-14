package com.ssic.cookbook.manager.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssic.cookbook.manager.dto.MaterialDto;
import com.ssic.cookbook.manager.dto.ProductionMethodDto;
import com.ssic.cookbook.manager.pojo.ProductionMethod;


/**
 * 		
 * <p>Title: ProductionMethodExMapper </p>
 * <p>Description: 类描述</p>
 * <p>Copyright (c) 2015 </p>
 * <p>Company: 上海天坊信息科技有限公司</p>
 * @author 高猛华	
 * @date 2015年12月22日 下午3:43:06	
 * @version 1.0
 * <p>修改人：Administrator</p>
 * <p>修改时间：2015年12月22日 下午3:43:06</p>
 * <p>修改备注：</p>
 */
public interface ProductionMethodExMapper {

    List<ProductionMethodDto> selectByExample(@Param("ProductionMethodDto") ProductionMethodDto productionMethodDto);

   

    ProductionMethod findProductionMethodById(@Param("id")  String id);



    void deleteByPrimaryKey(@Param("ProductionMethodDto") ProductionMethodDto pdto);

}