package com.mcy.layui.web.form;

import com.mcy.layui.custom.BaseForm;

public class TbMenuForm extends BaseForm<Integer> {
    private String name;        //菜单名称
    private String url;         //路径
    private String icon;        //图标
    private double idx;         //排序
    private Integer parentId;   //父节点id

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
