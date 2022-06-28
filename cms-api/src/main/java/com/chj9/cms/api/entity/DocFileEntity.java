package com.chj9.cms.api.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.chj9.cms.api.base.IdEntity;
import com.chj9.cms.common.constants.Constants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.InputStream;

@JsonIgnoreProperties(ignoreUnknown = true)
@TableName(value = "doc_file", autoResultMap = true)
public class DocFileEntity extends IdEntity {

    private String fileKey;

    private String filePath;

    private String fileName;

    private Integer isDeleted = Constants.COMMON_FLAG_NO;


    private String fileType;

    private InputStream inputStream;


    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}