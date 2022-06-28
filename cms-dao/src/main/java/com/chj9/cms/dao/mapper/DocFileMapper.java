package com.chj9.cms.dao.mapper;


import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chj9.cms.api.entity.DocFileEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DocFileMapper extends BaseMapper<DocFileEntity> {
    int deleteByPrimaryKey(Integer id);

    int insert(DocFileEntity record);

    DocFileEntity selectByPrimaryKey(Integer id);

    List<DocFileEntity> selectAll();

    int updateByPrimaryKey(DocFileEntity record);

    DocFileEntity selectByFileKey(@Param("fileKey")String fileKey);
}