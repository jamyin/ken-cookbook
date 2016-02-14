/**
 * 
 */
package com.ssic.cookbook.manager.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssic.cookbook.admin.entity.Page;
import com.ssic.cookbook.manager.dao.FixingsResultDao;
import com.ssic.cookbook.manager.dao.FixingsResultTypeDao;
import com.ssic.cookbook.manager.dao.FixingsResultTypeProductDao;
import com.ssic.cookbook.manager.dto.CategoryDto;
import com.ssic.cookbook.manager.dto.ColorDto;
import com.ssic.cookbook.manager.dto.CuisineDto;
import com.ssic.cookbook.manager.dto.FixingsResultDto;
import com.ssic.cookbook.manager.dto.FixingsResultTypeDto;
import com.ssic.cookbook.manager.dto.FixingsResultTypeProductDto;
import com.ssic.cookbook.manager.dto.IntelligentFixingsDto;
import com.ssic.cookbook.manager.dto.NutritionDto;
import com.ssic.cookbook.manager.dto.ProductDto;
import com.ssic.cookbook.manager.dto.ProductNexusDto;
import com.ssic.cookbook.manager.dto.ProductVo;
import com.ssic.cookbook.manager.dto.ShapeDto;
import com.ssic.cookbook.manager.dto.SmartMenuParams;
import com.ssic.cookbook.manager.dto.SmartNutritionDto;
import com.ssic.cookbook.manager.dto.StyleDto;
import com.ssic.cookbook.manager.dto.TasteDto;
import com.ssic.cookbook.manager.service.IIntelligentFixingsService;
import com.ssic.cookbook.manager.service.IProductService;
import com.ssic.cookbook.manager.service.MenuGenService;
import com.ssic.cookbook.manager.service.ProductNexusService;
import com.ssic.cookbook.manager.util.BeanUtils;
import com.ssic.cookbook.manager.util.DateUtils;
import com.ssic.util.UUIDGenerator;

/**
 * <br><b>一点感想</b></br>
 * 写这个方法走了不少弯路，耗费了不少精力，但最终做出来还是开心的，就像看着自己的孩子，再丑，也喜欢，哈哈，
 * 扯远了！这个方法实际上还有不少地方可以优化。可以跑的很快，比如统一dto后可以省略不同对象之间dto属性复制，
 * 要知道属性复制也是要循环的，而进行集合间的对象复制就更慢了，所以，如果要优化，这个算一个！
 * 还有一点也可以优化，那就是数据库查询，这个方法里面有一些循环查询，bullshit，如果可以用几个in条件查询多好，是不是会快很多？
 * 所以，这个也算一个待优化点！
 * @author wk.s
 * 智能配菜算法
 */
@Service
public class MenuGenServiceImpl implements MenuGenService {

	@Autowired
	private IProductService productService;
	@Autowired
	ProductNexusService productNexusService;
	@Autowired
	private IIntelligentFixingsService iIntelligentFixingsService;
	@Autowired
	private FixingsResultDao fixingsResultDao;
	@Autowired
	private FixingsResultTypeDao fixingsResultTypeDao;
	@Autowired
	private FixingsResultTypeProductDao fixingsResultTypeProductDao;
	
	/**
	 * 过滤
	 * @param dto
	 * @return
	 */
	public List<List<ProductVo>> ruleFilter(IntelligentFixingsDto dto, List<ProductDto> plst) {
		
		List<List<ProductVo>> result = new ArrayList<List<ProductVo>>();
		List<CategoryDto> categories = dto.getCategory(); // 因为这里是只有一个成品菜大类的情况，所以categories里面实际上只有一个值
		int catesize = categories.size();

		// 只有一个成品菜大类的情况
		if (catesize == 1) {

			List<ProductVo> relst = filterSCProduct(dto, plst, categories.get(0));
			if ((relst != null) && (relst.size() > 0)) {
				result.add(relst);
			} else {
				// 配菜失败
				result = null;
			}
		}

		if (catesize > 1) {
			boolean flag = false;
			for (CategoryDto categoryDto : categories) {
				List<ProductVo> temp = filterSCProduct(dto, plst, categoryDto);
				if (temp != null) {
					result.add(temp);
					flag = true;
				}
			}
			if (!flag) {
				// 配菜失败
				result = null;
			}
		}
		return result;
	}
	
