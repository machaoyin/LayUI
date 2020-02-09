package com.mcy.layui.repository;

import com.mcy.layui.custom.CommonRepository;
import com.mcy.layui.entity.FileImg;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public interface FileRepository extends CommonRepository<FileImg, Integer> {

    FileImg findByUuid(String uuid);
}
