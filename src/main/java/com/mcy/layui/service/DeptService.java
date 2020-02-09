package com.mcy.layui.service;

import com.mcy.layui.custom.CommonService;
import com.mcy.layui.entity.Dept;
import com.mcy.layui.repository.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptService extends CommonService<Dept, Integer> {

    @Autowired
    private DeptRepository deptRepository;

    public List<Dept> findByParentIsNull(Sort sort) {
        return deptRepository.findByParentIsNull(sort);
    }
}
