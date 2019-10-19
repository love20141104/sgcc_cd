package com.sgcc.dtomodel.wechat.template;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 模板消息基类
 */
@Data
public class TemplateMessage {

    private String template_id;                 //模板ID
    private String touser;                      //目标客户
    private String url;                         //用户点击模板信息的跳转页面
    private Map<String,TemplateData> data = new LinkedHashMap<>();      //模板里的数据

    public TemplateMessage(String template_id, String touser, String url, Map<String, TemplateData> data) {
        this.template_id = template_id;
        this.touser = touser;
        this.url = url;
        this.data = data;
    }
}
