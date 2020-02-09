package com.mcy.layui.web.form;

import com.mcy.layui.custom.BaseForm;

public class DeptForm extends BaseForm<Integer> {
    private String name;    //部门名称
    private String deptName;    //部门负责人
    private String phone;   //电话号
    private String number;  //编号
    private double idx;     //排序
    private Integer parentId;   //父节点id

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getIdx() {
        return idx;
    }

    public void setIdx(double idx) {
        this.idx = idx;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
