package com.ssic.cookbook.manager.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.ssic.cookbook.admin.dao.DaoSupport;
import com.ssic.cookbook.admin.entity.Page;
import com.ssic.cookbook.admin.util.PageData;
import com.ssic.cookbook.manager.dao.MaterialDao;
import com.ssic.cookbook.manager.dto.BrandDto;
import com.ssic.cookbook.manager.dto.MaterialBigCategoryDto;
import com.ssic.cookbook.manager.dto.MaterialDto;
import com.ssic.cookbook.manager.dto.MaterialNutritionDto;
import com.ssic.cookbook.manager.dto.NutritionDto;
import com.ssic.cookbook.manager.dto.ParamConfigDto;
import com.ssic.cookbook.manager.dto.ReturnResultDto;
import com.ssic.cookbook.manager.pojo.Material;
import com.ssic.cookbook.manager.pojo.ParamConfig;
import com.ssic.cookbook.manager.service.IBrandService;
import com.ssic.cookbook.manager.service.IMaterialBigCategoryService;
import com.ssic.cookbook.manager.service.IMaterialNutritionService;
import com.ssic.cookbook.manager.service.IMaterialService;
import com.ssic.cookbook.manager.service.INutritionService;
import com.ssic.cookbook.manager.service.IParamConfigService;
import com.ssic.cookbook.manager.util.CookbookFields;
import com.ssic.util.BeanUtils;
import com.ssic.util.UUIDGenerator;

@Service("materialService")
public class MaterialServiceImpl implements IMaterialService
{

    @Autowired
    private DaoSupport dao;
    @Autowired
    private MaterialDao materialDao;
    @Autowired
    private INutritionService nutritionService;
    @Autowired
    private IBrandService brandService;
    @Autowired
    private IMaterialBigCategoryService materialBigCategoryService;
    @Autowired
    private IParamConfigService paramConfigService;
    @Autowired
    private IMaterialNutritionService materialNutritionService;

    public List<PageData> findByPage(Page page) throws Exception
    {
        return (List<PageData>) dao.findForList("MaterialXMapper.findByPage", page);
    }

    public List<MaterialDto> findListByPage(MaterialDto materialDto)
    {
        Material material = new Material();
        BeanUtils.copyProperties(materialDto, material);
        List<Material> list = materialDao.findByPage(material);
        List<MaterialDto> listdto = BeanUtils.createBeanListByTarget(list, MaterialDto.class);
        return listdto;
    }

    public List<MaterialDto> findAll(MaterialDto materialDto)
    {
        // TODO Auto-generated method stub
        return materialDao.findAll(materialDto);
    }

    public int findCount(MaterialDto materialDto)
    {
        // TODO Auto-generated method stub
        return materialDao.findCount(materialDto);
    }

    public void updateM(MaterialDto materialDto)
    {
        // TODO Auto-generated method stub
        Material material = new Material();
        BeanUtils.copyProperties(materialDto, material);
        materialDao.updateM(material);
    }

    public void insertM(MaterialDto materialDto)
    {
        // TODO Auto-generated method stub
        Material material = new Material();
        BeanUtils.copyProperties(materialDto, material);
        materialDao.insertM(material);

    }

    @Transactional
    public ReturnResultDto insertAllMater(MaterialDto materialDto)
    { //新增原料 插入原料，营养，营养原料表
      // TODO Auto-generated method stub
        ReturnResultDto returnResultDto = new ReturnResultDto();
        //插入营养表
        List<NutritionDto> listnu = materialDto.getListNu();
        if (!CollectionUtils.isEmpty(listnu))
        {
            //插入原料表
            String materId = UUIDGenerator.getUUID();
            Material material = new Material();
            BeanUtils.copyProperties(materialDto, material);
            material.setId(materId);
            material.setCreateTime(new Date());
            materialDao.insertM(material);
            //插入营养
            for (NutritionDto nutr1 : listnu)
            {
                String pid = nutr1.getParamId();
                Long content = nutr1.getContent();
                ParamConfigDto paramco1 =
                    paramConfigService.findByTypeAndPid(pid, CookbookFields.Material_Nutrition);
                String name = paramco1.getParamName();
                String ptype = CookbookFields.Material_Nutrition;
                String nuid = UUIDGenerator.getUUID();
                NutritionDto nutritionDto = new NutritionDto();
                nutritionDto.setId(nuid);
                nutritionDto.setName(name);
                nutritionDto.setContent(content);
                //查询单位
                nutritionDto.setUnit(paramco1.getRemark());
                nutritionDto.setParamId(pid);
                nutritionDto.setParamType(ptype);
                nutritionDto.setCreateTime(new Date());
                nutritionService.insertNS(nutritionDto);
                //插入原料营养关系表
                MaterialNutritionDto materialNutritionDto = new MaterialNutritionDto();
                materialNutritionDto.setId(UUIDGenerator.getUUID());
                materialNutritionDto.setNutritionId(nuid);
                materialNutritionDto.setMaterialId(materId);
                materialNutritionDto.setCreateTime(new Date());
                materialNutritionService.insertBN(materialNutritionDto);

            }
            returnResultDto.setErrorCode(CookbookFields.Return_Success);
            returnResultDto.setMsg("返回成功");
        }
        else
        {
            returnResultDto.setErrorCode(CookbookFields.Return_Fail);
            returnResultDto.setMsg("营养不能为空");
        }
        return returnResultDto;
    }

