package com.mcy.layui.custom;

import org.springframework.data.domain.Page;

import java.util.HashMap;

/**
 * layui分页查询返回类型
 *
 * @author asus
 */
public class DataGridUtils {
    public static HashMap<String, Object> buildResult(Page page) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("msg", "");
        result.put("number", page.getNumberOfElements());
        result.put("count", page.getTotalElements());
        result.put("data", page.getContent());
        return result;
    }
}
