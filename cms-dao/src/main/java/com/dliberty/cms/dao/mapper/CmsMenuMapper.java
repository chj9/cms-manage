package com.dliberty.cms.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dliberty.cms.dao.base.BaseMapper;
import com.dliberty.cms.dao.dto.CmsMenuQueryDto;
import com.dliberty.cms.dao.entity.CmsMenu;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LG
 * @since 2019-06-13
 */
public interface CmsMenuMapper extends BaseMapper<CmsMenu> {

	/**
	 * 查询用户收藏
	 * @param userId
	 * @return
	 */
	List<CmsMenu> selectCollectionByUserId(@Param("userId") Long userId);
	
	/**
	 * 分页查询数据
	 * @param page
	 * @return
	 */
	IPage<CmsMenu> listPage(Page<CmsMenu> page,@Param("dto")CmsMenuQueryDto dto);
}
