package com.wondersgroup.tpa.model;

import java.io.Serializable;
import java.util.Date;

public class TpaHospital implements Serializable{

    private static final long serialVersionUID = 6532260115816910848L;

    private Long id;

    private String code;

    private String name;

    private Boolean isHeadquarters;

    private Byte type;

    private String specialType;

    private String specialTypeDesc;

    private String economicStyle;

    private String medicalInsuranceType;

    private String level;

    private String qualifications;

    private String areaNumber;

    private String areaName;

    private String address;

    private String contract;

    private String phone;

    private Date startTime;

    private Date endTime;

    private Date createTime;

    private Date updateTime;

    private Boolean status;

    private String spellFirstLetter;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Boolean getIsHeadquarters() {
        return isHeadquarters;
    }

    public void setIsHeadquarters(Boolean isHeadquarters) {
        this.isHeadquarters = isHeadquarters;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getSpecialType() {
        return specialType;
    }

    public void setSpecialType(String specialType) {
        this.specialType = specialType == null ? null : specialType.trim();
    }

    public String getSpecialTypeDesc() {
        return specialTypeDesc;
    }

    public void setSpecialTypeDesc(String specialTypeDesc) {
        this.specialTypeDesc = specialTypeDesc == null ? null : specialTypeDesc.trim();
    }

    public String getEconomicStyle() {
        return economicStyle;
    }

    public void setEconomicStyle(String economicStyle) {
        this.economicStyle = economicStyle == null ? null : economicStyle.trim();
    }

    public String getMedicalInsuranceType() {
        return medicalInsuranceType;
    }

    public void setMedicalInsuranceType(String medicalInsuranceType) {
        this.medicalInsuranceType = medicalInsuranceType == null ? null : medicalInsuranceType.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications == null ? null : qualifications.trim();
    }

    public String getAreaNumber() {
        return areaNumber;
    }

    public void setAreaNumber(String areaNumber) {
        this.areaNumber = areaNumber == null ? null : areaNumber.trim();
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract == null ? null : contract.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getSpellFirstLetter() {
        return spellFirstLetter;
    }

    public void setSpellFirstLetter(String spellFirstLetter) {
        this.spellFirstLetter = spellFirstLetter == null ? null : spellFirstLetter.trim();
    }
}