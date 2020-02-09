package com.mcy.layui.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.mcy.layui.custom.AjaxResult;
import com.mcy.layui.custom.CommonController;
import com.mcy.layui.custom.DeptTree;
import com.mcy.layui.entity.Dept;
import com.mcy.layui.service.DeptService;
import com.mcy.layui.web.form.DeptForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping(value = "/dept")
public class DeptController extends CommonController<Dept, Integer, DeptForm> {
    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "/show")
    public void show(DeptForm form, ModelMap map) throws InstantiationException, IllegalAccessException {
        Dept model = new Dept();
        Integer id = form.getId();
        Integer parentId = 0;
        if (id != null) {
            model = deptService.findById(id);
            parentId = model.getParent() == null ? 0 : model.getParent().getId();
        }
        map.put("parentId", parentId);
        map.put("model", model);
    }

    @RequestMapping(value = "/treeload")
    @ResponseBody
    public Object treeload() {
        Sort sort = Sort.by("idx");
        List<Dept> dpet = deptService.findByParentIsNull(sort);    //查找所有菜单
        List<HashMap<String, Object>> result = new ArrayList<>();    //定义一个map处理json键名问题
        return fun(dpet, result);
    }

    private Object fun(List<Dept> dpet, List<HashMap<String, Object>> result) {
        for (Dept d : dpet) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", d.getId());
            map.put("title", d.getName());
            map.put("spread", true);      //设置是否展开
            List<HashMap<String, Object>> result1 = new ArrayList<>();
            List<Dept> children = d.getChildren();    //下级菜单
            //这里可以根据自己需求判断节点默认选中
            /*if(m.getParent() != null || m.getChildren().size() == 0){
                map.put("checked", true);    //设置为选中状态
            }*/
            map.put("children", fun(children, result1));
            result.add(map);
        }
        return result;
    }

    @RequestMapping(value = "/treeSelect")
    @ResponseBody
    public Object treeSelect(Integer id) {
        Sort sort = Sort.by("idx");
        Specification<Dept> spec = buildSpec1();
        List<Dept> list = deptService.findAll(spec, sort);
        return buildTree(list, id);
    }

    private Object buildTree(List<Dept> list, Integer id) {
        List<HashMap<String, Object>> result = new ArrayList<>();
        for (Dept dept : list) {
            if (dept.getId() != id) {
                HashMap<String, Object> node = new HashMap<>();
                node.put("id", dept.getId());
                node.put("name", dept.getName());
                node.put("open", false);
                node.put("checked", false);
                if (dept.getChildren().size() != 0) {
                    node.put("children", buildTree(dept.getChildren(), id));
                }
                result.add(node);
            }
        }

        return result;
    }

    public Specification<Dept> buildSpec1() {
        Specification<Dept> specification = new Specification<Dept>() {

            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Dept> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                HashSet<Predicate> rules = new HashSet<>();
                Predicate parent = cb.isNull(root.get("parent"));
                rules.add(parent);
                return cb.and(rules.toArray(new Predicate[rules.size()]));
            }

        };
        return specification;
    }

    @Override
    public Object save(DeptForm form) {
        try {
            Dept model = new Dept();
            Integer id = form.getId();
            if (id != null) {
                model = deptService.findById(id);
            }
            //父级菜单id
            Integer parentId = form.getParentId();
            if (parentId == null) {
                model.setParent(null);
            } else {
                model.setParent(new Dept(parentId));
            }
            BeanUtils.copyProperties(form, model, "id", "parent");
            deptService.save(model);
            return new AjaxResult("数据保存成功！");
        } catch (Exception e) {
            return new AjaxResult(false, "数据保存失败");
        }
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(DeptForm form) {
        try {
            Dept model = deptService.findById(form.getId());
            model.setName(form.getName());
            deptService.save(model);
            return new AjaxResult("数据保存成功！");
        } catch (Exception e) {
            return new AjaxResult(false, "数据保存失败");
        }
    }

    @RequestMapping(value = "/checkedGain")
    @ResponseBody
    public void checkedGain(String data) {
        List<DeptTree> array2 = JSONArray.parseArray(data, DeptTree.class);
        treeData(array2);
    }

    //递归输出选中数据
    private void treeData(List<DeptTree> array2) {
        for (DeptTree tree : array2) {
            System.out.println(tree.getTitle() + "===" + tree.getId());
            if (tree.getChildren() != null) {
                treeData(tree.getChildren());
            }
        }
    }
}
