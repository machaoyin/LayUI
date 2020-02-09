package com.mcy.layui.repository;

import com.mcy.layui.custom.CommonRepository;
import com.mcy.layui.entity.LayEdit;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public interface LayEditRepository extends CommonRepository<LayEdit, Integer> {
}
