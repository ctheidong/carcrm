package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.OrderManager;

import java.util.List;

/**
 * 订单管理Mapper接口
 * 
 * @author chentao
 * @date 2020-08-16
 */
public interface OrderManagerMapper 
{
    /**
     * 查询订单管理
     * 
     * @param id 订单管理ID
     * @return 订单管理
     */
    public OrderManager selectOrderManagerById(Long id);

    /**
     * 查询订单管理列表
     * 
     * @param orderManager 订单管理
     * @return 订单管理集合
     */
    public List<OrderManager> selectOrderManagerList(OrderManager orderManager);
    /**
     * 统计加油金额数据
     *
     * @param
     * @return 订单管理集合
     */
    public List<Double> selectRefuelByMonth();
    /**
     * 统计司机回款金额数据
     *
     * @param
     * @return 订单管理集合
     */
    public List<Double> selectReturnMoneyByMonth();

    /**
     * 统计司机提成金额数据
     *
     * @param
     * @return 订单管理集合
     */
    public List<Double> selectCommissionMoneyByMonth();
    /**
     * 统计公司利润数据
     *
     * @param
     * @return 订单管理集合
     */
    public List<Double> selectIncomeMoneyByMonth();

    /**
     * 新增订单管理
     * 
     * @param orderManager 订单管理
     * @return 结果
     */
    public int insertOrderManager(OrderManager orderManager);

    /**
     * 修改订单管理
     * 
     * @param orderManager 订单管理
     * @return 结果
     */
    public int updateOrderManager(OrderManager orderManager);

    /**
     * 删除订单管理
     * 
     * @param id 订单管理ID
     * @return 结果
     */
    public int deleteOrderManagerById(Long id);

    /**
     * 批量删除订单管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOrderManagerByIds(String[] ids);
}