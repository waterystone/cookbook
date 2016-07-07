package com.adu.cookbook.model;

import com.adu.cookbook.util.Stringfy;

import java.util.Date;

/**
 * @author yunjie.du
 * @date 2016/7/6 15:43
 */
public class CookBook extends Stringfy {
    private long id;//主键自增ID
    private String title;//标题
    private int userId;//创建者ID
    private int degree;//难度系数
    private String categories;//类别,分号;分隔.
    private String tags;//标签,分号;分隔.
    private String materials;//原料,分号;分隔.
    private String coverPic;//封面图片.
    private String description;//摘要
    private String htmlContent;//食谱内容，html格式。
    private int state;//状态。0:无效;1:有效.
    private Date createTime;//创建时间
    private Date updateTime;//更新时间
    private int cnt;//烹饪次数

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public String getCoverPic() {
        return coverPic;
    }

    public void setCoverPic(String coverPic) {
        this.coverPic = coverPic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
}
