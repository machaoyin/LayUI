package com.mcy.layui.web.form;

import com.mcy.layui.custom.BaseForm;

public class TbClassForm extends BaseForm<Integer> {
    private Integer id;
    private String name;
    private String teacher;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
