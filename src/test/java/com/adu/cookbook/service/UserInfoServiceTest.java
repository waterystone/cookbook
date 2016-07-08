package com.adu.cookbook.service;

import com.adu.cookbook.BaseTest;
import com.adu.cookbook.model.UserInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yunjie.du
 * @date 2016/7/8 17:53
 */
public class UserInfoServiceTest extends BaseTest {
    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void addUser() {
        UserInfo userInfo = buildUserInfo();
        int res = userInfoService.addUser(userInfo);
        logger.info("res={}", res);
    }

    private UserInfo buildUserInfo() {
        UserInfo res = new UserInfo();
        res.setUserName("adu");
        res.setSex(1);
        res.setMobile("13411112222");
        res.setAccount(String.valueOf(System.currentTimeMillis()));
        res.setPassword("123455");
        return res;
    }
}
