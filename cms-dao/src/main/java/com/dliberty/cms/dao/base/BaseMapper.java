package com.dliberty.cms.dao.base;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dliberty.cms.vo.PageInfo;

public interface BaseMapper<T> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T> {

	/*
	 * 分页
	 */
	default IPage<T> selectPage(PageInfo page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
		IPage<T> ipage = new Page<>(page.getPageNum(), page.getPageSize());
		return this.selectPage(ipage, queryWrapper);
	}

	default IPage<T> selectPage(Integer paegNum, Integer pageSize, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
		IPage<T> ipage = new Page<>(paegNum, pageSize);
		return this.selectPage(ipage, queryWrapper);
	}
	
	/**
     * <p>
     * 由于 MybatisPlus BaseMapper提供的更新方法 没有显式的区分 "为空的字段是否更新"
     * 只能通过全局配置来设置（这个设置默认为 不更新空字段）
     * </p>
     * <p>
     * 此方法存在的意义 为了避免语义上模糊不清、方便以后项目更改
     * </p>
     * <p>
     * 注:此处实现依然使用 updateById, 当有"空字段也更新"的需求时 则只需更换此处实现即可
     * </p>
     *
     * @param entity
     * @return
     */
    default int updateByIdSelective(T entity) {
        return updateById(entity);
    }
    
}
