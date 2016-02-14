package com.ssic.cookbook.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssic.cookbook.manager.dto.BrandDto;
import com.ssic.cookbook.manager.dto.MaterialDto;
import com.ssic.cookbook.manager.service.IBrandService;
import com.ssic.cookbook.manager.service.IMaterialService;

@Controller
@RequestMapping("/brandController")
public class BrandController {

	@Autowired
	private IMaterialService materialService;
	@Autowired
	private IBrandService brandService;
	
	//查询使用该品牌的原料
	@RequestMapping("/findMByBid")
	@ResponseBody
	public List<MaterialDto> findMByBid(String brandId){
		MaterialDto materialDto = new MaterialDto();
		materialDto.setBrandId(brandId);
		List<MaterialDto> list = materialService.findListByPage(materialDto);
		return list;
	}
	
	
	//判断品牌名称是否重复
	@RequestMapping("/hasBrandName")
	@ResponseBody
	public List<BrandDto> hasBrandName(String brandName){
		BrandDto brandDto = new BrandDto();
		brandDto.setName(brandName);
		List<BrandDto> list = brandService.findForList(brandDto);
		return list;
	}
	
}
