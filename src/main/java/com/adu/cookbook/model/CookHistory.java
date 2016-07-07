package com.adu.cookbook.model;

import com.adu.cookbook.util.Stringfy;

import java.util.Date;

/**
 * @author yunjie.du
 * @date 2016/7/6 17:40
 */
public class CookHistory extends Stringfy {
    private long id;//主键自增ID
    private int userId;//创建者ID
    private long cookBookId;//食谱
    private int state;//状态。0:无效;1:有效.
    private Date createTime;//创建时间
    private Date updateTime;//更新时间

    public CookHistory() {
    }

    public CookHistory(int userId, long cookBookId) {
        this.userId = userId;
        this.cookBookId = cookBookId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getCookBookId() {
        return cookBookId;
    }

    public void setCookBookId(long cookBookId) {
        this.cookBookId = cookBookId;
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
}
