package com.mcy.layui.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Dept {
    private Integer id;
    private String name;    //部门名称
    private String deptName;    //部门负责人
    private String phone;   //电话号
    private String number;  //编号
    private double idx;     //排序
    @JsonIgnore
    private Dept parent;
    @JsonIgnore
    private List<Dept> children = new ArrayList<>();

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

    @ManyToOne
    @CreatedBy
    public Dept getParent() {
        return parent;
    }

    public void setParent(Dept parent) {
        this.parent = parent;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    @OrderBy(value = "idx")
    public List<Dept> getChildren() {
        return children;
    }

    public void setChildren(List<Dept> children) {
        this.children = children;
    }

    public Dept(Integer id, String name, String deptName, String phone, String number, double idx, Dept parent, List<Dept> children) {
        this.id = id;
        this.name = name;
        this.deptName = deptName;
        this.phone = phone;
        this.number = number;
        this.idx = idx;
        this.parent = parent;
        this.children = children;
    }

    public Dept(Integer id) {
        this.id = id;
    }

    public Dept() {
    }
}
