package com.ruoyi.system.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 拖车管理对象 car
 * 
 * @author ruoyi
 * @date 2020-08-16
 */
@ApiModel
public class Car extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @ApiModelProperty(name = "id",value = "车牌id")
    private Long id;

    /** 车牌号 */
    @ApiModelProperty(name = "carNo",value = "车牌号")
    @Excel(name = "车牌号")
    private String carNo;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCarNo(String carNo) 
    {
        this.carNo = carNo;
    }

    public String getCarNo() 
    {
        return carNo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("carNo", getCarNo())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
