package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CarMapper;
import com.ruoyi.system.domain.Car;
import com.ruoyi.system.service.ICarService;
import com.ruoyi.common.core.text.Convert;

/**
 * 拖车管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-08-16
 */
@Service
public class CarServiceImpl implements ICarService 
{
    @Autowired
    private CarMapper carMapper;

    /**
     * 查询拖车管理
     * 
     * @param id 拖车管理ID
     * @return 拖车管理
     */
    @Override
    public Car selectCarById(Long id)
    {
        return carMapper.selectCarById(id);
    }

    /**
     * 查询拖车管理列表
     * 
     * @param car 拖车管理
     * @return 拖车管理
     */
    @Override
    public List<Car> selectCarList(Car car)
    {
        return carMapper.selectCarList(car);
    }

    /**
     * 新增拖车管理
     * 
     * @param car 拖车管理
     * @return 结果
     */
    @Override
    public int insertCar(Car car)
    {
        car.setCreateTime(DateUtils.getNowDate());
        return carMapper.insertCar(car);
    }

    /**
     * 修改拖车管理
     * 
     * @param car 拖车管理
     * @return 结果
     */
    @Override
    public int updateCar(Car car)
    {
        car.setUpdateTime(DateUtils.getNowDate());
        return carMapper.updateCar(car);
    }

    /**
     * 删除拖车管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCarByIds(String ids)
    {
        return carMapper.deleteCarByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除拖车管理信息
     * 
     * @param id 拖车管理ID
     * @return 结果
     */
    @Override
    public int deleteCarById(Long id)
    {
        return carMapper.deleteCarById(id);
    }
}
