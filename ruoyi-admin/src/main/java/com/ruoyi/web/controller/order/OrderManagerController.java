package com.ruoyi.web.controller.order;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.OrderManager;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.module.utils.Authorize;
import com.ruoyi.system.service.IOrderManagerService;
import com.ruoyi.system.service.ISysDictDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单管理Controller
 * 
 * @author chentao
 * @date 2020-08-16
 */
@Controller
@RequestMapping("/order/manager")
@Api(tags = "订单模块接口信息")
public class OrderManagerController extends BaseController
{
    private String prefix = "order/manager";

    @Autowired
    private IOrderManagerService orderManagerService;
    @Autowired
    private ISysDictDataService dataService;
    /**
     * 小程序查询订单管理列表
     */
    @Authorize
    @ApiOperation(value = "查询付款方式列表" ,httpMethod = "get")
    @ApiImplicitParam(defaultValue = "不用传参")
    @GetMapping("/getTypeList")
    @ResponseBody
    public TableDataInfo getTypeList()
    {
        startPage();
        List<SysDictData> list = dataService.selectDictData();
        return getDataTable(list);
    }

    @RequiresPermissions("order:manager:view")
    @GetMapping()
    public String manager()
    {
        return prefix + "/manager";
    }

    /**
     * 查询订单管理列表
     */

