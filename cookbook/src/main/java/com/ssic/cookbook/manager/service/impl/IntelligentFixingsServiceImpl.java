package com.ssic.cookbook.manager.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssic.cookbook.admin.entity.Page;
import com.ssic.cookbook.manager.dao.CategoryDao;
import com.ssic.cookbook.manager.dao.ColorDao;
import com.ssic.cookbook.manager.dao.CuisineDao;
import com.ssic.cookbook.manager.dao.FixingsMasterDao;
import com.ssic.cookbook.manager.dao.FixingsResultDao;
import com.ssic.cookbook.manager.dao.IntelligentFixingsDao;
import com.ssic.cookbook.manager.dao.NutritionDao;
import com.ssic.cookbook.manager.dao.ShapeDao;
import com.ssic.cookbook.manager.dao.StyleDao;
import com.ssic.cookbook.manager.dao.TasteDao;
import com.ssic.cookbook.manager.dto.CategoryDto;
import com.ssic.cookbook.manager.dto.ColorDto;
import com.ssic.cookbook.manager.dto.CuisineDto;
import com.ssic.cookbook.manager.dto.FixingsMasterDto;
import com.ssic.cookbook.manager.dto.FixingsResultDto;
import com.ssic.cookbook.manager.dto.IntelligentFixingsDto;
import com.ssic.cookbook.manager.dto.ShapeDto;
import com.ssic.cookbook.manager.dto.SmartNutritionDto;
import com.ssic.cookbook.manager.dto.SmartResultDto;
import com.ssic.cookbook.manager.dto.StyleDto;
import com.ssic.cookbook.manager.dto.TasteDto;
import com.ssic.cookbook.manager.pojo.FixingsResult;
import com.ssic.cookbook.manager.pojo.IntelligentFixings;
import com.ssic.cookbook.manager.pojo.IntelligentFixingsCategory;
import com.ssic.cookbook.manager.pojo.IntelligentFixingsColor;
import com.ssic.cookbook.manager.pojo.IntelligentFixingsCuisine;
import com.ssic.cookbook.manager.pojo.IntelligentFixingsNutrition;
import com.ssic.cookbook.manager.pojo.IntelligentFixingsShape;
import com.ssic.cookbook.manager.pojo.IntelligentFixingsStyle;
import com.ssic.cookbook.manager.pojo.IntelligentFixingsTaste;
import com.ssic.cookbook.manager.service.IIntelligentFixingsService;
import com.ssic.cookbook.manager.service.MenuGenService;
import com.ssic.cookbook.manager.util.BeanUtils;
@Service
public class IntelligentFixingsServiceImpl implements IIntelligentFixingsService
{       
        @Autowired
        private IntelligentFixingsDao intelligentFixingsDao;
        
        @Autowired
        private FixingsMasterDao fixingsMasterDao;
        
        @Autowired
        private FixingsResultDao fixingsResultDao;
        
        @Autowired
        private NutritionDao nutritionDao;
        
        @Autowired
        private CategoryDao categoryDao;
        
        @Autowired
        private StyleDao styleDao;
        
        @Autowired
        private ShapeDao shapeDao;
        
        @Autowired
        private TasteDao tasteDao;
        
        @Autowired
        private CuisineDao cuisineDao;
        
        @Autowired
        private ColorDao colorDao;
        
        @Autowired
        private MenuGenService menuGenService; 
        

        //@Cacheable(value = "default", key = "'cookbook.manager.dto.IntelligentFixingsDtoList'")
        public List<FixingsResultDto> findAllIntelligentProduct(FixingsResultDto fdto)
        {
            // TODO 添加方法注释
            List<FixingsResultDto> list=   intelligentFixingsDao.findAllIntelligentProduct(fdto);
            if(list==null){
                return list;
            }
            return list;
        }

        
        //@Cacheable(value = "default", key = "'cookbook.manager.dto.IntelligentFixingsDtoList'")
        public List<FixingsResultDto> findAllIntelligentProductType()
        {
            // TODO 添加方法注释
            return intelligentFixingsDao.findAllIntelligentProductType();
        }
       // @Cacheable(value = "default", key = "'cookbook.manager.dto.IntelligentFixingsDtoList'")
        public List<FixingsResultDto> findAllIntelligentProductName()
        {
            // TODO 添加方法注释
            return intelligentFixingsDao.findAllIntelligentProductName();
        }