	/**
	 * 某一大类成品菜过滤
	 * @param ifDto 只抽取其中的配置条件
	 * @param plst 成品菜列表
	 * @param category 大类，及其相关的一些参数
	 * @return
	 */
	public List<ProductVo> filterSCProduct(IntelligentFixingsDto ifDto, List<ProductDto> plst, CategoryDto category){
		
		List<ProductVo> result = null;
		List<CategoryDto> categories = new ArrayList<CategoryDto>(1);
		categories.add(category);
		// 1.根据类型过滤
		List<ProductDto> cateLst = isTyped(plst, categories);
		
		// A。此处进行必须条件过滤 
		// 1.1  根据是否包含敏感食材过滤
		// 1.2过滤是否敏感食材以及营养含量
		//cateLst = isSenAndNut(cateLst, ifDto);
		cateLst = isNut(cateLst, ifDto);
		// B。下面进行非必须条件过滤
		int categsize = cateLst.size();
		Long count = category.getCategoryCount();
		if (categsize < count) {
			// 配菜失败
		} else {

			List<ProductVo> compLst = new ArrayList<ProductVo>(plst.size()); // 累加保存成品菜经过的规则过滤次数
			
			for (ProductDto productDto2 : cateLst) {
				ProductVo vo = new ProductVo();
				BeanUtils.copyProperties(productDto2, vo);
				vo.setCount(1);
				compLst.add(vo);
			}

			// 2.0 根据烹饪方式过滤
			List<ProductDto> cuisLst = isCuisined(plst, ifDto.getCuisine());
			if (cuisLst.size() >= count) {
				for (ProductDto pDto2 : cuisLst) {
					for (ProductVo vo : compLst) {
						if (pDto2.getId().equals(vo.getId())) {
							Integer temp = vo.getCount();
							temp++;
							vo.setCount(temp);
						}
					}
				}
			}

			// 3.0根据口味进行过滤
			List<ProductDto> tasteLst = isTasted(plst, ifDto.getTaste());
			if (tasteLst.size() >= count) {
				for (ProductDto pDto2 : tasteLst) {
					for (ProductVo vo : compLst) {
						if (pDto2.getId().equals(vo.getId())) {
							Integer temp = vo.getCount();
							temp++;
							vo.setCount(temp);
						}
					}
				}
			}

			// 4.0根据颜色进行过滤
			List<ProductDto> colorLst = isColored(plst, ifDto.getColor());
			if (colorLst.size() >= count) {
				for (ProductDto pDto2 : colorLst) {
					for (ProductVo vo : compLst) {
						if (pDto2.getId().equals(vo.getId())) {
							Integer temp = vo.getCount();
							temp++;
							vo.setCount(temp);
						}
					}
				}
			}

			// 5.0根据菜系进行过滤
			List<ProductDto> styleLst = isStyled(plst, ifDto.getStyle());
			if (styleLst.size() >= count) {
				for (ProductDto pDto2 : styleLst) {
					for (ProductVo vo : compLst) {
						if (pDto2.getId().equals(vo.getId())) {
							Integer temp = vo.getCount();
							temp++;
							vo.setCount(temp);
						}
					}
				}
			}

			// 6.0根据形状进行过滤
			List<ProductDto> shapeLst = isShaped(plst, ifDto.getShape());
			if (shapeLst.size() >= count) {
				for (ProductDto pDto2 : shapeLst) {
					for (ProductVo vo : compLst) {
						if (pDto2.getId().equals(vo.getId())) {
							Integer temp = vo.getCount();
							temp++;
							vo.setCount(temp);
						}
					}
				}
			}
			
			// 对过滤后的结果集进行排序(倒叙)
			Collections.sort(compLst);
			Collections.reverse(compLst);
			result = compLst;
		}
		return result;
	}
	
	/**
	 * 根据大类过滤成品菜
	 * @param plst
	 * @param clst
	 * @return
	 */
	public List<ProductDto> isTyped(List<ProductDto> plst, List<CategoryDto> clst){
		
		List<ProductDto> relst = new ArrayList<ProductDto>();
		Set<String> types = new HashSet<String>();
		for (CategoryDto type : clst) {
			types.add(type.getCategoryId());
		}
		for (ProductDto pdto : plst) {
			if(types.contains(pdto.getProductCategoryId())){
				relst.add(pdto);
			}
		}
		return relst;
	}
	
	/**
	 * 根据菜系过滤
	 * @param plst
	 * @param slst
	 * @return
	 */
	public List<ProductDto> isStyled(List<ProductDto> plst, List<StyleDto> slst){
		
		Set<String> styles = new HashSet<String>();
		List<ProductDto> relst = new ArrayList<ProductDto>();
	
		for (StyleDto style : slst) {
			styles.add(style.getStyleId());
		}
		
		for (ProductDto pdto : plst) {
			if(styles.contains(pdto.getProductStyleId())){
				relst.add(pdto);
			}
		}
		return relst;
	}
	
	
	/**
	 * 根据烹饪方式进行过滤
	 * @param plst
	 * @param cdtos
	 * @return
	 */
	public List<ProductDto> isCuisined(List<ProductDto> plst, List<CuisineDto> cdtos){
		
		Set<String> cuisines = new HashSet<String>();
		List<ProductDto> relst = new ArrayList<ProductDto>();
		
		for (CuisineDto cdto : cdtos) {
			cuisines.add(cdto.getCuisineId());
		}
		
		for (ProductDto pdto : plst) {
			if(cuisines.contains(pdto.getProductCuisineId())){
				relst.add(pdto);
			}
		}
		return relst;
	}
	
