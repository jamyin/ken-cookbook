/**
 * 
 */
package com.ssic.cookbook.manager.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssic.cookbook.admin.dao.DaoSupport;
import com.ssic.cookbook.admin.util.PageData;
import com.ssic.cookbook.manager.dao.CCProductDao;
import com.ssic.cookbook.manager.dao.CategoryClassDao;
import com.ssic.cookbook.manager.dao.FixingsResultDao;
import com.ssic.cookbook.manager.dao.FixingsResultTypeDao;
import com.ssic.cookbook.manager.dao.FixingsResultTypeProductDao;
import com.ssic.cookbook.manager.dao.IndependentFixingsDao;
import com.ssic.cookbook.manager.dto.CategoryClassDto;
import com.ssic.cookbook.manager.dto.CategoryClassProductDto;
import com.ssic.cookbook.manager.dto.FixingsResultDto;
import com.ssic.cookbook.manager.dto.FixingsResultTypeDto;
import com.ssic.cookbook.manager.dto.FixingsResultTypeProductDto;
import com.ssic.cookbook.manager.dto.FixingsVO;
import com.ssic.cookbook.manager.dto.IndependentFixingsDto;
import com.ssic.cookbook.manager.service.DIYBookService;
import com.ssic.cookbook.manager.util.DateToolKit;
import com.ssic.util.UUIDGenerator;

/**
 * @author wk.s
 *
 */
@Service
public class DIYBookMenuServiceImpl implements DIYBookService {
	
	@Autowired
	private DaoSupport dao;
	@Autowired
	private CCProductDao cCProductDao;
	@Autowired
	private IndependentFixingsDao independentFixingsDao;
	@Autowired
	private CategoryClassDao categoryClassDao;
	@Autowired
	private FixingsResultDao fixingsResultDao;
	@Autowired
	private FixingsResultTypeDao fixingsResultTypeDao;
	@Autowired
	private FixingsResultTypeProductDao fixingsResultTypeProductDao;
	
	public String add(IndependentFixingsDto independent, CategoryClassDto category, CategoryClassProductDto product, FixingsVO temp) {
		
		boolean reFlag = false;
		String resultId = new String();
		String[] bciArr = toStrArr(temp.getBcidsVO(),";");
		int bsize = bciArr.length;
		String[] pidArr = toStrArr(temp.getPidsVO(),",");
		String[] priceArr = toStrArr(temp.getPriceVO(), ";");
		String iid = UUIDGenerator.getUUID(); //自主配菜表数据id
		{
			independent.setId(iid);
			independent.setCreateTime(new Date());
			independent.setFixingsTime(DateToolKit.parseStr(independent.getFixingsTimeVO(), "yyyy-MM-dd"));
		}
		// 保存自主菜单数据
		boolean flag01 = independentFixingsDao.add(independent);
		if(!flag01) throw new RuntimeException("保存自主菜单数据异常");
		else{
			
			//---start circle for CategoryClass-----
			for(int iv02=0; iv02<bsize; iv02++){
				
				String cid = UUIDGenerator.getUUID(); // 品类表id
				{
					category.setId(cid);
					category.setBigCategoryId(bciArr[iv02]);
					category.setIndependentFixingsId(iid);
					category.setCreateTime(new Date());
					Double dPrice = Double.parseDouble(priceArr[iv02]);
					category.setCost(dPrice.longValue());
				}
				// 保存品类数据
				boolean flag02 = categoryClassDao.add(category);
				if(!flag02) throw new RuntimeException("保存品类数据异常");
				else{
					
					//---circle for category_class_product ----
					String[] pidArr_temp = toStrArr(pidArr[iv02], ";");
					int psize = pidArr_temp.length;
					for(int iv03=0; iv03<psize; iv03++){
						
						String ccpId = UUIDGenerator.getUUID(); // 品类表id
						{
							product.setId(ccpId);
							product.setProductId(pidArr_temp[iv03]);
							product.setCategoryClassId(cid);
							product.setCreateTime(new Date());
						}
						// 保存成品菜数据
						boolean flag03 = cCProductDao.add(product);
						if(!flag03){
							reFlag = false;
							throw new RuntimeException("保存成品菜数据异常");
						} 
						else{
							reFlag = true;
						}
					}
					//-----end circle for category_class_product -----
				}
			}
			//----end circle for CategoryClass----
		}
		
		if(reFlag){
			FixingsResultDto fixingsResult = new FixingsResultDto();
			String frid = UUIDGenerator.getUUID();
			resultId = frid;
			fixingsResult.setId(frid);
			fixingsResult.setFixingsId(independent.getFixingsMasterId());
			fixingsResult.setFixingsType(1); // 自主配菜
			boolean flagX01 = fixingsResultDao.add(fixingsResult);
			if(!flagX01){
				reFlag = false;
				throw new RuntimeException("保存配菜结果数据异常");
			} 
			else{
				
				//--circle for bigcategoryId --
				for(int iv04=0; iv04<bsize; iv04++){
					
					String[] footTypeArr = toStrArr(temp.getFoodType(), ",");
					int ftsize = footTypeArr.length;
					for(int iv06=0; iv06<ftsize; iv06++){
						
						String frtid = UUIDGenerator.getUUID();
						FixingsResultTypeDto dto = new FixingsResultTypeDto();
						{
							dto.setId(frtid);
							dto.setMealType(Integer.parseInt(footTypeArr[iv06]));
							dto.setFixingsResultId(frid);
							dto.setProductCategoryId(bciArr[iv04]);
							dto.setFixingsTime(DateToolKit.parseStr(independent.getFixingsTimeVO(), "yyyy-MM-dd"));
						}
						boolean flagX02 = fixingsResultTypeDao.add(dto);
						if(!flagX02){
							reFlag = false;
							throw new RuntimeException("保存配菜结果类型数据异常");
						}
						else{
							
							//---circle for productId---
							String[] pidArr_temp = toStrArr(pidArr[iv04], ";");
							int psize = pidArr_temp.length;
							for(int iv05=0; iv05<psize; iv05++){
								String frtpId = UUIDGenerator.getUUID();
								FixingsResultTypeProductDto frtpDto = new FixingsResultTypeProductDto();
								{
									frtpDto.setId(frtpId);
									frtpDto.setProductId(pidArr_temp[iv05]);
									frtpDto.setResultTypeId(frtid);
								}
								boolean flagX03 = fixingsResultTypeProductDao.add(frtpDto);
								if(!flagX03){
									reFlag = false;
								}
							}
							//---end circle for productId---
						}
						
					}
				}
				//--- end circle for bigcategoryId---
			}
		}
		return resultId;
	}
	
	/**
	 * 切割字符串
	 * @param str
	 * @param regex
	 * @return
	 */
	public String[] toStrArr(String str, String regex){
		
		str = str.substring(0, str.length()-1);
		String[] strArr = null;
		strArr = str.split(regex);
		return strArr;
	}
	
	 /**
	  * @author pengcheng.yang
	  * @param pd
	  * @return
	  * @throws Exception
	  */
	 public List<PageData> QueryFixingsResult(PageData pd) throws Exception{
		 return (List<PageData>) dao.QueryFixingsResult("FixingsResultTypeExMapper.QueryFixingsResult", pd);
	 }
}