        public int findCount()
        {
            // TODO 添加方法注释
            return intelligentFixingsDao.findCount();
        }
        
        /**
		 * @author YIn
		 * @time:2015年12月31日 上午9:29:30
		 */
        @Transactional
		public SmartResultDto addSmart(IntelligentFixingsDto dto) {
        	Page page = new Page();
    		String id = menuGenService.generateMenuV02(dto, page);
    		if(StringUtils.isEmpty(id)){
        		 return null; 
    		}
        	//配菜主表插数据
        	FixingsMasterDto masterdto = new FixingsMasterDto();
        	masterdto.setId(UUID.randomUUID()+"");
        	masterdto.setFixingsName(dto.getFixingsName());
        	masterdto.setFixingsType(2); //智能配菜
        	boolean masterFlag = fixingsMasterDao.add(masterdto);
        	/*if(masterFlag != true){
        		return null;
        	}*/
        	
        	//向智能配菜表插数据
        	dto.setId(UUID.randomUUID()+"");
        	dto.setFixingsMasterId(masterdto.getId());
			IntelligentFixings smart = new IntelligentFixings();
			BeanUtils.copyProperties(dto, smart);
			boolean smartFlag = intelligentFixingsDao.addSmart(smart);
        	
        	for (CategoryDto categoryDto : dto.getCategory()) {  
        		categoryDto.setId(UUID.randomUUID()+"");
        		categoryDto.setCreateTime(new Date());
        		categoryDto.setStat(1);
				categoryDto.setIntelligentFixingsId(dto.getId());
	        }  
        	for (StyleDto styleDto : dto.getStyle()) { 
        		styleDto.setId(UUID.randomUUID()+"");
        		styleDto.setCreateTime(new Date());
        		styleDto.setStat(1);
        		styleDto.setIntelligentFixingsId(dto.getId());
        	}  
        	for (ShapeDto shapeDto : dto.getShape()) { 
        		shapeDto.setId(UUID.randomUUID()+"");
        		shapeDto.setCreateTime(new Date());
        		shapeDto.setStat(1);
        		shapeDto.setIntelligentFixingsId(dto.getId());
        	}  
        	for (TasteDto tasteDto : dto.getTaste()) {  
        		tasteDto.setId(UUID.randomUUID()+"");
        		tasteDto.setCreateTime(new Date());
        		tasteDto.setStat(1);
        		tasteDto.setIntelligentFixingsId(dto.getId());
        	}  
        	for (CuisineDto cuisineDto : dto.getCuisine()) { 
        		cuisineDto.setId(UUID.randomUUID()+"");
        		cuisineDto.setCreateTime(new Date());
        		cuisineDto.setStat(1);
        		cuisineDto.setIntelligentFixingsId(dto.getId());
        	}  
        	for (ColorDto colorDto : dto.getColor()) {  
        		colorDto.setId(UUID.randomUUID()+"");
        		colorDto.setCreateTime(new Date());
        		colorDto.setStat(1);
        		colorDto.setIntelligentFixingsId(dto.getId());
        	}  
        	for (SmartNutritionDto smartNutritionDto : dto.getNutrition()) {
        		smartNutritionDto.setId(UUID.randomUUID()+"");
        		smartNutritionDto.setCreateTime(new Date());
        		smartNutritionDto.setStat(1);
        		smartNutritionDto.setIntelligentFixingsId(dto.getId());
        	}  
        	boolean categoryFlag = categoryDao.addList(dto.getCategory());
        	boolean styleFlag = styleDao.addList(dto.getStyle());
        	boolean shapeFlag = shapeDao.addList(dto.getShape());
        	boolean tasteFlag = tasteDao.addList(dto.getTaste());
        	boolean cuisineFlag = cuisineDao.addList(dto.getCuisine());
        	boolean colorFlag = colorDao.addList(dto.getColor());
        	boolean nutritionFlag = nutritionDao.addList(dto.getNutrition());
        	if(masterFlag && smartFlag && categoryFlag && styleFlag && shapeFlag && tasteFlag && cuisineFlag && colorFlag && nutritionFlag){
        		SmartResultDto smartResultDto = new SmartResultDto();
        		smartResultDto.setProductId(id);
        		smartResultDto.setSmartId(dto.getId());
        		
        		//FixingsResult 表里面插入智能配菜Id
        		FixingsResult fixingsResult =new FixingsResult();
        		fixingsResult.setId(id);
        		fixingsResult.setFixingsId(dto.getId());
        		fixingsResultDao.updateFixingsResult(fixingsResult);
        		return smartResultDto;
        	}
			return null;
		}

