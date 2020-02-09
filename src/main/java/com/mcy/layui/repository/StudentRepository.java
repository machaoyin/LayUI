package com.mcy.layui.repository;

import com.mcy.layui.custom.CommonRepository;
import com.mcy.layui.entity.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CommonRepository<Student, Integer> {

}
