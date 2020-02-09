package com.mcy.layui.custom;

import org.springframework.data.domain.Page;

import java.util.HashMap;

/**
 * 流加载返回数据格式工具类
 */
public class FlowloadUtils {
    public static HashMap<String, Object> buildResult(Page page) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("msg", "");
        result.put("pages", page.getTotalPages());      //数据总页数
        result.put("data", page.getContent());      //加载的数据
        return result;
    }
}
