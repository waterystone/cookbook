package com.adu.cookbook.service;

import com.adu.cookbook.model.UserInfo;

/**
 * @author yunjie.du
 * @date 2016/6/29 15:36
 */
public interface UserInfoService {
    public UserInfo getUserInfoById(int id);

    public UserInfo getUserInfoByAccount(String account);

    public int addUser(UserInfo userInfo);
}
