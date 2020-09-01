package com.ruoyi.web.controller.car;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Car;
import com.ruoyi.system.module.utils.Authorize;
import com.ruoyi.system.service.ICarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 拖车管理Controller
 * 
 * @author ruoyi
 * @date 2020-08-16
 */
@Controller
@RequestMapping("/car/car")
@Api(tags ="车辆信息模块")
public class CarController extends BaseController
{
    private String prefix = "car/car";

    @Autowired
    private ICarService carService;

    @RequiresPermissions("car:car:view")
    @GetMapping()
    public String car()
    {
        return prefix + "/car";
    }

    /**
     * 查询拖车管理列表
     */
    @RequiresPermissions("car:car:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Car car)
    {
        startPage();
        List<Car> list = carService.selectCarList(car);
        return getDataTable(list);
    }
    /**
     * 小程序查询拖车管理列表
     */
    @ApiOperation(value = "拖车车牌号查询列表",httpMethod = "POST")
    @ApiImplicitParam(name = "Car",value = "{}",defaultValue = "{} 默认传空对象")
    @Authorize
    @PostMapping("/getlist")
    @ResponseBody
    public TableDataInfo getlist(Car car)
    {
        startPage();
        List<Car> list = carService.selectCarList(car);
        return getDataTable(list);
    }
    /**
     * 导出拖车管理列表
     */
    @RequiresPermissions("car:car:export")
    @Log(title = "拖车管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Car car)
    {
        List<Car> list = carService.selectCarList(car);
        ExcelUtil<Car> util = new ExcelUtil<Car>(Car.class);
        return util.exportExcel(list, "car");
    }

    /**
     * 新增拖车管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存拖车管理
     */
    @RequiresPermissions("car:car:add")
    @Log(title = "拖车管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Car car)
    {
        return toAjax(carService.insertCar(car));
    }

    /**
     * 修改拖车管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Car car = carService.selectCarById(id);
        mmap.put("car", car);
        return prefix + "/edit";
    }

    /**
     * 修改保存拖车管理
     */
    @RequiresPermissions("car:car:edit")
    @Log(title = "拖车管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Car car)
    {
        return toAjax(carService.updateCar(car));
    }

    /**
     * 删除拖车管理
     */
    @RequiresPermissions("car:car:remove")
    @Log(title = "拖车管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(carService.deleteCarByIds(ids));
    }
}