		public List<IntelligentFixingsDto> findSmart(IntelligentFixingsDto dto) {
			//设置查询条件
			dto.setStat(1);   //有效
			dto.setFixingsType(2);   //2为智能配菜
			List<IntelligentFixingsDto> result = intelligentFixingsDao.findIntelligentFixings(dto);
			for(int i = 0;i < result.size();i++){
				String id = result.get(i).getId();
				if(id != null && !id.equals("")){
				List<IntelligentFixingsCategory> categoryList = categoryDao.findCategory(id);
				List<CategoryDto> categoryDtoList= BeanUtils.createBeanListByTarget(categoryList, CategoryDto.class);
				result.get(i).setCategory(categoryDtoList);
				
				List<IntelligentFixingsNutrition> nutritionList = nutritionDao.findNutrition(id);
				List<SmartNutritionDto> nutritionDtoList= BeanUtils.createBeanListByTarget(nutritionList, SmartNutritionDto.class);
				result.get(i).setNutrition(nutritionDtoList);
				}
				
				List<IntelligentFixingsStyle> styleList = styleDao.findStyle(id);
				List<StyleDto> styleDtoList= BeanUtils.createBeanListByTarget(styleList, StyleDto.class);
				result.get(i).setStyle(styleDtoList);
				
				List<IntelligentFixingsShape> shapeList = shapeDao.findShape(id);
				List<ShapeDto> shapeDtoList= BeanUtils.createBeanListByTarget(shapeList, ShapeDto.class);
				result.get(i).setShape(shapeDtoList);
				
				List<IntelligentFixingsTaste> tasteList = tasteDao.findTaste(id);
				List<TasteDto> tasteDtoList= BeanUtils.createBeanListByTarget(tasteList, TasteDto.class);
				result.get(i).setTaste(tasteDtoList);
				
				List<IntelligentFixingsCuisine> cuisineList = cuisineDao.findCuisine(id);
				List<CuisineDto> cuisineDtoList= BeanUtils.createBeanListByTarget(cuisineList, CuisineDto.class);
				result.get(i).setCuisine(cuisineDtoList);
				
				List<IntelligentFixingsColor> colorList = colorDao.findColor(id);
				List<ColorDto> colorDtoList= BeanUtils.createBeanListByTarget(colorList, ColorDto.class);
				result.get(i).setColor(colorDtoList);
			}
			return result;
		}

		public int findCount(IntelligentFixingsDto dto) {
			//设置查询条件
			dto.setStat(1);   //有效
			dto.setFixingsType(2);   //2为智能配菜
			int count = intelligentFixingsDao.findCount(dto);
			return count;
		}

		/**
		 * @author YIn
		 * @time:2016年1月7日 下午3:10:02
		 */
		public int update(IntelligentFixingsDto dto) {
			IntelligentFixings record = BeanUtils.createBeanByTarget(dto, IntelligentFixings.class);
			record.setLastUpdateTime(new Date());
			return intelligentFixingsDao.updateIntelligentFixings(record);
			//return intelligentFixingsDao.updateByPrimaryKeySelective(record);
		}


		//  @CacheEvict(value = "default", key ="'cookbook.manager.dto.IntelligentFixingsDtoList'", beforeInvocation = true)
        public void deleteIntelligent(FixingsResultDto fdto)
        {
            intelligentFixingsDao.deleteIntelligent(fdto);
            
        }

}