    public MaterialDto findById(String id)
    {
        // TODO Auto-generated method stub
        Material material = materialDao.findById(id);
        MaterialDto materialDto = new MaterialDto();
        BeanUtils.copyProperties(material, materialDto);
        return materialDto;
    }

    /**
     * @author pengcheng.yang
     * @throws Exception 
     * @desc 到处原料配置数据
     */
    @SuppressWarnings("unchecked")
    public List<PageData> expExcelFindAll(PageData pd) throws Exception
    {
        return (List<PageData>) dao.expExcelFindAll("MaterialXMapper.expExcelFindAll", pd);
    }

    public List<NutritionDto> findNuByMid(String mid)
    {
        // TODO Auto-generated method stub
        return materialDao.findNuByMid(mid);
    }

    public void delM(String id)
    {
        // TODO Auto-generated method stub
        Material material = new Material();
        material.setId(id);
        material.setStat(0);
        materialDao.delM(material);
    }

    @Transactional
    public void updateMaterAndNutr(MaterialDto materialDto)
    {
        // TODO Auto-generated method stub
        //更新原料，删除原料营养关系表，删除营养表，插入原料营养关系表，营养表
        //更新原料
        Material material = new Material();
        BeanUtils.copyProperties(materialDto, material);
        materialDao.updateM(material);
        //删除营养表
        List<MaterialNutritionDto> listmn = materialNutritionService.findByMaterialId(materialDto.getId());
        if (!CollectionUtils.isEmpty(listmn))
        {
            for (MaterialNutritionDto manudto : listmn)
            {
                String nutrid = manudto.getNutritionId();
                nutritionService.deleteNutr(nutrid);
            }
        }
        //删除原料营养关系表
        materialNutritionService.deleteMN(materialDto.getId());
        //插入营养表
        //插入原料营养关系表
        List<NutritionDto> listnu = materialDto.getListNu();
        if (!CollectionUtils.isEmpty(listnu))
        {
            for (NutritionDto nutrDto2 : listnu)
            {
                nutritionService.insertNS(nutrDto2);
                MaterialNutritionDto materialNutritionDto2 = new MaterialNutritionDto();
                materialNutritionDto2.setNutritionId(nutrDto2.getId());
                materialNutritionDto2.setMaterialId(materialDto.getId());
                materialNutritionDto2.setLastUpdateTime(new Date());
                materialNutritionDto2.setId(UUIDGenerator.getUUID());
                materialNutritionService.insertBN(materialNutritionDto2);
            }
        }

    }

    /** 
    * (non-Javadoc)   
    * @see com.ssic.cookbook.manager.service.IMaterialService#findByName(java.lang.String)   
    */
    public MaterialDto findByName(String name)
    {
        MaterialDto dto = new MaterialDto();
        Material m = materialDao.findByName(name);
        if (m != null)
        {
            BeanUtils.copyProperties(m, dto);
            return dto;
        }
        return null;
    }

