package com.dliberty.cms.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.dliberty.cms.vo.BaseVo;

public class CmsStudentParam extends BaseVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "分数")
	private Integer stuScore;

	@NotBlank(message="学生姓名")
    private String stuName;

	@NotBlank(message = "毕业学校")
    private String stuSchool;

	@NotBlank(message = "录取类别")
    private String recruitCategory;

	@NotBlank(message = "报名号")
    private String signupNum;

	@NotBlank(message = "请输入身份证号")
	@Length(min=18,max=18,message="请输入正确身份证号")
	private String idNum;

	@NotNull(message = "请选择性别")
    private Integer stuSex;

	@NotBlank(message = "请输入民族")
    private String stuNation;

	@NotBlank(message = "请输入家庭住址")
    private String stuAddr;

	@NotBlank(message = "请选择乡镇")
    private String stuTownship;

	@NotBlank(message = "请选择村")
    private String stuVillage;

	@NotBlank(message = "请输入家长姓名1")
    private String parentOneName;

	@NotBlank(message = "请输入家长电话1")
    private String parentOnePhone;

    private String parentTwoName;

    private String parentTwoPhone;

    @NotNull(message = "请输入报名预缴费金额")
    private Integer prePaymentAmount;

    @NotNull(message = "请选择是否住校")
    private Integer isResidence;

    @NotNull(message = "请输入缴费金额")
    private Integer paymentAmount;

    @NotNull(message = "请选择是否建立卡贫困户")
    private Integer isPoor;

    @NotNull(message = "请选择是否残疾")
    private Integer isDeformity;
    
    @NotNull(message = "请选择是否单亲")
    private Integer isSignParent;

    @NotNull(message = "请选择是否孤儿")
    private Integer isOrphan;
    
    @NotNull(message = "请选择是否特殊困难")
    private Integer isDifficulty;

    @NotNull(message = "请选择是否留守")
    private Integer isStayBehind;

    @NotNull(message = "请选择是否团员")
    private Integer isLeagueMember;
    
    private String idCardFullFaceImg;
    
    private String idCardReverseSideImg;
    
    private String stuStatusImg;
    
    @NotNull(message = "请输入身高")
    private String height;
    
    @NotNull(message = "请输入体重")
    private String weight;

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public Integer getStuSex() {
		return stuSex;
	}

	public void setStuSex(Integer stuSex) {
		this.stuSex = stuSex;
	}

	public String getStuNation() {
		return stuNation;
	}

	public void setStuNation(String stuNation) {
		this.stuNation = stuNation;
	}

	public String getStuAddr() {
		return stuAddr;
	}

	public void setStuAddr(String stuAddr) {
		this.stuAddr = stuAddr;
	}

	public String getStuTownship() {
		return stuTownship;
	}

	public void setStuTownship(String stuTownship) {
		this.stuTownship = stuTownship;
	}

	public String getStuVillage() {
		return stuVillage;
	}

	public void setStuVillage(String stuVillage) {
		this.stuVillage = stuVillage;
	}

	public String getParentOneName() {
		return parentOneName;
	}

	public void setParentOneName(String parentOneName) {
		this.parentOneName = parentOneName;
	}

	public String getParentOnePhone() {
		return parentOnePhone;
	}

	public void setParentOnePhone(String parentOnePhone) {
		this.parentOnePhone = parentOnePhone;
	}

	public String getParentTwoName() {
		return parentTwoName;
	}

	public void setParentTwoName(String parentTwoName) {
		this.parentTwoName = parentTwoName;
	}

	public String getParentTwoPhone() {
		return parentTwoPhone;
	}

	public void setParentTwoPhone(String parentTwoPhone) {
		this.parentTwoPhone = parentTwoPhone;
	}

	public Integer getPrePaymentAmount() {
		return prePaymentAmount;
	}

	public void setPrePaymentAmount(Integer prePaymentAmount) {
		this.prePaymentAmount = prePaymentAmount;
	}

	public Integer getIsResidence() {
		return isResidence;
	}

	public void setIsResidence(Integer isResidence) {
		this.isResidence = isResidence;
	}

	public Integer getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Integer paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public Integer getIsPoor() {
		return isPoor;
	}

	public void setIsPoor(Integer isPoor) {
		this.isPoor = isPoor;
	}

	public Integer getIsDeformity() {
		return isDeformity;
	}

	public void setIsDeformity(Integer isDeformity) {
		this.isDeformity = isDeformity;
	}

	public Integer getIsOrphan() {
		return isOrphan;
	}

	public void setIsOrphan(Integer isOrphan) {
		this.isOrphan = isOrphan;
	}


	public Integer getIsSignParent() {
		return isSignParent;
	}

	public void setIsSignParent(Integer isSignParent) {
		this.isSignParent = isSignParent;
	}

	public Integer getIsDifficulty() {
		return isDifficulty;
	}

	public void setIsDifficulty(Integer isDifficulty) {
		this.isDifficulty = isDifficulty;
	}

	public Integer getIsStayBehind() {
		return isStayBehind;
	}

	public void setIsStayBehind(Integer isStayBehind) {
		this.isStayBehind = isStayBehind;
	}

	public Integer getIsLeagueMember() {
		return isLeagueMember;
	}

	public void setIsLeagueMember(Integer isLeagueMember) {
		this.isLeagueMember = isLeagueMember;
	}

	public String getIdCardFullFaceImg() {
		return idCardFullFaceImg;
	}

	public void setIdCardFullFaceImg(String idCardFullFaceImg) {
		this.idCardFullFaceImg = idCardFullFaceImg;
	}

	public String getIdCardReverseSideImg() {
		return idCardReverseSideImg;
	}

	public void setIdCardReverseSideImg(String idCardReverseSideImg) {
		this.idCardReverseSideImg = idCardReverseSideImg;
	}

	public String getStuStatusImg() {
		return stuStatusImg;
	}

	public void setStuStatusImg(String stuStatusImg) {
		this.stuStatusImg = stuStatusImg;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

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
    
    
	
    
    

}