	/**
	 * 根据口味进行过滤
	 * @param plst
	 * @param tdtos
	 * @return
	 */
	public List<ProductDto> isTasted(List<ProductDto> plst, List<TasteDto> tdtos){
		
		Set<String> tastes = new HashSet<String>();
		List<ProductDto> relst = new ArrayList<ProductDto>();
		
		for (TasteDto tdto : tdtos) {
			tastes.add(tdto.getTasteId());
		}
		
		for (ProductDto pdto : plst) {
			if(tastes.contains(pdto.getProductTasteId())){
				relst.add(pdto);
			}
		}
		return relst;
	}
	
	/**
	 * 根据颜色进行过滤
	 * @param plst
	 * @param cdtos
	 * @return
	 */
	public List<ProductDto> isColored(List<ProductDto> plst, List<ColorDto> cdtos){
		
		Set<String> colors = new HashSet<String>();
		List<ProductDto> relst = new ArrayList<ProductDto>();
		
		for (ColorDto cdto : cdtos) {
			colors.add(cdto.getColorId());
		}
		
		for (ProductDto pdto : plst) {
			if(colors.contains(pdto.getProductColorId())){
				relst.add(pdto);
			}
		}
		return relst;
	}
	
	/**
	 * 根据形状进行过滤
	 * @param plst
	 * @param sdtos
	 * @return
	 */
	public List<ProductDto> isShaped(List<ProductDto> plst, List<ShapeDto> sdtos) {

		Set<String> shapes = new HashSet<String>();
		List<ProductDto> relst = new ArrayList<ProductDto>();

		for (ShapeDto sdto : sdtos) {
			shapes.add(sdto.getShapeId());
		}

		for (ProductDto pdto : plst) {
			if (shapes.contains(pdto.getProductShapeId())) {
				relst.add(pdto);
			}
		}
		return relst;
	}
	
	/**
	 * TODO 有待改进、优化
	 * 根据价格过滤
	 * @param plst
	 * @param ifdto
	 * @return
	 */
	public List<ProductDto> isCost(List<ProductDto> plst, IntelligentFixingsDto ifdto){
		
		List<ProductDto> result = new ArrayList<ProductDto>();
		Long sum = 0l; // 总数量
		List<CategoryDto> clst = ifdto.getCategory();
		Long totolCost = ifdto.getTotalCost(); // 总成本
		String totolCostOper = ifdto.getTotalCostOperator();
		Long fixedPrice = ifdto.getTotalFixedPrice(); // 总定价
		String fixedPriceOper = ifdto.getTotalFixedPriceOperator();
		int clstSize = clst.size();
		if(clstSize == 1){
			sum = clst.get(0).getCategoryCount();
		}
		
		for (ProductDto pdto : plst) {
			
			boolean flag01 = false;
			boolean flag02 = false;
			Double perTotalCost = pdto.getSingleCost()*sum;
			Long perFixedPrice = pdto.getEachPricing()*sum;
			if(totolCostOper.equals("<")){
				if(perTotalCost < totolCost){
					flag01 = true;
				}
			}
			if(totolCostOper.equals(">")){
				if(perTotalCost > totolCost){
					flag01 = true;
				}
			}
			if(totolCostOper.equals("=")){
				if(perTotalCost == Double.parseDouble(totolCost+"")){
					flag01 = true;
				}
			}
			if(totolCostOper.equals("<=")){
				if(perTotalCost <= totolCost){
					flag01 = true;
				}
			}
			if(totolCostOper.equals(">=")){
				if(perTotalCost >= totolCost){
					flag01 = true;
				}
			}
			if(fixedPriceOper.equals("<")){
				if(fixedPrice < perFixedPrice){
					flag02 = true;
				}
			}
			if(fixedPriceOper.equals(">")){
				if(fixedPrice > perFixedPrice){
					flag02 = true;
				}
			}
			if(fixedPriceOper.equals("=")){
				if(fixedPrice == perFixedPrice){
					flag02 = true;
				}
			}
			if(fixedPriceOper.equals("<=")){
				if(fixedPrice <= perFixedPrice){
					flag02 = true;
				}
			}
			if(fixedPriceOper.equals(">=")){
				if(fixedPrice >= perFixedPrice){
					flag02 = true;
				}
			}
			if(flag01 && flag02){
				result.add(pdto);
			}
		}
		return result;
	}
	
