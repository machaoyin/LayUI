package com.mcy.layui.web.controller;

import com.mcy.layui.custom.AjaxResult;
import com.mcy.layui.custom.CommonController;
import com.mcy.layui.entity.Student;
import com.mcy.layui.service.StudentService;
import com.mcy.layui.service.TbClassService;
import com.mcy.layui.web.form.StudentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;

@Controller
@RequestMapping(value = "/student")
public class StudentController extends CommonController<Student, Integer, StudentForm> {

    @Autowired
    private TbClassService tbClassService;
    @Autowired
    private StudentService studentService;

    @Override
    public void edit(StudentForm form, ModelMap map) throws InstantiationException, IllegalAccessException {
        map.put("tbClass", tbClassService.findAll());
        Student model = new Student();
        Integer id = form.getId();
        if (id != null) {
            model = studentService.findById(id);
        }
        map.put("model", model);
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Integer id, Boolean isUsed) {
        try {
            Student stu = studentService.findById(id);
            stu.setIsUsed(isUsed);
            studentService.save(stu);
            return new AjaxResult("数据修改成功");
        } catch (Exception e) {
            return new AjaxResult(false, "数据修改失败");
        }
    }

    @RequestMapping(value = "/updateGrade")
    @ResponseBody
    public Object updateGrade(Integer id, double grade) {
        try {
            Student stu = studentService.findById(id);
            stu.setGrade(grade);
            studentService.save(stu);
            return new AjaxResult("数据修改成功");
        } catch (Exception e) {
            return new AjaxResult(false, "数据修改失败");
        }
    }

    @RequestMapping(value = "delete1")
    @ResponseBody
    public Object delete(String ids) {
        try {
            String[] split = ids.split(",");
            for (int i = 0; i < split.length; i++) {
                studentService.deleteById(Integer.parseInt(split[i]));
            }
            return new AjaxResult("数据删除成功");
        } catch (Exception e) {
            return new AjaxResult(false, "数据删除失败");
        }
    }

    @Override
    public Specification<Student> buildSpec(StudentForm form) {
        Specification<Student> specification = new Specification<Student>() {

            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                HashSet<Predicate> rules = new HashSet<>();
                if (StringUtils.hasText(form.getName())) {
                    Predicate name = cb.like(root.get("name"), "%" + form.getName() + "%");
                    rules.add(name);
                }
                if (StringUtils.hasText(form.getTeahcerName())) {
                    Predicate code = cb.like(root.get("tbClass").get("name"), "%" + form.getTeahcerName() + "%");
                    rules.add(code);
                }
                return cb.and(rules.toArray(new Predicate[rules.size()]));
            }

        };
        return specification;
    }
}
