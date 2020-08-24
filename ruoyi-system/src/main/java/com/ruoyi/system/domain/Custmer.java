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
    private Long id;

    /** 客户名称 */
    @Excel(name = "客户名称")
    @ApiModelProperty("客户名称")
    private String custmerName;
    /** 客户名称 */
    @Excel(name = "手机号")
    private String phoneNum;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
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
