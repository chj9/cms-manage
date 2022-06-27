package com.dliberty.cms.dao.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dliberty.cms.dao.entity.DocFile;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DocFileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DocFile record);

    DocFile selectByPrimaryKey(Integer id);

    List<DocFile> selectAll();

    int updateByPrimaryKey(DocFile record);
    
    DocFile selectByFileKey(@Param("fileKey")String fileKey);
}