package com.mywjdh.logistics.logisticswechat.domain;

import java.util.Date;

public class Order {
    private Integer id;

    private Integer userId;

    private String sourceName;

    private String sourceTel;

    private String sourceAddress;

    private String tarName;

    private String tarTel;

    private String tarAddress;

    private String reqTime;

    private String name;

    private String tiji;

    private String zhongliang;

    private String pack;

    private String status;

    private Date createDate;

    private Date updateDate;

    private String remarks;

    private String delFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName == null ? null : sourceName.trim();
    }

    public String getSourceTel() {
        return sourceTel;
    }

    public void setSourceTel(String sourceTel) {
        this.sourceTel = sourceTel == null ? null : sourceTel.trim();
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress == null ? null : sourceAddress.trim();
    }

    public String getTarName() {
        return tarName;
    }

    public void setTarName(String tarName) {
        this.tarName = tarName == null ? null : tarName.trim();
    }

    public String getTarTel() {
        return tarTel;
    }

    public void setTarTel(String tarTel) {
        this.tarTel = tarTel == null ? null : tarTel.trim();
    }

    public String getTarAddress() {
        return tarAddress;
    }

    public void setTarAddress(String tarAddress) {
        this.tarAddress = tarAddress == null ? null : tarAddress.trim();
    }

    public String getReqTime() {
        return reqTime;
    }

    public void setReqTime(String reqTime) {
        this.reqTime = reqTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTiji() {
        return tiji;
    }

    public void setTiji(String tiji) {
        this.tiji = tiji == null ? null : tiji.trim();
    }

    public String getZhongliang() {
        return zhongliang;
    }

    public void setZhongliang(String zhongliang) {
        this.zhongliang = zhongliang == null ? null : zhongliang.trim();
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack == null ? null : pack.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", sourceName=" + sourceName + ", sourceTel=" + sourceTel
				+ ", sourceAddress=" + sourceAddress + ", tarName=" + tarName + ", tarTel=" + tarTel + ", tarAddress="
				+ tarAddress + ", reqTime=" + reqTime + ", name=" + name + ", tiji=" + tiji + ", zhongliang="
				+ zhongliang + ", pack=" + pack + ", status=" + status + ", createDate=" + createDate + ", updateDate="
				+ updateDate + ", remarks=" + remarks + ", delFlag=" + delFlag + "]";
	}
    
    
}