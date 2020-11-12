package com.ruoyi.system.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户管理对象 custmer
 * 
 * @author chentao
 * @date 2020-08-16
 */
@ApiModel
public class Custmer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @ApiModelProperty("客户id")
    private String id;

    /** 客户名称 */
    @Excel(name = "客户名称")
    @ApiModelProperty("客户名称")
    private String custmerName;

    /** 客户名称-手机号 */
    @ApiModelProperty("客户名称-手机号  用于小程序下拉框进行筛选数据")
    private String custmerNameAndPhone;

    /** 客户名称 */
    @Excel(name = "手机号")
    private String phoneNum;

    /** 订单金额 */
    @Excel(name = "订单金额")
    private double orderMoney;

    /** 记账金额 */
    @Excel(name = "记账金额")
    private double recordMoney;

    /** 已回款金额 */
    @Excel(name = "已回款金额")
    private double returnedMoney;

    /**欠款金额 */
    @Excel(name = "欠款金额")
    private double arrearMoney;

    /**最近一次还款金额 */
    @Excel(name = "最近一次还款金额")
    private double latestMoney;

    /**记账状态 1：有欠款 2：无欠款 */
    @Excel(name = "记账状态 1：有欠款 2：无欠款 ")
    private String status;



    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setCustmerName(String custmerName) 
    {
        this.custmerName = custmerName;
    }

    public String getCustmerName() 
    {
        return custmerName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public double getRecordMoney() {
        return recordMoney;
    }

    public void setRecordMoney(double recordMoney) {
        this.recordMoney = recordMoney;
    }

    public double getReturnedMoney() {
        return returnedMoney;
    }

    public void setReturnedMoney(double returnedMoney) {
        this.returnedMoney = returnedMoney;
    }

    public double getArrearMoney() {
        return arrearMoney;
    }

    public void setArrearMoney(double arrearMoney) {
        this.arrearMoney = arrearMoney;
    }

    public double getLatestMoney() {
        return latestMoney;
    }

    public void setLatestMoney(double latestMoney) {
        this.latestMoney = latestMoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustmerNameAndPhone() {
        return custmerNameAndPhone;
    }

    public void setCustmerNameAndPhone(String custmerNameAndPhone) {
        this.custmerNameAndPhone = custmerNameAndPhone;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("custmerName", getCustmerName())
            .append("createTime", getCreateTime())
            .append("remark", getRemark())
            .toString();
    }
}
