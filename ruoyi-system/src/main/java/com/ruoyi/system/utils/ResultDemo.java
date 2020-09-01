package com.ruoyi.system.utils;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chentao
 * @date 2020/8/26
 **/
public class ResultDemo {
    public static TableDataInfo tableDataInfo = new TableDataInfo();
    public static List<AjaxResult> list = new ArrayList<>();
    public static TableDataInfo result(){
        tableDataInfo.setCode(500);
        tableDataInfo.setMsg("用户不存在！");
        tableDataInfo.setRows(list);
        tableDataInfo.setTotal(0);
        return  tableDataInfo;
    }
    public static TableDataInfo resultSuccess(String msg){
        tableDataInfo.setCode(0);
        tableDataInfo.setMsg(msg);
        tableDataInfo.setRows(list);
        tableDataInfo.setTotal(0);
        return  tableDataInfo;
    }
    public static TableDataInfo resultError(String msg){
        tableDataInfo.setCode(500);
        tableDataInfo.setMsg(msg);
        tableDataInfo.setRows(list);
        tableDataInfo.setTotal(0);
        return  tableDataInfo;
    }
    public static TableDataInfo resultException(String msg){
        tableDataInfo.setCode(500);
        tableDataInfo.setMsg(msg);
        tableDataInfo.setRows(list);
        tableDataInfo.setTotal(0);
        return  tableDataInfo;
    }
}
