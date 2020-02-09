package com.mcy.layui.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TbMenu {
    private Integer id;
    private String name;        //菜单名称
    private String url;         //路径
    private String icon;        //图标
    private double idx;         //排序
    @JsonIgnore
    private TbMenu parent;
    @JsonIgnore
    private List<TbMenu> children = new ArrayList<>();

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    @ManyToOne
    @CreatedBy
    public TbMenu getParent() {
        return parent;
    }

    public void setParent(TbMenu parent) {
        this.parent = parent;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    @OrderBy(value = "idx")
    public List<TbMenu> getChildren() {
        return children;
    }

    public void setChildren(List<TbMenu> children) {
        this.children = children;
    }

    public TbMenu(Integer id, String name, String url, String icon, double idx, TbMenu parent, List<TbMenu> children) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.icon = icon;
        this.idx = idx;
        this.parent = parent;
        this.children = children;
    }

    public TbMenu(Integer id) {
        this.id = id;
    }

    public TbMenu() {
    }

    @Transient
    public Integer getParentId() {
        return parent == null ? 0 : parent.getId();
    }
}
