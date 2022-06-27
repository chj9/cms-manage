package com.dliberty.cms;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dliberty.cms.entity.CmsMenuEntity;
import com.dliberty.cms.entity.CmsMenuMaterialEntity;
import com.dliberty.cms.entity.CmsMenuStepEntity;
import com.dliberty.cms.service.CmsMenuEsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dliberty.cms.service.CmsMenuMaterialService;
import com.dliberty.cms.service.CmsMenuService;
import com.dliberty.cms.service.CmsMenuStepService;
import com.dliberty.cms.vo.CmsMenuVo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MenuApplication.class)
public class CmsMenuServiceTest {

	@Autowired
	private CmsMenuEsService cmsMenuEsService;
	@Autowired
	private CmsMenuService cmsMenuService;
	@Autowired
	private CmsMenuStepService cmsMenuStepService;
	@Autowired
	private CmsMenuMaterialService cmsMenuMaterialService;

	@Test
	public void deleteTest() {
		cmsMenuEsService.deleteById(1+"");
	}

	@Test
	public void saveTest() {

		List<CmsMenuVo> list = new ArrayList<>();
		
		IPage<CmsMenuEntity> ipage = new Page<>(11, 5000);

		IPage<CmsMenuEntity> page = cmsMenuService.page(ipage);

		page.getRecords().stream().forEach(menu -> {

			boolean existsById = cmsMenuEsService.existsById(menu.getId()+"");
			if (!existsById) {

				CmsMenuVo vo = new CmsMenuVo();
				BeanUtils.copyProperties(menu, vo);
				List<CmsMenuStepEntity> stepList = cmsMenuStepService.selectByMenuId(menu.getId());
				vo.setStepList(stepList);
				List<CmsMenuMaterialEntity> materialList = cmsMenuMaterialService.selectByMenuId(menu.getId());
				vo.setMaterialList(materialList);

				list.add(vo);
			}

		});

		cmsMenuEsService.saveAll(list);

	}

	@Test
	public void getTest() {
		//long count = cmsMenuEsService.count();
		boolean existsById = cmsMenuEsService.existsById(1+"");
		System.out.println(existsById);
	}

//	@Test
//	public void queryPageTest() {
//		Page<CmsMenuVo> pageQuery = cmsMenuEsService.pageQuery(1, 20, "西兰花");
//		System.out.println(pageQuery);
//
//	}
	
//	@Test
//	public void getByIdsTest() {
//		List<CmsMenuVo> byIds = cmsMenuEsService.getByIds(java.util.Arrays.asList(14854,14855,14856));
//		System.out.println(byIds);
//	}
	
//	@Test
//	public void queryPageCateTest() {
//		Page<CmsMenuVo> pageQuery = cmsMenuEsService.pageQueryByCateId(1, 20, 26);
//		System.out.println(pageQuery);
//
//	}
	
	@Test
	public void selectCollectionByUserIdTest() {
		List<CmsMenuVo> menuList = cmsMenuService.selectCollectionByUserId(18l);
		System.out.println(menuList);
	}
	
	@Test
	public void browseTest() {
		cmsMenuService.browse(1L);
	}

}
