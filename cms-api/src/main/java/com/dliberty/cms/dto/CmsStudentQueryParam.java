package com.dliberty.cms.dto;


import com.dliberty.cms.common.vo.BaseVo;

public class CmsStudentQueryParam extends BaseVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer stuScore;

    private String stuName;

    private String stuSchool;

    private String recruitCategory;
    
    private String signupNum;
    
    private String idNum;

	public Integer getStuScore() {
		return stuScore;
	}

	public void setStuScore(Integer stuScore) {
		this.stuScore = stuScore;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getStuSchool() {
		return stuSchool;
	}

	public void setStuSchool(String stuSchool) {
		this.stuSchool = stuSchool;
	}

	public String getRecruitCategory() {
		return recruitCategory;
	}

	public void setRecruitCategory(String recruitCategory) {
		this.recruitCategory = recruitCategory;
	}

	public String getSignupNum() {
		return signupNum;
	}

	public void setSignupNum(String signupNum) {
		this.signupNum = signupNum;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
    
    

}
