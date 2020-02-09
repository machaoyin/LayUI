package com.mcy.layui.repository;

import com.mcy.layui.custom.CommonRepository;
import com.mcy.layui.entity.Dept;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptRepository extends CommonRepository<Dept, Integer> {

    List<Dept> findByParentIsNull(Sort sort);
}
