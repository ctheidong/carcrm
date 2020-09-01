package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Custmer;

/**
 * 客户管理Service接口
 * 
 * @author chentao
 * @date 2020-08-16
 */
public interface ICustmerService 
{
    /**
     * 查询客户管理
     * 
     * @param id 客户管理ID
     * @return 客户管理
     */
    public Custmer selectCustmerById(String id);

    /**
     * 查询客户管理列表
     * 
     * @param custmer 客户管理
     * @return 客户管理集合
     */
    public List<Custmer> selectCustmerList(Custmer custmer);

    /**
     * 新增客户管理
     * 
     * @param custmer 客户管理
     * @return 结果
     */
    public int insertCustmer(Custmer custmer);

    /**
     * 修改客户管理
     * 
     * @param custmer 客户管理
     * @return 结果
     */
    public int updateCustmer(Custmer custmer);

    /**
     * 批量删除客户管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCustmerByIds(String ids);

    /**
     * 删除客户管理信息
     * 
     * @param id 客户管理ID
     * @return 结果
     */
    public int deleteCustmerById(String id);
}
