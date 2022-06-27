package com.dliberty.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dliberty.cms.dao.entity.CmsMenu;
import com.dliberty.cms.vo.CmsMenuAddOrModifyParam;
import com.dliberty.cms.vo.CmsMenuParam;
import com.dliberty.cms.vo.CmsMenuQueryParam;
import com.dliberty.cms.vo.CmsMenuVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LG
 * @since 2019-06-13
 */
public interface CmsMenuService extends IService<CmsMenu> {
	
	/**
	 * 分页查询Es
	 * @param param
	 * @return
	 */
	PageDTO<CmsMenuVo> listPageEs(CmsMenuQueryParam param);
	
	/**
	 * 搜索引擎查询
	 * @param id
	 * @return
	 */
	CmsMenuVo getPageEs(String id);
	
	/**
	 * 分页查询
	 * @param param
	 * @return
	 */
	IPage<CmsMenu> listPage(CmsMenuQueryParam param);
	
	
	/**
	 * 创建菜单
	 * @param param
	 * @return
	 */
	CmsMenu createMenu(CmsMenuParam param);
	
	/**
	 * 根据refId 查询
	 * @param refId
	 * @return
	 */
	List<CmsMenu> selectByRefId(String refId);
	
	/**
	 * 浏览+1
	 * @param id
	 */
	void browse(Long menuId);
	
	/**
	 * 收藏+num
	 * @param id
	 */
	void collection(Long menuId,Integer num);
	
	/**
	 * 查询用户的收藏
	 * @param userId
	 * @return
	 */
	List<CmsMenuVo> selectCollectionByUserId(Long userId);
	
	/**
	 * 更新搜索引擎数据
	 * @param menu
	 */
	void updateEs(CmsMenu menu);
	
	/**
	 * 删除菜谱
	 * @param id
	 */
	void delete(Integer id);
	
	/**
	 * 修改菜谱基本信息（不包含步骤，材料等）
	 * @param id
	 * @param param
	 * @return
	 */
	CmsMenu update(Integer id,CmsMenuAddOrModifyParam param);
}
