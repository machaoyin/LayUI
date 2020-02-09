package com.mcy.layui.web.form;

import com.mcy.layui.custom.BaseForm;

public class LayEditForm extends BaseForm<Integer> {
    private String creatdate;       //创建时间
    private String title;       //标题
    private String name;        //发布人
    private String text;        //内容

    public String getCreatdate() {
        return creatdate;
    }

    public void setCreatdate(String creatdate) {
        this.creatdate = creatdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