    @RequiresPermissions("order:manager:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OrderManager orderManager)
    {
        startPage();
        List<OrderManager> list = orderManagerService.selectOrderManagerList(orderManager);
        return getDataTable(list);
    }
    /**
     * 小程序查询订单管理列表
     */
    @Authorize
    @ApiOperation(value = "查询订单管理列表" ,httpMethod = "post")
    @ApiImplicitParam(name = "userId",value = "用户userId",required = true)
    @PostMapping("/getList")
    @ResponseBody
    public TableDataInfo getList(@RequestBody  OrderManager orderManager)
    {
        startPage();
        List<OrderManager> list = orderManagerService.selectOrderManagerList(orderManager);
        return getDataTable(list);
    }
    /**
     * 导出订单管理列表
     */

    @RequiresPermissions("order:manager:export")
    @Log(title = "订单管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OrderManager orderManager)
    {
        List<OrderManager> list = orderManagerService.selectOrderManagerList(orderManager);
        ExcelUtil<OrderManager> util = new ExcelUtil<OrderManager>(OrderManager.class);
        return util.exportExcel(list, "manager");
    }

    /**
     * 新增订单管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存订单管理
     */
    @RequiresPermissions("order:manager:add")
    @Log(title = "订单管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OrderManager orderManager)
    {
        //订单编号为当前插入日期
        orderManager.setId(DateUtils.parseDateToStr("yyyymmdd",DateUtils.getNowDate()));
        return toAjax(orderManagerService.insertOrderManager(orderManager));
    }
    /**
     * 小程序新增保存订单管理
     */
    @ApiOperation(value = "新增订单",httpMethod = "post")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderDate" ,value = "日期",required = true),
            @ApiImplicitParam(name = "start" ,value = "起点",required = true),
            @ApiImplicitParam(name = "end" ,value = "终点",required = true),
            @ApiImplicitParam(name = "carType" ,value = "车型",required = true),
            @ApiImplicitParam(name = "carNo" ,value = "车牌号",required = true),
            @ApiImplicitParam(name = "custmerId" ,value = "客户id",required = true),
            @ApiImplicitParam(name = "custmerName" ,value = "客户姓名",required = true),
            @ApiImplicitParam(name = "phoneNum" ,value = "联系电话",required = true),
            @ApiImplicitParam(name = "paymentTerm" ,value = "收款方式 1：记账 2：司机收款 3公司二维码：",required = true),
            @ApiImplicitParam(name = "towingFee" ,value = "拖车费",required = true),
            @ApiImplicitParam(name = "towcarNo" ,value = "拖车车牌号",required = true),
            @ApiImplicitParam(name = "commission" ,value = "提成",required = true),
            @ApiImplicitParam(name = "zaFee" ,value = "杂费",required = true),
            @ApiImplicitParam(name = "approveStatus" ,value = "审批状态 1:待审批 2:通过 3:驳回 4:撤销"),
            @ApiImplicitParam(name = "driverName" ,value = "司机姓名" ,required = true),
            @ApiImplicitParam(name = "userId" ,value = "司机userId",required = true),
            @ApiImplicitParam(name = "companyPay" ,value = "公司支付金额"),
            @ApiImplicitParam(name = "returnMoney" ,value = "司机回款金额"),
            @ApiImplicitParam(name = "refuelMoney" ,value = "加油金额"),
            @ApiImplicitParam(name = "refuelcardMoney" ,value = "加油卡加油费"),
            @ApiImplicitParam(name = "mileage" ,value = "加油公里数"),
            @ApiImplicitParam(name = "appRemark" ,value = "审批备注"),
    })
    @Log(title = "订单管理", businessType = BusinessType.INSERT)
    @PostMapping("/minAdd")
    @Authorize
    @ResponseBody
    public AjaxResult minAddSave(OrderManager orderManager)
    {
        //订单编号为当前插入日期
        orderManager.setId(DateUtils.parseDateToStr("yyyymmdd",DateUtils.getNowDate()));
        return toAjax(orderManagerService.insertOrderManager(orderManager));
    }
    /**
     * 小程序订单详情
     */
    @ApiOperation(value = "订单详情接口" ,httpMethod = "get")
    @ApiImplicitParam(name = "id",value = "订单id",required = true)
    @GetMapping("/minDetail/{id}")
    public AjaxResult minDetail(@PathVariable("id") Long id)
    {
        OrderManager orderManager = orderManagerService.selectOrderManagerById(id);

        return AjaxResult.success(orderManager);
    }
    /**
     * 小程序撤销订单
     */
//    @RequiresPermissions("order:manager:edit")
    @ApiOperation(value = "撤销订单",httpMethod = "post")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "订单id",required = true),
            @ApiImplicitParam(name = "approveStatus",value = "审批状态  1:待审批 2:通过 3:驳回 4:撤销 ",required = true)
    })
    @Log(title = "订单管理", businessType = BusinessType.UPDATE)
    @PostMapping("/revocation")
    @ResponseBody
    public AjaxResult revocation(@RequestBody  OrderManager orderManager)
    {
        return toAjax(orderManagerService.updateOrderManager(orderManager));
    }
    /**
     * 修改订单管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OrderManager orderManager = orderManagerService.selectOrderManagerById(id);
        mmap.put("orderManager", orderManager);
        return prefix + "/edit";
    }
    /**
     * 订单详情
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap)
    {
        OrderManager orderManager = orderManagerService.selectOrderManagerById(id);
        mmap.put("orderManager", orderManager);
        return prefix + "/detail";
    }
    /**
     * 订单审批
     */
    @GetMapping("/approve/{id}")
    public String approve(@PathVariable("id") Long id, ModelMap mmap)
    {
        OrderManager orderManager = orderManagerService.selectOrderManagerById(id);
        mmap.put("orderManager", orderManager);
        return prefix + "/approve";
    }
    /**
     * 审批保存订单管理
     */
    @RequiresPermissions("order:manager:edit")
    @Log(title = "订单管理", businessType = BusinessType.UPDATE)
    @PostMapping("/approve")
    @ResponseBody
    public AjaxResult approve(OrderManager orderManager)
    {
        return toAjax(orderManagerService.updateOrderManager(orderManager));
    }

    /**
     * 修改保存订单管理
     */
    @RequiresPermissions("order:manager:edit")
    @Log(title = "订单管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OrderManager orderManager)
    {
        return toAjax(orderManagerService.updateOrderManager(orderManager));
    }

    /**
     * 删除订单管理
     */
    @RequiresPermissions("order:manager:remove")
    @Log(title = "订单管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(orderManagerService.deleteOrderManagerByIds(ids));
    }
}
