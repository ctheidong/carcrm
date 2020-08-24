package com.ruoyi.web.controller.statistics;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.service.IOrderManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.expression.Arrays;

import java.util.List;

/**
 * @author chentao
 * @date 2020/8/22
 **/

@Controller
@RequestMapping("/statistics")
public class StatisticsController extends BaseController {
    private String prefix = "statistics";
    @Autowired
    private IOrderManagerService orderManagerService;
    @GetMapping()
    public String statistics(ModelMap mmap){

        List<Double> refuelByMonth = orderManagerService.selectRefuelByMonth();
        List<Double> returnMoneyByMonth = orderManagerService.selectReturnMoneyByMonth();
        List<Double> commissionMoneyByMonth = orderManagerService.selectCommissionMoneyByMonth();
        List<Double> incomeMoneyByMonth = orderManagerService.selectIncomeMoneyByMonth();
        mmap.put("refuelByMonth",refuelByMonth==null?new Arrays():refuelByMonth.toArray());
        mmap.put("returnMoneyByMonth",returnMoneyByMonth==null?new Arrays():returnMoneyByMonth.toArray());
        mmap.put("commissionMoneyByMonth",commissionMoneyByMonth==null?new Arrays():commissionMoneyByMonth.toArray());
        mmap.put("incomeMoneyByMonth",incomeMoneyByMonth==null?new Arrays():incomeMoneyByMonth.toArray());

        return prefix + "/statistics";
    }
    public AjaxResult getOptionData(){

        return null;
    }
}
