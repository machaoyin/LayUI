package com.mcy.layui.web.controller;

import com.mcy.layui.custom.AjaxResult;
import com.mcy.layui.custom.CommonController;
import com.mcy.layui.custom.CommonRepository;
import com.mcy.layui.custom.FlowloadUtils;
import com.mcy.layui.entity.LayEdit;
import com.mcy.layui.service.FileService;
import com.mcy.layui.service.LayEditService;
import com.mcy.layui.web.form.LayEditForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/layEdit")
public class LayEditController extends CommonController<LayEdit, Integer, LayEditForm> {
    @Autowired
    private LayEditService layEditService;
    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/edit2")
    public void edit2() {

    }

    @Override
    public Object save(LayEditForm form) throws InstantiationException, IllegalAccessException {
        try {
            LayEdit model = new LayEdit();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Integer id = form.getId();
            if (id != null) {
                model = layEditService.findById(id);
            } else {
                form.setCreatdate(sdf.format(new Date()));
            }
            BeanUtils.copyProperties(form, model, "id");
            layEditService.save(model);
            return new AjaxResult("数据保存成功");
        } catch (Exception e) {
            return new AjaxResult(false, "数据保存失败");
        }
    }

    @RequestMapping(value = "/upload")
    @ResponseBody
    public Object upload(MultipartFile file) {
        String filename = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        boolean boole = fileService.saveFile(file, uuid);
        if (boole) {
            Map<String, Object> map = new HashMap<String, Object>();
            Map<String, Object> map2 = new HashMap<String, Object>();
            map.put("code", 0);    //0表示上传成功
            map.put("msg", "上传成功"); //提示消息
            map2.put("src", "http://localhost/layEdit/download?uuid=" + uuid);
            map2.put("title", filename);
            map.put("data", map2);
            return map;
        } else {
            return new AjaxResult(true, file.getOriginalFilename());
        }
    }

    @RequestMapping(value = "/download")
    @ResponseBody
    private void download(String uuid, HttpServletRequest request, HttpServletResponse response) {
        fileService.download(uuid, request, response);
    }

    @RequestMapping(value = "/visit")
    @ResponseBody
    public Object visit(Integer id) {
        return layEditService.findById(id).getText();
    }

    @RequestMapping(value = "delete1")
    @ResponseBody
    public Object delete(String ids) {
        try {
            String[] split = ids.split(",");
            for (int i = 0; i < split.length; i++) {
                layEditService.deleteById(Integer.parseInt(split[i]));
            }
            return new AjaxResult("数据删除成功");
        } catch (Exception e) {
            return new AjaxResult(false, "数据删除失败");
        }
    }

    @Override
    public Specification<LayEdit> buildSpec(LayEditForm form) {
        Specification<LayEdit> specification = new Specification<LayEdit>() {

            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<LayEdit> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                HashSet<Predicate> rules = new HashSet<>();
                if (StringUtils.hasText(form.getTitle())) {
                    Predicate name = cb.like(root.get("title"), "%" + form.getTitle() + "%");
                    rules.add(name);
                }
                if (StringUtils.hasText(form.getName())) {
                    Predicate difficultylevel = cb.like(root.get("name"), "%" + form.getName() + "%");
                    rules.add(difficultylevel);
                }
                return cb.and(rules.toArray(new Predicate[rules.size()]));
            }

        };
        return specification;
    }

    @RequestMapping(value = "/flow")
    public void flow() {

    }

    @RequestMapping(value = "/flowPage")
    @ResponseBody
    public Object flowPage(Integer page, LayEditForm form) {
        //分页，页数从0开始的，所以需要-1，每页10条数据
        Pageable pageable = PageRequest.of(page - 1, 10);
        Page<LayEdit> blog;
        blog = layEditService.findAll(pageable);
        return FlowloadUtils.buildResult(blog);
    }
}