	/**
	 * 根据是否敏感食材和营养含量尽心过滤
	 * <br>先进行敏感食材过滤
	 * <br>再进行营养含量过滤
	 * @param pros
	 * @param nutritions
	 * @return
	 */
	@Deprecated
	public List<ProductDto> isSenAndNut(List<ProductDto> pros, IntelligentFixingsDto ifDto){
		
		List<SmartNutritionDto> nutritions = ifDto.getNutrition();
		int nutrSize = nutritions.size();
		Integer senStatus = ifDto.getIsSensitiveIngredients();
		List<ProductDto> reLst = new ArrayList<ProductDto>();
		
		// 这一块使用in(id1,id2,id3,...)效率会更高
		for (ProductDto proDto : pros) {
			ProductNexusDto productNexusDto = new ProductNexusDto();
			productNexusDto.setId(proDto.getId());
			productNexusDto = productNexusService.finProductNexusByProductId(productNexusDto);
			//非空验证
			List<NutritionDto> nutLst = productNexusDto.getNutritionDtoList();
			if((nutLst != null) && (nutLst.size() != 0)){ // 由于业务的具体实现，这里通过验证List<NutritionDto>非空来判断对象非空
				
				Integer senStatusX02 = productNexusDto.getIsSensitiveMaterial();
				if(senStatusX02 == senStatus){
					
					// 验证营养含量
					int nutSize = nutLst.size();
					if(nutSize >= nutrSize){
						
						Map<String, Double> nuts = new HashMap<String, Double>(nutSize);
						for (NutritionDto nutritionDto : nutLst) {
							nuts.put(nutritionDto.getId(), nutritionDto.getCon());
						}
						boolean flag = true;
						for (SmartNutritionDto nutrDto : nutritions) {
							String nutId = nutrDto.getNutritionId();
							if(nuts.containsKey(nutId)){
								
								boolean flagx = false;
								Double nutQ = Double.parseDouble(nutrDto.getNutritionContent() + "");
								Double nutQP = nuts.get(nutId);
								String operator = nutrDto.getNutritionOperator();
								if(operator.equals(">")){
									if(nutQP.doubleValue() > nutQ.doubleValue()){
										flagx = true;
									}
									flag = flagx && flag;
								}
								
								if(operator.equals("<")){
									if(nutQP.doubleValue() < nutQ.doubleValue()){
										flagx = true;
									}
									flag = flagx && flag;
								}
								
								if(operator.equals("=")){
									if(nutQP.doubleValue() == nutQ.doubleValue()){
										flagx = true;
									}
									flag = flagx && flag;
								}
								
								if(operator.equals(">=")){
									if(nutQP.doubleValue() >= nutQ.doubleValue()){
										flagx = true;
									}
									flag = flagx && flag;
								}
								
								if(operator.equals("<=")){
									if(nutQP.doubleValue() <= nutQ.doubleValue()){
										flagx = true;
									}
									flag = flagx && flag;
								}
							}else{
								flag = false;
							}
						}
						if(flag) reLst.add(proDto);
					}
				} 
			}
		}
		return reLst;
	}
	
	/**
	 * 判断营养含量
	 * @param pros
	 * @param ifDto
	 * @return
	 */
	public List<ProductDto> isNut(List<ProductDto> pros, IntelligentFixingsDto ifDto){
		
		List<SmartNutritionDto> nutritions = ifDto.getNutrition();
		int nutrSize = nutritions.size();
		List<ProductDto> reLst = new ArrayList<ProductDto>();
		
		// 这一块使用in(id1,id2,id3,...)效率会更高
		for (ProductDto proDto : pros) {
			
			//非空验证
			List<NutritionDto> nutLst = proDto.getNutritionDtoList();
			if((nutLst != null) && (nutLst.size() != 0)){ // 由于业务的具体实现，这里通过验证List<NutritionDto>非空来判断对象非空
				
				// 验证营养含量
				int nutSize = nutLst.size();
				if(nutSize >= nutrSize){
					
					Map<String, Double> nuts = new HashMap<String, Double>(nutSize);
					for (NutritionDto nutritionDto : nutLst) {
						nuts.put(nutritionDto.getId(), nutritionDto.getCon());
					}
					boolean flag = true;
					for (SmartNutritionDto nutrDto : nutritions) {
						String nutId = nutrDto.getNutritionId();
						if(nuts.containsKey(nutId)){
							
							boolean flagx = false;
							Double nutQ = Double.parseDouble(nutrDto.getNutritionContent() + "");
							Double nutQP = nuts.get(nutId);
							String operator = nutrDto.getNutritionOperator();
							if(operator.equals(">")){
								if(nutQP.doubleValue() > nutQ.doubleValue()){
									flagx = true;
								}
								flag = flagx && flag;
							}
							
							if(operator.equals("<")){
								if(nutQP.doubleValue() < nutQ.doubleValue()){
									flagx = true;
								}
								flag = flagx && flag;
							}
							
							if(operator.equals("=")){
								if(nutQP.doubleValue() == nutQ.doubleValue()){
									flagx = true;
								}
								flag = flagx && flag;
							}
							
							if(operator.equals(">=")){
								if(nutQP.doubleValue() >= nutQ.doubleValue()){
									flagx = true;
								}
								flag = flagx && flag;
							}
							
							if(operator.equals("<=")){
								if(nutQP.doubleValue() <= nutQ.doubleValue()){
									flagx = true;
								}
								flag = flagx && flag;
							}
						}else{
							flag = false;
						}
					}
					if(flag) reLst.add(proDto);
				} 
			}
		}
		return reLst;
	}
	
