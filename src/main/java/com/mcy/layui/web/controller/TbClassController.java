package com.mcy.layui.web.controller;

import com.mcy.layui.custom.AjaxResult;
import com.mcy.layui.custom.CommonController;
import com.mcy.layui.entity.TbClass;
import com.mcy.layui.service.TbClassService;
import com.mcy.layui.web.form.TbClassForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;

@Controller
@RequestMapping(value = "/tbClass")
public class TbClassController extends CommonController<TbClass, Integer, TbClassForm> {
    @Autowired
    private TbClassService tbClassService;

    @RequestMapping(value = "delete1")
    @ResponseBody
    public Object delete(String ids) {
        try {
            String[] split = ids.split(",");
            for (int i = 0; i < split.length; i++) {
                tbClassService.deleteById(Integer.parseInt(split[i]));
            }
            return new AjaxResult("数据删除成功");
        } catch (Exception e) {
            return new AjaxResult(false, "数据删除失败");
        }
    }

    @Override
    public Specification<TbClass> buildSpec(TbClassForm form) {
        Specification<TbClass> specification = new Specification<TbClass>() {

            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<TbClass> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                HashSet<Predicate> rules = new HashSet<>();
                if (StringUtils.hasText(form.getName())) {
                    Predicate name = cb.like(root.get("name"), "%" + form.getName() + "%");
                    rules.add(name);
                }
                if (StringUtils.hasText(form.getTeacher())) {
                    Predicate code = cb.like(root.get("teacher"), "%" + form.getTeacher() + "%");
                    rules.add(code);
                }
                return cb.and(rules.toArray(new Predicate[rules.size()]));
            }

        };
        return specification;
    }
}
