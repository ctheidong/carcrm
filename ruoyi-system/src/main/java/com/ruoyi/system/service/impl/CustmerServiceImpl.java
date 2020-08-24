package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CustmerMapper;
import com.ruoyi.system.domain.Custmer;
import com.ruoyi.system.service.ICustmerService;
import com.ruoyi.common.core.text.Convert;

/**
 * 客户管理Service业务层处理
 * 
 * @author chentao
 * @date 2020-08-16
 */
@Service
public class CustmerServiceImpl implements ICustmerService 
{
    @Autowired
    private CustmerMapper custmerMapper;

    /**
     * 查询客户管理
     * 
     * @param id 客户管理ID
     * @return 客户管理
     */
    @Override
    public Custmer selectCustmerById(Long id)
    {
        return custmerMapper.selectCustmerById(id);
    }

    /**
     * 查询客户管理列表
     * 
     * @param custmer 客户管理
     * @return 客户管理
     */
    @Override
    public List<Custmer> selectCustmerList(Custmer custmer)
    {
        return custmerMapper.selectCustmerList(custmer);
    }

    /**
     * 新增客户管理
     * 
     * @param custmer 客户管理
     * @return 结果
     */
    @Override
    public int insertCustmer(Custmer custmer)
    {
        custmer.setCreateTime(DateUtils.getNowDate());
        return custmerMapper.insertCustmer(custmer);
    }

    /**
     * 修改客户管理
     * 
     * @param custmer 客户管理
     * @return 结果
     */
    @Override
    public int updateCustmer(Custmer custmer)
    {
        return custmerMapper.updateCustmer(custmer);
    }

    /**
     * 删除客户管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCustmerByIds(String ids)
    {
        return custmerMapper.deleteCustmerByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除客户管理信息
     * 
     * @param id 客户管理ID
     * @return 结果
     */
    @Override
    public int deleteCustmerById(Long id)
    {
        return custmerMapper.deleteCustmerById(id);
    }
}
