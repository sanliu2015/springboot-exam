package com.plq.exam;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.Map;

/**
 * 一维数据
 */
public class StandardOneDimExcel {

    public static void main(String[] args) {
        ExcelReader reader = ExcelUtil.getReader("d:/exam.xlsx",0);
        List<Map<String, Object>> datas = reader.readAll();
        System.out.println(JSON.toJSONString(datas));
        List<List<Object>> title = reader.read(0, 1);       // 首行
        List<Object> cols = title.get(0);   // 横轴
        System.out.println("横轴，cols:" + JSON.toJSONString(cols.subList(1,cols.size())));
        List<List<Object>> data = reader.read(1, Integer.MAX_VALUE);    // 数据行
        System.out.println(JSON.toJSONString(data));
    }

}
