package com.adu.cookbook.model;

import com.adu.cookbook.util.Stringfy;

import java.util.Date;

/**
 * @author yunjie.du
 * @date 2016/6/29 15:26
 */
public class UserInfo extends Stringfy {
    private int id;//主键自增ID
    private String userName;//姓名
    private int sex;//性别.0:女;1:男.
    private String mobile;//手机号
    private String account;//登陆账号
    private String password;//密码
    private int state;//权限状态(按位管理，0表示无，1表示有，全0则无任何权限).1位:登陆.
    private Date createTime;//创建时间
    private Date updateTime;//更新时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
