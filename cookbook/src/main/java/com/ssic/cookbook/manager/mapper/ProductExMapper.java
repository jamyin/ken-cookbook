package com.ssic.cookbook.manager.mapper;

import com.ssic.cookbook.manager.dto.ProductNexusDto;
import com.ssic.cookbook.manager.pojo.Product;
import com.ssic.cookbook.manager.pojo.ProductExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ProductExMapper {

	public List<ProductNexusDto> findProductNexus(@Param("productNexusDto") ProductNexusDto productNexusDto);
}