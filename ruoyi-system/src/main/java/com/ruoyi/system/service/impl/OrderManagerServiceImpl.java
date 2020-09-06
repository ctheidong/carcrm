package com.ruoyi.system.service.impl;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.Custmer;
import com.ruoyi.system.domain.OrderManager;
import com.ruoyi.system.mapper.OrderManagerMapper;
import com.ruoyi.system.service.IOrderManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单管理Service业务层处理
 * 
 * @author chentao
 * @date 2020-08-16
 */
@Service
public class OrderManagerServiceImpl implements IOrderManagerService 
{
    @Autowired
    private OrderManagerMapper orderManagerMapper;

    /**
     * 查询订单管理
     * 
     * @param id 订单管理ID
     * @return 订单管理
     */
    @Override
    public OrderManager selectOrderManagerById(String id)
    {
        return orderManagerMapper.selectOrderManagerById(id);
    }

    /**
     * 查询订单管理列表
     * 
     * @param orderManager 订单管理
     * @return 订单管理
     */
    @Override
    public List<OrderManager> selectOrderManagerList(OrderManager orderManager)
    {
        return orderManagerMapper.selectOrderManagerList(orderManager);
    }

    /**
     * 新增订单管理
     * 
     * @param orderManager 订单管理
     * @return 结果
     */
    @Override
    public int insertOrderManager(OrderManager orderManager)
    {
        return orderManagerMapper.insertOrderManager(orderManager);
    }

    /**
     * 修改订单管理
     * 
     * @param orderManager 订单管理
     * @return 结果
     */
    @Override
    public int updateOrderManager(OrderManager orderManager)
    {
        orderManager.setUpdateTime(DateUtils.getNowDate());
        return orderManagerMapper.updateOrderManager(orderManager);
    }

    /**
     * 删除订单管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOrderManagerByIds(String ids)
    {
        return orderManagerMapper.deleteOrderManagerByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除订单管理信息
     * 
     * @param id 订单管理ID
     * @return 结果
     */
    @Override
    public int deleteOrderManagerById(Long id)
    {
        return orderManagerMapper.deleteOrderManagerById(id);
    }

    @Override
    public List<Double> selectRefuelByMonth() {
        return orderManagerMapper.selectRefuelByMonth();
    }

    @Override
    public List<Double> selectReturnMoneyByMonth() {
        return orderManagerMapper.selectReturnMoneyByMonth();
    }

    @Override
    public List<Double> selectCommissionMoneyByMonth() {
        return orderManagerMapper.selectCommissionMoneyByMonth();
    }

    @Override
    public List<Double> selectIncomeMoneyByMonth() {
        return orderManagerMapper.selectIncomeMoneyByMonth();
    }

    @Override
    public String checkCustmerIdUnique(String custmerId)
    {
        int count = orderManagerMapper.checkCustmerIdUnique(custmerId);
        if (count > 0)
        {
            return UserConstants.USER_NAME_NOT_UNIQUE;
        }
        return UserConstants.USER_NAME_UNIQUE;
    }

    @Override
    public List<Custmer> selectCustmerListTj(Custmer custmer) {
        return orderManagerMapper.selectCustmerListTj(custmer);
    }
}