    @Transactional
	public ReturnResultDto insertExecl(List<MaterialDto> list) {
		// TODO Auto-generated method stub
    	ReturnResultDto returnResultDto = new ReturnResultDto();
      if(!CollectionUtils.isEmpty(list)){    	
		for(MaterialDto m1 : list){
			String bigCategoryName1 = m1.getBigCategoryName();
			String brandName1 = m1.getBrandName();
			List<NutritionDto> listnutr = m1.getListNu();
			
			if("是".equals(m1.getIsSensitiveName())){
				m1.setIsSensitiveMaterial(1);
			}else{
				m1.setIsSensitiveMaterial(0);
			}
			
			if("原料".equals(m1.getTypeName())){
				m1.setType(1);
			}else{
				m1.setType(2);
			}
			
			//查询原料大类是否存在    不存在插入
			String bigCategoryName1_id ="";
			MaterialBigCategoryDto materialBigCategoryDto = new MaterialBigCategoryDto();
			materialBigCategoryDto.setName(bigCategoryName1);
			List<MaterialBigCategoryDto> listmbig = materialBigCategoryService.findForList(materialBigCategoryDto);
			if(CollectionUtils.isEmpty(listmbig)){
				bigCategoryName1_id = UUIDGenerator.getUUID();
				//插入新的原料大类
				MaterialBigCategoryDto materialBigCategoryDto2 = new MaterialBigCategoryDto();
				materialBigCategoryDto2.setId(bigCategoryName1_id);
				materialBigCategoryDto2.setName(bigCategoryName1);
				materialBigCategoryDto2.setCreateTime(new Date());
				materialBigCategoryService.insertMBC(materialBigCategoryDto2);
				m1.setBigCategoryId(bigCategoryName1_id);
			}else{
				bigCategoryName1_id = listmbig.get(0).getId();
				m1.setBigCategoryId(bigCategoryName1_id);
			}
			//查询原料品牌是否存在   不存在插入
			String brandName1_id ="";
			BrandDto brandDto = new BrandDto();
			brandDto.setName(brandName1);
			List<BrandDto> listmbrand = brandService.findForList(brandDto);
			if(CollectionUtils.isEmpty(listmbrand)){
				brandName1_id = UUIDGenerator.getUUID();
				//插入新的原料品牌表
				BrandDto brandDto2 = new BrandDto();
				brandDto2.setName(brandName1);
                brandDto2.setId(brandName1_id);
                brandDto2.setCreateTime(new Date());
				brandService.insertBS(brandDto2);
				m1.setBrandId(brandName1_id);
			}else{
				brandName1_id = listmbrand.get(0).getId();
				m1.setBrandId(brandName1_id);
			}
			
			//查询原料营养是否存在  不存在插入
			for(NutritionDto  nutri: listnutr){
				 //查询营养名称是否存在  不存在插入
				String nutrname = nutri.getName();
				String nutrunit = nutri.getUnit();
				
				ParamConfigDto paramConfigDto = new ParamConfigDto();
				paramConfigDto.setParamName(nutrname);
				paramConfigDto.setParamType(CookbookFields.Material_Nutrition);
				List<ParamConfigDto> listpar = paramConfigService.findByList(paramConfigDto);
				
				if(CollectionUtils.isEmpty(listpar)){
					//插入参数表中
				   String paramcid = UUIDGenerator.getUUID();
	               ParamConfigDto paramConfigDto2 = new ParamConfigDto();
	               paramConfigDto2.setId(paramcid);
	               paramConfigDto2.setParamId(paramcid);
	               paramConfigDto2.setParamName(nutrname);
	               paramConfigDto2.setName("原料营养");
	               paramConfigDto2.setCreateTime(new Date());
	               paramConfigDto2.setParamType(CookbookFields.Material_Nutrition);
	               paramConfigDto2.setRemark(nutrunit);
				   paramConfigService.insertPam(paramConfigDto2);
				}else{
					//不插入参数表 
					nutri.setParamId(listpar.get(0).getParamId());
				}
			}
			
			  //插入原料表
            String materId = UUIDGenerator.getUUID();
            Material material = new Material();
            BeanUtils.copyProperties(m1, material);
            material.setId(materId);
            material.setCreateTime(new Date());
            materialDao.insertM(material);
            //插入营养
            for (NutritionDto nutr1 : listnutr)
            {
                String pid = nutr1.getParamId();
                Long content = nutr1.getContent();
                ParamConfigDto paramco1 =
                    paramConfigService.findByTypeAndPid(pid, CookbookFields.Material_Nutrition);
                String name = paramco1.getParamName();
                String ptype = CookbookFields.Material_Nutrition;
                String nuid = UUIDGenerator.getUUID();
                NutritionDto nutritionDto = new NutritionDto();
                nutritionDto.setId(nuid);
                nutritionDto.setName(name);
                nutritionDto.setContent(content);
                //查询单位
                nutritionDto.setUnit(paramco1.getRemark());
                nutritionDto.setParamId(pid);
                nutritionDto.setParamType(ptype);
                nutritionDto.setCreateTime(new Date());
                nutritionService.insertNS(nutritionDto);
                //插入原料营养关系表
                MaterialNutritionDto materialNutritionDto = new MaterialNutritionDto();
                materialNutritionDto.setId(UUIDGenerator.getUUID());
                materialNutritionDto.setNutritionId(nuid);
                materialNutritionDto.setMaterialId(materId);
                materialNutritionDto.setCreateTime(new Date());
                materialNutritionService.insertBN(materialNutritionDto);

            }
			
		  }
    	}
      returnResultDto.setErrorCode("200");
      returnResultDto.setMsg("成功导入 "+list.size()+" 条");
      return returnResultDto;
      
	}

    
     /** 
     * (non-Javadoc)   
     * @see com.ssic.cookbook.manager.service.IMaterialService#findListByName(com.ssic.cookbook.manager.dto.MaterialDto)   
     */
    public List<MaterialDto> findListByName(MaterialDto materialDto)
    {
        Material material = new Material();
        BeanUtils.copyProperties(materialDto, material);
        List<Material> list = materialDao.findListByName(material);
        List<MaterialDto> listdto = BeanUtils.createBeanListByTarget(list, MaterialDto.class);
        return listdto;
    }

	public List<MaterialDto> findListBy(MaterialDto materialDto) {
		// TODO Auto-generated method stub
		  Material material = new Material();
	      BeanUtils.copyProperties(materialDto, material);
	      List<Material> list = materialDao.findBy(material);
	      List<MaterialDto> listdto = BeanUtils.createBeanListByTarget(list, MaterialDto.class);
	      return listdto;
	}
}
