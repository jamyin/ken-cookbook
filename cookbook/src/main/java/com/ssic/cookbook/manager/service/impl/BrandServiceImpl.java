package com.ssic.cookbook.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssic.cookbook.manager.dao.BrandDao;
import com.ssic.cookbook.manager.dto.BrandDto;
import com.ssic.cookbook.manager.dto.LimitPageDto;
import com.ssic.cookbook.manager.pojo.Brand;
import com.ssic.cookbook.manager.service.IBrandService;
import com.ssic.util.BeanUtils;

@Service
public class BrandServiceImpl implements IBrandService{
	@Autowired
	private BrandDao brandDao;

	public List<BrandDto> findForList(BrandDto brandDto) {
		// TODO Auto-generated method stub
		Brand brand = new Brand();
		BeanUtils.copyProperties(brandDto, brand);
		List<Brand> list = brandDao.findForList(brand);
		List<BrandDto> listdto = BeanUtils.createBeanListByTarget(list, BrandDto.class);
		return listdto;
	}

	public BrandDto findById(String id) {
		// TODO Auto-generated method stub
		Brand brand= brandDao.findById(id);
		BrandDto brandDto = new BrandDto();
		BeanUtils.copyProperties(brand, brandDto);
		return brandDto;
	}

	public void updateBS(BrandDto brandDto) {
		// TODO Auto-generated method stub
		Brand brand = new Brand();
		BeanUtils.copyProperties(brandDto, brand);
		brandDao.updateById(brand);
	}

	public void insertBS(BrandDto brandDto) {
		// TODO Auto-generated method stub
		Brand brand = new Brand();
		BeanUtils.copyProperties(brandDto, brand);
		brandDao.insertBrand(brand);
	}

	public void deleteBS(String id) {
		// TODO Auto-generated method stub
		Brand brand = new Brand();
		brand.setId(id);
		brandDao.deleteBS(brand);
	}

	public int findForCount(BrandDto brandDto) {
		// TODO Auto-generated method stub
		Brand brand = new Brand();
		BeanUtils.copyProperties(brandDto, brand);
		return brandDao.findForCount(brand);
	}

	public List<BrandDto> findForList(BrandDto brandDto,
			LimitPageDto limitPageDto) {
		// TODO Auto-generated method stub
		Brand brand = new Brand();
		BeanUtils.copyProperties(brandDto, brand);
	    List<Brand> list =	brandDao.findForList(brand, limitPageDto);
		List<BrandDto> listdto = BeanUtils.createBeanListByTarget(list, BrandDto.class);
		return listdto;
	}

}
