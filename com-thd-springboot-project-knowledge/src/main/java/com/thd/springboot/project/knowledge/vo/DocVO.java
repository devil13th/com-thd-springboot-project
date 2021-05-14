package com.thd.springboot.project.knowledge.vo;

import com.thd.springboot.project.knowledge.constant.DocTypeEnum;

import java.util.List;

/**
 * com.thd.springboot.project.knowledge.vo.DocBean
 *
 * @author: wanglei62
 * @DATE: 2021/5/12 11:46
 **/
public class DocVO {
    // 文档id
    private String id;
    // 分类
    private String classify;
    // 标题
    private String title;
    // 描述
    private String desc;
    // 内容
    private String content;
    // 文档类型
    private String docType;
    // 文件路径
    private String filePath;

    // 结果高亮
    private List<String> highLight;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public List<String> getHighLight() {
        return highLight;
    }

    public void setHighLight(List<String> highLight) {
        this.highLight = highLight;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