	/**
	 * 智能配菜
	 * @param dateLst
	 * @param ruleLst
	 * @param plst
	 * @param dto
	 * @return
	 */
	public Map<String, List<String>> genDayMenuV02(List<Date> dateLst, List<List<ProductVo>> ruleLst, List<ProductDto> plst, IntelligentFixingsDto dto){
		
		Integer size = dateLst.size();
		Map<String, List<String>> reMap = new HashMap<String, List<String>>(size);
		// 先通过成本价过滤一层
		List<ProductVo> filterLst = costfilterFirst(ruleLst, dto);
		int needSum = 0; // 指定配餐方案中各个大类的成品菜的分数
		List<CategoryDto> cateLst = dto.getCategory();
		for(CategoryDto cate : cateLst){
			needSum += cate.getCategoryCount();
		}
		Integer lunchCount = dto.getLunchCount();
		Integer dinnerCount = dto.getDinnerCount();
		if((lunchCount != null) && (dinnerCount != null)){
			needSum *= 2;
		}
		if(filterLst.size() < needSum){
			// 配菜失败
			return null;
		}else{
			
			int cateSize = cateLst.size();
			for(Date date : dateLst){
				
				String dateStr = DateUtils.format(date, "yyyy-MM-dd");
				List<ProductVo> reLst = null;
				
				if(cateSize == 1){ // 只有一个大类的情况
					reLst = isCostV02(filterLst, dto, true);
				}
				if(cateSize > 1){ // 有多个大类的情况
					reLst = isCostV02(filterLst, dto, false);
				}
				if(reLst == null){
					return null;
				}else{
					
					int sizeV03 = reLst.size();
					List<String> pids = new ArrayList<String>(sizeV03);
					for(ProductVo vo : reLst){
						pids.add(vo.getId());
					}
					reMap.put(dateStr, pids);
				}
			}
		}
		return reMap;
	}
	
	/**
	 * 复制数组
	 * @param src
	 * @param dest
	 * @return
	 */
	public List<ProductVo> copyLst(List<ProductVo> src, List<ProductVo> dest){
		
		for (ProductVo vo : src) {
			dest.add(vo);
		}
		return dest;
	}
	
	/**
	 * 从数组lst中找出离数组voLst最远的元素并返回，然后从lst中剔除该元素
	 * @param voLst
	 * @param lst
	 * @param mark true表示只生成只有一个大类的配菜方案，false表示生成包含多个大类的的配菜方案
	 * @return
	 */
	public ProductVo similarDegree(List<ProductVo> voLst, List<ProductVo> lst, boolean mark){
		
		int maxSqDegree = 0; 
		ProductVo maxdvo = null;
		Integer maxdvoIndex = null;
		int size = lst.size();
		for(int i=0; i<size; i++){
			
			int degree = 0;
			ProductVo vo = lst.get(i);
			for(ProductVo vo1 : voLst){
				
				if(mark){
					if(!vo1.getProductColorId().equals(vo.getProductColorId())){
						int colorV = SmartMenuParams.scolor;
						degree += colorV;
					}
					if(!vo1.getProductCuisineId().equals(vo.getProductCuisineId())){
						int cuisineV = SmartMenuParams.scuisine;
						degree += cuisineV;
					}
					if(!vo1.getProductShapeId().equals(vo.getProductShapeId())){
						int shapeV = SmartMenuParams.sshape;
						degree += shapeV;
					}
					if(!vo1.getProductStyleId().equals(vo.getProductStyleId())){
						int styleV = SmartMenuParams.sstyle;
						degree += styleV;
					}
					if(!vo1.getProductTasteId().equals(vo.getProductTasteId())){
						int tasteV = SmartMenuParams.staste;
						degree += tasteV;
					}
				}else{
					
					if(!vo1.getProductColorId().equals(vo.getProductColorId())){
						int colorV = SmartMenuParams.mcolor;
						degree += colorV;
					}
					if(!vo1.getProductCuisineId().equals(vo.getProductCuisineId())){
						int cuisineV = SmartMenuParams.mcuisine;
						degree += cuisineV;
					}
					if(!vo1.getProductShapeId().equals(vo.getProductShapeId())){
						int shapeV = SmartMenuParams.mshape;
						degree += shapeV;
					}
					if(!vo1.getProductStyleId().equals(vo.getProductStyleId())){
						int styleV = SmartMenuParams.mstyle;
						degree += styleV;
					}
					if(!vo1.getProductTasteId().equals(vo.getProductTasteId())){
						int tasteV = SmartMenuParams.mtaste;
						degree += tasteV;
					}
				}
			}
			if(degree > maxSqDegree){
				maxSqDegree = degree;
				maxdvo = vo;
				maxdvoIndex = i;
			}
		}
		lst.remove(maxdvoIndex);
		return maxdvo; 
	}
	
