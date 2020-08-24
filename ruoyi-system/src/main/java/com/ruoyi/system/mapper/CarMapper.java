package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Car;

/**
 * 拖车管理Mapper接口
 * 
 * @author ruoyi
 * @date 2020-08-16
 */
public interface CarMapper 
{
    /**
     * 查询拖车管理
     * 
     * @param id 拖车管理ID
     * @return 拖车管理
     */
    public Car selectCarById(Long id);

    /**
     * 查询拖车管理列表
     * 
     * @param car 拖车管理
     * @return 拖车管理集合
     */
    public List<Car> selectCarList(Car car);

    /**
     * 新增拖车管理
     * 
     * @param car 拖车管理
     * @return 结果
     */
    public int insertCar(Car car);

    /**
     * 修改拖车管理
     * 
     * @param car 拖车管理
     * @return 结果
     */
    public int updateCar(Car car);

    /**
     * 删除拖车管理
     * 
     * @param id 拖车管理ID
     * @return 结果
     */
    public int deleteCarById(Long id);

    /**
     * 批量删除拖车管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCarByIds(String[] ids);
}
