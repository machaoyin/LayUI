package com.mcy.layui.web.controller;

import com.mcy.layui.custom.AjaxResult;
import com.mcy.layui.service.FileService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Controller
public class IndexController {
    @Autowired
    private FileService fileService;

    @RequestMapping(value = {"/index", "/"})
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/system")
    public String system(){
        return "system";
    }

    @RequestMapping(value = "/imgSave")
    @ResponseBody
    public Boolean imgSave(MultipartFile file) {
        String uuid = UUID.randomUUID().toString()+".jpg";
        Boolean bool = fileService.saveFile(file, uuid);
        return bool;
    }
}