	/**
	 * 智能生成菜单
	 * @param id
	 * @param page
	 * @return
	 */
	@Deprecated
	public String generateMenuV01(IntelligentFixingsDto dto, Page page){
		
		String result = "";
		Map<String, List<String>> reMap = new HashMap<String, List<String>>();
		ProductDto productDto = new ProductDto();
		List<ProductDto> plst = productService.findAll(productDto);
		List<List<ProductVo>> ruleLst = ruleFilter(dto, plst);
		if(ruleLst == null){
			//配菜失败
			reMap = null;
		}else{
			List<Date> dateLst = DateUtils.getDatesBetweenTwoDate(dto.getFixingsStartTime(), dto.getFixingsEndTime());
			reMap = genDayMenuV02(dateLst, ruleLst, plst, dto);
			if(reMap != null){
				result = saveInteMenu(reMap, dto, plst);
			}
		}
		return result;
	}
	
	/**
	 * 智能生成菜单
	 * @param id
	 * @param page
	 * @return
	 */
	public String generateMenuV02(IntelligentFixingsDto dto, Page page){
		
		String result = "";
		Map<String, List<String>> reMap = new HashMap<String, List<String>>();
		ProductDto productDto = new ProductDto();
		List<ProductDto> plst = productService.findAll(productDto);
		List<ProductVo> tempLst = new ArrayList<ProductVo>();
		for(ProductDto tdto : plst){
			ProductVo vo = new ProductVo();
			BeanUtils.copyProperties(tdto, vo);
			tempLst.add(vo);
		}
		// 过滤是否敏感食材一层 
		List<ProductVo> spLst = senseFilterSecond(tempLst, dto.getIsSensitiveIngredients());
		//重新复制属性
		plst = new ArrayList<ProductDto>();
		for(ProductVo vo : spLst){
			ProductDto tdto = new ProductDto();
			BeanUtils.copyProperties(vo, tdto);
			plst.add(tdto);
		}
		List<List<ProductVo>> ruleLst = ruleFilter(dto, plst);
		if(ruleLst == null){
			//配菜失败
			reMap = null;
		}else{
			List<Date> dateLst = DateUtils.getDatesBetweenTwoDate(dto.getFixingsStartTime(), dto.getFixingsEndTime());
			reMap = genDayMenuV02(dateLst, ruleLst, plst, dto);
			if(reMap != null){
				result = saveInteMenu(reMap, dto, plst);
			}
		}
		return result;
	}
	
	/**
	 * 保存智能配菜信息
	 * @param reMap
	 * @param dto
	 * @return
	 */
	public String saveInteMenu(Map<String, List<String>> reMap, IntelligentFixingsDto dto, List<ProductDto> plst){
		// 保存数据
		String result = null;
		if(reMap != null){
		
			FixingsResultDto frDto = new FixingsResultDto();
			String frId = UUIDGenerator.getUUID();
			frDto.setId(frId);
			frDto.setFixingsId(dto.getId());
			frDto.setFixingsType(2); // 智能配菜
			frDto.setStat(1);
			frDto.setCreateTime(new Date());
			boolean flagX01 = fixingsResultDao.add(frDto);
			if(flagX01){
				
				Set<String> dateSet = reMap.keySet();
				for (String dateStr : dateSet) {
					
					List<String> pids = reMap.get(dateStr);
					// 根据午饭、晚饭数量是否非空判断成品菜类型,如果两餐都有，那么各一半长度的成品菜
					// 联合数据回滚
					int psizeX01 = pids.size();
					for (int i=0; i<psizeX01; i++) {
						
						String pid = pids.get(i);
						for(ProductDto pro : plst){
							if(pro.getId().equals(pid)){
								
								FixingsResultTypeDto frtDto = new FixingsResultTypeDto();
								String frtId = UUIDGenerator.getUUID();
								frtDto.setId(frtId);
								frtDto.setFixingsResultId(frId);
								frtDto.setCreateTime(new Date());
								frtDto.setStat(1);
								frtDto.setFixingsTime(DateUtils.StringToDate(dateStr, "yyyy-MM-dd"));
								frtDto.setProductCategoryId(pro.getProductCategoryId());
								if((dto.getLunchCount() != null) && (dto.getDinnerCount() != null)){
									int j = i+1;
									int l = psizeX01 / 2;
									if(j <= l){
										frtDto.setMealType(1);
									}else{
										frtDto.setMealType(2);
									}
								}else{
									if(dto.getLunchCount() != null){
										frtDto.setMealType(1);
									}
									if(dto.getDinnerCount() != null){
										frtDto.setMealType(2);
									}
								}
								
								boolean flagX02 = fixingsResultTypeDao.add(frtDto);
								if(flagX02){
									
									FixingsResultTypeProductDto frtpDto = new FixingsResultTypeProductDto();
									String frtpId = UUIDGenerator.getUUID();
									frtpDto.setId(frtpId);
									frtpDto.setCreateTime(new Date());
									frtpDto.setResultTypeId(frtId);
									frtpDto.setProductId(pid);
									frtpDto.setStat(1);
									boolean flagX03 = fixingsResultTypeProductDao.add(frtpDto);
									result = frId;
									if(!flagX03){
										throw new RuntimeException("保存智能配菜结果成品菜数据异常");
									}
								}else{
									throw new RuntimeException("保存智能配菜结果类型数据异常");
								}
							}	
						}
					}
				}
			}else{
				throw new RuntimeException("保存智能配菜结果数据异常");
			}
		}
		return result;
	}
	
