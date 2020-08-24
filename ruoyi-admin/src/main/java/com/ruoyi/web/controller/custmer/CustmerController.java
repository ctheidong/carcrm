package com.ruoyi.web.controller.custmer;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Custmer;
import com.ruoyi.system.module.utils.Authorize;
import com.ruoyi.system.service.ICustmerService;
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
 * 客户管理Controller
 * 
 * @author chentao
 * @date 2020-08-16
 */
@Controller
@RequestMapping("/custmer/custmer")
@Api(tags = "客户信息模块")
public class CustmerController extends BaseController
{
    private String prefix = "custmer/custmer";

    @Autowired
    private ICustmerService custmerService;

    @RequiresPermissions("custmer:custmer:view")
    @GetMapping()
    public String custmer()
    {
        return prefix + "/custmer";
    }

    /**
     * 查询客户管理列表
     */
    @RequiresPermissions("custmer:custmer:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Custmer custmer)
    {
        startPage();
        List<Custmer> list = custmerService.selectCustmerList(custmer);
        return getDataTable(list);
    }
    /**
     * 小程序查询客户管理列表
     */
    @ApiOperation(value = "获取客户列表接口",httpMethod = "post")
    @ApiImplicitParam(example = "{}",defaultValue = "{}",required = true)
    @PostMapping("/getlist")
    @ResponseBody
    @Authorize
    public TableDataInfo getlist(@RequestBody Custmer custmer)
    {
        startPage();
        List<Custmer> list = custmerService.selectCustmerList(custmer);
        return getDataTable(list);
    }

    /**
     * 导出客户管理列表
     */
    @RequiresPermissions("custmer:custmer:export")
    @Log(title = "客户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Custmer custmer)
    {
        List<Custmer> list = custmerService.selectCustmerList(custmer);
        ExcelUtil<Custmer> util = new ExcelUtil<Custmer>(Custmer.class);
        return util.exportExcel(list, "custmer");
    }

    /**
     * 新增客户管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存客户管理
     */
    @RequiresPermissions("custmer:custmer:add")
    @Log(title = "客户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Custmer custmer)
    {
        return toAjax(custmerService.insertCustmer(custmer));
    }

    /**
     * 修改客户管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Custmer custmer = custmerService.selectCustmerById(id);
        mmap.put("custmer", custmer);
        return prefix + "/edit";
    }

    /**
     * 修改保存客户管理
     */
    @RequiresPermissions("custmer:custmer:edit")
    @Log(title = "客户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Custmer custmer)
    {
        return toAjax(custmerService.updateCustmer(custmer));
    }

    /**
     * 删除客户管理
     */
    @RequiresPermissions("custmer:custmer:remove")
    @Log(title = "客户管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(custmerService.deleteCustmerByIds(ids));
    }
}
