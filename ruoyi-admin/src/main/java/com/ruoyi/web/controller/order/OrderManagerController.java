package com.ruoyi.web.controller.order;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Custmer;
import com.ruoyi.system.domain.OrderManager;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.module.utils.Authorize;
import com.ruoyi.system.service.ICustmerService;
import com.ruoyi.system.service.IOrderManagerService;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.utils.DowlonFileUtils;
import com.ruoyi.system.utils.ResultDemo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
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
public class OrderManagerController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(OrderManagerController.class);
    private String prefix = "order/manager";
    @Value("${excel.tempatePath}")
    private String tempatePath;
    @Autowired
    private IOrderManagerService orderManagerService;
    @Autowired
    private ISysDictDataService dataService;
    @Autowired
    private ICustmerService custmerService;

    /**
     * 小程序查询付款方式列表
     */
    @Authorize
    @ApiOperation(value = "查询付款方式列表", httpMethod = "GET")
    @ApiImplicitParam(defaultValue = "不用传参")
    @PostMapping("/getTypeList")
    @ResponseBody
    public TableDataInfo getTypeList() {
        startPage();
        List<SysDictData> list = dataService.selectDictData();
        return getDataTable(list);
    }

    @RequiresPermissions("order:manager:view")
    @GetMapping()
    public String manager() {
        return prefix + "/manager";
    }

    /**
     * 查询订单管理列表
     */

    @RequiresPermissions("order:manager:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OrderManager orderManager) {
        startPage();
        List<OrderManager> list = orderManagerService.selectOrderManagerList(orderManager);
        return getDataTable(list);
    }

    /**
     * 小程序查询订单管理列表
     */
    @Authorize
    @ApiOperation(value = "查询订单管理列表", httpMethod = "POST")
    @ApiImplicitParam(name = "userId", value = "用户userId", required = true)
    @PostMapping("/getList")
    @ResponseBody
    public TableDataInfo getList(@RequestBody OrderManager orderManager) {
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
    public AjaxResult export(OrderManager orderManager) {
        List<OrderManager> list = orderManagerService.selectOrderManagerList(orderManager);
        ExcelUtil<OrderManager> util = new ExcelUtil<OrderManager>(OrderManager.class);
        return util.exportExcel(list, "manager");
    }

    /**
     * 新增订单管理
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存订单管理
     */
    @RequiresPermissions("order:manager:add")
    @Log(title = "订单管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OrderManager orderManager) {
        //订单编号为当前插入日期
        orderManager.setId(DateUtils.parseDateToStr("yyyymmdd", DateUtils.getNowDate()));
        return toAjax(orderManagerService.insertOrderManager(orderManager));
    }

    /**
     * 小程序新增保存订单管理
     */
    @ApiOperation(value = "新增订单", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderDate", value = "日期", required = true),
            @ApiImplicitParam(name = "start", value = "起点", required = true),
            @ApiImplicitParam(name = "end", value = "终点", required = true),
            @ApiImplicitParam(name = "carType", value = "车型", required = true),
            @ApiImplicitParam(name = "carNo", value = "车牌号", required = true),
            @ApiImplicitParam(name = "custmerId", value = "客户id", required = true),
            @ApiImplicitParam(name = "custmerName", value = "客户姓名", required = true),
            @ApiImplicitParam(name = "phoneNum", value = "联系电话", required = true),
            @ApiImplicitParam(name = "paymentTerm", value = "收款方式 1：记账 2：司机收款 3公司二维码：", required = true),
            @ApiImplicitParam(name = "towingFee", value = "拖车费", required = true),
            @ApiImplicitParam(name = "towcarNo", value = "拖车车牌号", required = true),
            @ApiImplicitParam(name = "commission", value = "提成", required = true),
            @ApiImplicitParam(name = "zaFee", value = "杂费", required = true),
            @ApiImplicitParam(name = "approveStatus", value = "审批状态 1:待审批 2:通过 3:驳回 4:撤销"),
            @ApiImplicitParam(name = "driverName", value = "司机姓名", required = true),
            @ApiImplicitParam(name = "userId", value = "司机userId", required = true),
            @ApiImplicitParam(name = "companyPay", value = "公司支付金额"),
            @ApiImplicitParam(name = "returnMoney", value = "司机回款金额"),
            @ApiImplicitParam(name = "refuelMoney", value = "加油金额"),
            @ApiImplicitParam(name = "refuelcardMoney", value = "加油卡加油费"),
            @ApiImplicitParam(name = "mileage", value = "加油公里数"),
            @ApiImplicitParam(name = "appRemark", value = "审批备注"),
    })
    @Log(title = "订单管理", businessType = BusinessType.INSERT)
    @PostMapping("/minAdd")
    @Authorize
    @ResponseBody
    public TableDataInfo minAddSave(@RequestBody OrderManager orderManager) {
        //订单编号为当前插入日期
        orderManager.setId(DateUtils.parseDateToStr("yyyymmdd", DateUtils.getNowDate()));
        int result = orderManagerService.insertOrderManager(orderManager);
        if (result > 0) {
            return ResultDemo.resultSuccess("操作成功！");
        }
        return ResultDemo.resultError("操作失败！");
    }

    /**
     * 小程序订单详情
     */
    @ApiOperation(value = "订单详情接口", httpMethod = "POST")
    @ApiImplicitParam(name = "id", value = "订单id", required = true)
    @PostMapping("/minDetail")
    @Authorize
    @ResponseBody
    public TableDataInfo minDetail(@RequestBody OrderManager param) {
        try {
            List<OrderManager> list = new LinkedList<>();
            OrderManager orderManager = orderManagerService.selectOrderManagerById(param.getId());
            list.add(orderManager);
            return getDataTable(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDemo.resultError("请求异常!" + e.getMessage());
        }
    }

    /**
     * 小程序撤销订单
     */
//    @RequiresPermissions("order:manager:edit")
    @ApiOperation(value = "撤销订单", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单id", required = true),
            @ApiImplicitParam(name = "approveStatus", value = "审批状态  1:待审批 2:通过 3:驳回 4:撤销 ", required = true)
    })
    @Log(title = "订单管理", businessType = BusinessType.UPDATE)
    @PostMapping("/revocation")
    @ResponseBody
    public TableDataInfo revocation(@RequestBody OrderManager orderManager) {
        int i = orderManagerService.updateOrderManager(orderManager);
        if (i > 0) {
            return ResultDemo.resultSuccess("操作成功！");
        }
        return ResultDemo.resultError("操作失败！");
    }

    /**
     * 修改订单管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        OrderManager orderManager = orderManagerService.selectOrderManagerById(id);
        mmap.put("orderManager", orderManager);
        mmap.put("oldCustmerId", orderManager.getCustmerId());
        return prefix + "/edit";
    }

    /**
     * 订单详情
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id, ModelMap mmap) {
        OrderManager orderManager = orderManagerService.selectOrderManagerById(id);
        mmap.put("orderManager", orderManager);
        return prefix + "/detail";
    }

    /**
     * 订单审批
     */
    @GetMapping("/approve/{id}")
    public String approve(@PathVariable("id") String id, ModelMap mmap) {
        OrderManager orderManager = orderManagerService.selectOrderManagerById(id);
        mmap.put("orderManager", orderManager);
        mmap.put("oldCustmerId", orderManager.getCustmerId());
        return prefix + "/approve";
    }

    /**
     * 审批保存订单管理
     */
    @SuppressWarnings("all")
    @RequiresPermissions("order:manager:edit")
    @Log(title = "订单管理", businessType = BusinessType.UPDATE)
    @PostMapping("/approve")
    @ResponseBody
    public AjaxResult approve(OrderManager orderManager) {
        if (!orderManager.getCustmerId().equals(orderManager.getOldCustmerId())) {//保存时判断客户id与传入时的客户id是否相同，如果不相等则为新增用户
            Custmer custmer = new Custmer();
            custmer.setId(orderManager.getCustmerId());
            custmer.setCustmerName(orderManager.getCustmerName());
            custmer.setOrderMoney(orderManager.getTowingFee());
            custmer.setRecordMoney(orderManager.getRecordAmount());
            int i = custmerService.insertCustmer(custmer);
            if (i == 0) {
                return AjaxResult.error("新增客户失败");
            }
        }
        return toAjax(orderManagerService.updateOrderManager(orderManager));
    }

    /**
     * 修改保存订单管理
     */
    @RequiresPermissions("order:manager:edit")
    @Log(title = "订单管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OrderManager orderManager) {

        return toAjax(orderManagerService.updateOrderManager(orderManager));
    }

    /**
     * 删除订单管理
     */
    @RequiresPermissions("order:manager:remove")
    @Log(title = "订单管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(orderManagerService.deleteOrderManagerByIds(ids));
    }

    @ApiOperation("批量导出订单信息")
    @RequiresPermissions("order:manager:export")
    @Log(title = "订单管理", businessType = BusinessType.EXPORT)
    @GetMapping("/myexport")
    public void myexport(HttpServletResponse response) throws Exception {
        //        File file = ResourceUtils.getFile(analyzeTemplatePath);  //jar包下找不到文件
        OrderManager orderManager = new OrderManager();
        OutputStream out = null;
//        Workbook wb = new XSSFWorkbook();
        Workbook wb = null;
        try {
            List<OrderManager> residentList = orderManagerService.selectOrderManagerList(orderManager);
            //        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            InputStream is = DowlonFileUtils.getResourcesFileInputStream(tempatePath);
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd hhmmss");
            //标题行抽出字段
            String[] title = {"序号", "日期", "拖运起始地点、车型、车牌号、联系电话", "收款金额", "记账金额", "客户名称", "提成", "杂费", "司机", "拖车车牌号码", "备注", "公司支付金额", "司机回款金额", "收款专员回款", "加油公里数", "加油金额", "油卡加油", "备注"};
            //读取文件模板并创建wb对象
            wb = new XSSFWorkbook(is);
            Sheet stuSheet = wb.getSheetAt(0);//获取到第一个工作表

            //循环数据列表,并填入到excel中
            for (int i = 0; i < residentList.size(); i++) {
                //创建list.size()行数据
                Row row = stuSheet.createRow(i + 1);
                //把值一一写进单元格里
                //设置第一列为自动递增的序号
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(DateUtils.parseDateToStr("yyyy-mm-dd HH:mm:ss", residentList.get(i).getOrderDate())); //日期
                row.createCell(2).setCellValue(residentList.get(i).getStart() + "、" + residentList.get(i).getCarType() + "、" + residentList.get(i).getCarNo() + "、" + residentList.get(i).getPhoneNum()); //起始地点。。。
                row.createCell(3).setCellValue(residentList.get(i).getDriverReceiverMoney());   //收款金额
                row.createCell(4).setCellValue(residentList.get(i).getRecordMoney());   //记账金额
                row.createCell(5).setCellValue(residentList.get(i).getCustmerName());   //客户名称
                row.createCell(6).setCellValue(residentList.get(i).getCommission());   //提成
                row.createCell(7).setCellValue(residentList.get(i).getZaFee());   //杂费
                row.createCell(8).setCellValue(residentList.get(i).getDriverName());   //司机
                row.createCell(9).setCellValue(residentList.get(i).getCarNo());   //拖车车牌号码
                row.createCell(10).setCellValue(residentList.get(i).getRemark());   //备注
                row.createCell(11).setCellValue(residentList.get(i).getComponyReceiverMoney());   //公司支付金额
                row.createCell(12).setCellValue(residentList.get(i).getReturnMoney());   //司机回款金额
                //            row.createCell(13).setCellValue(residentList.get(i).getHjStr());   //收款专员回款
                row.createCell(14).setCellValue(residentList.get(i).getMileage());   //加油公里数
                row.createCell(15).setCellValue(residentList.get(i).getRefuelMoney());   //加油金额
                row.createCell(16).setCellValue(residentList.get(i).getRefuelcardMoney());   //油卡加油金额
                row.createCell(17).setCellValue(residentList.get(i).getAppRemark());   //审批备注
            }
            //设置单元格宽度自适应，在此基础上把宽度调至1.5倍
            for (int i = 0; i < title.length; i++) {
                stuSheet.autoSizeColumn(i, true);
                stuSheet.setColumnWidth(i, stuSheet.getColumnWidth(i) * 15 / 10);
            }
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=orderInfo.xlsx");
            response.flushBuffer();
            wb.write(response.getOutputStream());
            wb.close();
        } catch (Exception e) {
            log.error("导出Excel异常{}", e.getMessage());
            throw new BusinessException("导出Excel失败，请联系网站管理员！");
        } finally {
            if (wb != null) {
                try {
                    wb.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
