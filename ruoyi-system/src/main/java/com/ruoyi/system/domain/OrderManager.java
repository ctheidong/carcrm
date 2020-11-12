package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 订单管理对象 order_manager
 * 
 * @author chentao
 * @date 2020-08-16
 */
@ApiModel
public class OrderManager extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单ID */
    @ApiModelProperty("订单id")
    private String id;

    /** 日期 */
    @ApiModelProperty(value = "日期")
    @Excel(name = "日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date orderDate;

    /** 时间 */
    @Excel(name = "时间", width = 30)
//    @DateTimeFormat(pattern="HH:mm")
    private String orderTime;

    /** 起点 */
    @ApiModelProperty(value = "起点")
    @Excel(name = "起点")
    private String start;

    /** 终点 */
    @Excel(name = "终点")
    @ApiModelProperty(value = "终点")
    private String end;

    /** 车型 */
    @Excel(name = "车型")
    @ApiModelProperty(value = "车型")
    private String carType;

    /** 车牌号 */
    @Excel(name = "车牌号")
    @ApiModelProperty(value = "车牌号")
    private String carNo;

    /** 客户id */
    @Excel(name = "客户id")
    @ApiModelProperty(value = "客户id")
    private String custmerId;

    /** 客户姓名 */
    @Excel(name = "客户姓名")
    @ApiModelProperty(value = "客户姓名")
    private String custmerName;

    /** 联系电话 */
    @ApiModelProperty(value = "联系电话")
    @Excel(name = "联系电话")
    private String phoneNum;

    /** 收款方式 1：记账 2：司机收款 3公司二维码： */
    @ApiModelProperty(value = "记账金额")
    @Excel(name = "记账金额")
    private double recordMoney;

    @ApiModelProperty(value = "公司二维码收款金额")
    @Excel(name = "公司二维码收款金额")
    private double componyReceiverMoney;

    @ApiModelProperty(value = "司机收款金额")
    @Excel(name = "司机收款金额")
    private double driverReceiverMoney;

    @ApiModelProperty(value = "收款方式 1：记账 2：司机收款 3公司二维码：")
    @Excel(name = "收款方式 1：记账 2：司机收款 3公司二维码：")
    private Integer paymentTerm;
    /** 拖车费 */
    @ApiModelProperty(value = "拖车费")
    @Excel(name = "拖车费")
    private Double towingFee;

    /** 拖车车牌号 */
    @ApiModelProperty(value = "拖车车牌号")
    @Excel(name = "拖车车牌号")
    private String towcarNo;

    /** 提成 */
    @Excel(name = "提成")
    @ApiModelProperty(value = "提成")
    private Double commission;

    /** 杂费 */
    @Excel(name = "杂费")
    @ApiModelProperty(value = "杂费")
    private Double zaFee;

    /** 审批状态 */
    @ApiModelProperty(value = "审批状态 1:待审批 2:通过 3:驳回 4:撤销")
    @Excel(name = "审批状态")
    private Integer approveStatus;

    /** 司机姓名 */
    @ApiModelProperty(value = "司机姓名")
    @Excel(name = "司机姓名")
    private String driverName;

    /** 司机userId */
    @Excel(name = "司机userId")
    private Long userId;

    /** 公司支付金额 */
    @ApiModelProperty(value = "公司支付金额")
    @Excel(name = "公司支付金额")
    private Double companyPay;

    /** 司机回款金额 */
    @ApiModelProperty(value = "司机回款金额")
    @Excel(name = "司机回款金额")
    private Double returnMoney;

    /** 加油金额 */
    @ApiModelProperty(value = "加油金额")
    @Excel(name = "加油金额")
    private Double refuelMoney;

    /** 加油卡加油费 */
    @ApiModelProperty(value = "加油卡加油费")
    @Excel(name = "加油卡加油费")
    private Double refuelcardMoney;

    /** 加油公里数 */
    @ApiModelProperty(value = "加油公里数")
    @Excel(name = "加油公里数")
    private String mileage;

    /** 备注 */
    @ApiModelProperty(value = "备注")
    @Excel(name = "备注")
    private String remark;

    /** 审批备注 */
    @ApiModelProperty(value = "审批备注")
    @Excel(name = "审批备注")
    private String appRemark;
    /** 收款金额 */
    @Excel(name = "回款金额（收款专员回款）")
    @ApiModelProperty(value = "回款金额（收款专员回款）")
    private Double returnAmount;

    /**原客户id*/
    private String oldCustmerId;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setOrderDate(Date orderDate) 
    {
        this.orderDate = orderDate;
    }

    public Date getOrderDate() 
    {
        return orderDate;
    }
    public void setOrderTime(String orderTime)
    {
        this.orderTime = orderTime;
    }

    public String getOrderTime()
    {
        return orderTime;
    }
    public void setStart(String start) 
    {
        this.start = start;
    }

    public String getStart() 
    {
        return start;
    }
    public void setEnd(String end) 
    {
        this.end = end;
    }

    public String getEnd() 
    {
        return end;
    }
    public void setCarType(String carType) 
    {
        this.carType = carType;
    }

    public String getCarType() 
    {
        return carType;
    }
    public void setCarNo(String carNo) 
    {
        this.carNo = carNo;
    }

    public String getCarNo() 
    {
        return carNo;
    }
    public void setCustmerId(String custmerId)
    {
        this.custmerId = custmerId;
    }

    public String getCustmerId()
    {
        return custmerId;
    }
    public void setCustmerName(String custmerName) 
    {
        this.custmerName = custmerName;
    }

    public String getCustmerName() 
    {
        return custmerName;
    }
    public void setPhoneNum(String phoneNum) 
    {
        this.phoneNum = phoneNum;
    }

    public String getPhoneNum() 
    {
        return phoneNum;
    }
    public void setPaymentTerm(Integer paymentTerm) 
    {
        this.paymentTerm = paymentTerm;
    }

    public Integer getPaymentTerm() 
    {
        return paymentTerm;
    }
    public void setTowingFee(Double towingFee) 
    {
        this.towingFee = towingFee;
    }

    public Double getTowingFee() 
    {
        return towingFee;
    }
    public void setTowcarNo(String towcarNo) 
    {
        this.towcarNo = towcarNo;
    }

    public String getTowcarNo() 
    {
        return towcarNo;
    }
    public void setCommission(Double commission) 
    {
        this.commission = commission;
    }

    public Double getCommission() 
    {
        return commission;
    }
    public void setZaFee(Double zaFee) 
    {
        this.zaFee = zaFee;
    }

    public Double getZaFee() 
    {
        return zaFee;
    }
    public void setApproveStatus(Integer approveStatus) 
    {
        this.approveStatus = approveStatus;
    }

    public Integer getApproveStatus() 
    {
        return approveStatus;
    }
    public void setDriverName(String driverName) 
    {
        this.driverName = driverName;
    }

    public String getDriverName() 
    {
        return driverName;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setCompanyPay(Double companyPay) 
    {
        this.companyPay = companyPay;
    }

    public Double getCompanyPay() 
    {
        return companyPay;
    }
    public void setReturnMoney(Double returnMoney) 
    {
        this.returnMoney = returnMoney;
    }

    public Double getReturnMoney() 
    {
        return returnMoney;
    }
    public void setRefuelMoney(Double refuelMoney) 
    {
        this.refuelMoney = refuelMoney;
    }

    public Double getRefuelMoney() 
    {
        return refuelMoney;
    }
    public void setRefuelcardMoney(Double refuelcardMoney) 
    {
        this.refuelcardMoney = refuelcardMoney;
    }

    public Double getRefuelcardMoney() 
    {
        return refuelcardMoney;
    }
    public void setMileage(String mileage) 
    {
        this.mileage = mileage;
    }

    public String getMileage() 
    {
        return mileage;
    }
    public void setAppRemark(String appRemark) 
    {
        this.appRemark = appRemark;
    }

    public String getAppRemark() 
    {
        return appRemark;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }


    public double getRecordMoney() {
        return recordMoney;
    }

    public void setRecordMoney(double recordMoney) {
        this.recordMoney = recordMoney;
    }

    public double getComponyReceiverMoney() {
        return componyReceiverMoney;
    }

    public void setComponyReceiverMoney(double componyReceiverMoney) {
        this.componyReceiverMoney = componyReceiverMoney;
    }

    public double getDriverReceiverMoney() {
        return driverReceiverMoney;
    }

    public void setDriverReceiverMoney(double driverReceiverMoney) {
        this.driverReceiverMoney = driverReceiverMoney;
    }

    public String getOldCustmerId() {
        return oldCustmerId;
    }

    public void setOldCustmerId(String oldCustmerId) {
        this.oldCustmerId = oldCustmerId;
    }

    public Double getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(Double returnAmount) {
        this.returnAmount = returnAmount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderDate", getOrderDate())
            .append("orderTime", getOrderTime())
            .append("start", getStart())
            .append("end", getEnd())
            .append("carType", getCarType())
            .append("carNo", getCarNo())
            .append("custmerId", getCustmerId())
            .append("custmerName", getCustmerName())
            .append("phoneNum", getPhoneNum())
            .append("paymentTerm", getPaymentTerm())
            .append("towingFee", getTowingFee())
            .append("towcarNo", getTowcarNo())
            .append("commission", getCommission())
            .append("zaFee", getZaFee())
            .append("approveStatus", getApproveStatus())
            .append("driverName", getDriverName())
            .append("userId", getUserId())
            .append("companyPay", getCompanyPay())
            .append("returnMoney", getReturnMoney())
            .append("refuelMoney", getRefuelMoney())
            .append("refuelcardMoney", getRefuelcardMoney())
            .append("mileage", getMileage())
            .append("remark", getRemark())
            .append("appRemark", getAppRemark())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