	/**
	 * 比较两个值(oper为比较符号)
	 * <br>反过来，比如2>1，那么返回false
	 * @param val1
	 * @param val2
	 * @param oper
	 * @return
	 */
	public boolean compareVal(Double val1, Double val2, String oper){
		
		boolean flag = false;
		if(oper.equals(">")){
			if(val1.doubleValue() > val2.doubleValue()){
				flag = true;
			}
		}
		
		if(oper.equals("<")){
			if(val1.doubleValue() < val2.doubleValue()){
				flag = true;
			}
		}
		
		if(oper.equals("=")){
			if(val1.doubleValue() == val2.doubleValue()){
				flag = true;
			}
		}
		
		if(oper.equals(">=")){
			if(val1.doubleValue() >= val2.doubleValue()){
				flag = true;
			}
		}
		
		if(oper.equals("<=")){
			if(val1.doubleValue() <= val2.doubleValue()){
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * 成本价过滤
	 * @return
	 */
	public List<ProductVo> costfilterFirst(List<List<ProductVo>> ruleLst, IntelligentFixingsDto dto){
		
		List<ProductVo> reLst = new ArrayList<ProductVo>();

		for (List<ProductVo> proLst : ruleLst) {

			List<ProductVo> tempLst = new ArrayList<ProductVo>();
			tempLst = copyLst(proLst, tempLst);
			int tempSize01 = tempLst.size();
			String costOper = dto.getTotalCostOperator();
			String priceOper = dto.getTotalFixedPriceOperator();
			Double costSum = Double.parseDouble(dto.getTotalCost() + "");
			Double priceSum = Double.parseDouble(dto.getTotalFixedPrice() + "");
			List<ProductVo> deLst = new ArrayList<ProductVo>();
			for (int i = 0; i < tempSize01; i++) {

				ProductVo pvo = tempLst.get(i);
				Double eachCost = pvo.getSingleCost();
				Double eachPrice = Double.parseDouble(pvo.getEachPricing() + "");
				
				boolean flagx01 = false;
				boolean flagx02 = false;
				// 验证单个价格
				if(costOper.equals("<")){
					flagx01 = compareVal(eachCost, costSum, ">=");
				}
				if(costOper.equals("=")){
					flagx01 = compareVal(eachCost, costSum, ">=");
				}
				
				// 验证定价
				if(priceOper.equals("<")){
					flagx02 = compareVal(eachPrice, priceSum, ">=");
				}
				
				if(priceOper.equals("=")){
					flagx02 = compareVal(eachPrice, priceSum, ">=");
				}
				
				if(flagx01 || flagx02){
					deLst.add(pvo);
				}else{
					reLst.add(pvo);
				}
			}
			
			for(ProductVo vo : deLst){
				reLst.remove(vo);
			}
		}
		return reLst;
	}
	
	/**
	 * 生成一天的配菜方案
	 * @param tempLst
	 * @param dto
	 * @param singleFlag
	 * @return
	 */
	public List<ProductVo> isCostV02(List<ProductVo> filterLst, IntelligentFixingsDto dto, boolean singleFlag) {

		List<ProductVo> tempLst = new ArrayList<ProductVo>();
		tempLst = copyLst(filterLst, tempLst);
		List<ProductVo> dayLst = new ArrayList<ProductVo>();
		if((tempLst != null) && (tempLst.size() > 0)){
			
			int counter = 1;
			int pvsize = tempLst.size();
			int n = 0;
			if(pvsize > 2){
				n = pvsize/2;
			}else{
				n = pvsize;
			}
			Random rand = new Random();
			List<ProductVo> tempX01 = new ArrayList<ProductVo>();
			tempX01 = copyLst(tempLst, tempX01);

			while (true) {
				
				if(singleFlag){ // 单个大类
					// 随机初始一个基准点
					int index01 = 0;
					pvsize = tempLst.size();
					if(pvsize > 1){
						index01 = rand.nextInt(pvsize);
					}else{
						index01 = 0;
					}
					ProductVo center = tempLst.get(index01);
					tempLst.remove(index01);
					dayLst.add(center);
					// 找出相似度最小的dayCount-1个点
					Long tempCount = dto.getCategory().get(0).getCategoryCount();
					Long dayCount = ((dto.getLunchCount()!=null) && (dto.getDinnerCount() != null)) ? tempCount*2 : tempCount ;
					for(int i=1; i<dayCount; i++){
						ProductVo maxd = similarDegree(dayLst, tempLst, true);
						dayLst.add(maxd);
					}
				}else{
					
					int lack = 0; // 记录所属大类成品菜还少多少
					List<CategoryDto> cateLst = dto.getCategory();
					//先将满足条件的大类组装好
					for(CategoryDto cate : cateLst){
						
						String cid = cate.getCategoryId();
						List<ProductVo> temp = new ArrayList<ProductVo>();
						for(ProductVo pro : tempLst){
							if(pro.getProductCategoryId().equals(cid)) temp.add(pro);
						}
						
						// 随机初始一个基准点
						int tempSize = temp.size();
						ProductVo center = null;
						if(tempSize != 0){
							int index02 = 0;
							if(tempSize > 1){
								index02 = rand.nextInt(tempSize);
							}
							center = temp.get(index02);
							temp.remove(index02);
							dayLst.add(center);
						}
						
						Long cateCount = 0l;
						if((dto.getLunchCount() != null) && (dto.getDinnerCount() != null)){
							cateCount = cate.getCategoryCount()*2;
						}else{
							cateCount = cate.getCategoryCount();
						}
						
						if(tempSize > cateCount){
							//从temp中取出cateCount个最不相似的pro
							while (true) {
								for(int i=1; i<cateCount; i++){
									ProductVo maxd = similarDegree(dayLst, temp, true); // 同时将maxd从temp中删除
									dayLst.add(maxd);
								}
								break;
							}
							tempX01.removeAll(dayLst);
						}
						if(tempSize == cateCount){
							dayLst.addAll(temp);
							tempX01.removeAll(temp);
						}
						if(tempSize < cateCount){
							
							int size = temp.size();
							if(size != 0){
								dayLst.addAll(temp);
								tempX01.removeAll(temp);
							}
							lack += (cateCount-tempSize);
						}
					}

					//补全配菜方案
					while (true) {
						for(int i=0; i<lack; i++){
							ProductVo maxd = similarDegree(dayLst, tempX01, false);
							dayLst.add(maxd);
						}
						break;
					}
				}
				// 计算成本价等
				dayLst = calcAndCompare(dayLst, dto);
				// 判断是否包含敏感食材，因为在最开始的地方已经过滤了不包含情况，所以这里只判断包含情况
				int sense = dto.getIsSensitiveIngredients().intValue();
				if(sense == 1){
					boolean senMark = false;
					for(ProductVo vo : dayLst){
						if(vo.getIsSensitiveMaterial().intValue() == 1){
							senMark = true;
						}
					}
					if(!senMark){
						dayLst = null;
					}
				}
				
				if(dayLst != null){
					break;
				} else {
					if (counter < n) {
						counter++;
						tempX01 = copyLst(tempLst, tempX01);
						dayLst = new ArrayList<ProductVo>();
					} else {
						// 配菜失败
						return null;
					}
				}
			}
		}
		return dayLst;
	}
	
	/**
	 * 计算并比较成本、定价与预定的成本定价
	 * @param dayLst
	 * @param dto
	 * @return
	 */
	public List<ProductVo> calcAndCompare(List<ProductVo> dayLst, IntelligentFixingsDto dto){
		
		List<ProductVo> reLst = new ArrayList<ProductVo>();
		String costOper = dto.getTotalCostOperator();
		String priceOper = dto.getTotalFixedPriceOperator();
		Double costSum = Double.parseDouble(dto.getTotalCost()+"");
	    Double priceSum = Double.parseDouble(dto.getTotalFixedPrice()+"");
		
		Double tempCost = 0.0;
		Double tempPrice = 0.0;
		for (ProductVo vox02 : dayLst) {
			if(vox02 != null){
				tempCost += vox02.getSingleCost();
				tempPrice += vox02.getEachPricing();
			}
		}
		boolean flgX02 = compareVal(tempCost, costSum, costOper);
		boolean flgX03 = compareVal(tempPrice, priceSum, priceOper);
		
		if (!(flgX02 && flgX03)) reLst = null;
		else reLst = dayLst;
		
		return reLst;
	}
	
	/**
	 * 敏感食材过滤
	 * <br><b>notice:</b></br>
	 * <br>如果要求包含敏感食材，则只打标记不做过滤，以便后续处理；如果要求非敏感食材，则打标机并过滤</br>
	 * <br><b>1表示包含敏感；0表示不包含敏感</b></br>
	 * @param flst 原数据集合
	 * @param senseMark
	 * @return
	 */
	public List<ProductVo> senseFilterSecond(List<ProductVo> flst, Integer senseMark){
		
		int mark = senseMark.intValue();
		List<ProductVo> relst = new ArrayList<ProductVo>();
		
		if(mark == 1){
			
			for(ProductVo vo : flst){
				ProductNexusDto productNexusDto = new ProductNexusDto();
				productNexusDto.setId(vo.getId());
				productNexusDto = productNexusService.finProductNexusByProductId(productNexusDto);
				BeanUtils.copyProperties(productNexusDto, vo);
				relst.add(vo);
			}
		}
		if(mark == 0){
			
			for(ProductVo vo : flst){
				ProductNexusDto productNexusDto = new ProductNexusDto();
				productNexusDto.setId(vo.getId());
				productNexusDto = productNexusService.finProductNexusByProductId(productNexusDto);
				if(productNexusDto.getIsSensitiveMaterial().intValue() == 0){
					BeanUtils.copyProperties(productNexusDto, vo);
					relst.add(vo);
				}
			}
		}
		return relst;
	}

	
	
	
	
}
