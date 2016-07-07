package com.adu.cookbook.controller;

import com.adu.cookbook.model.ApiResult;
import com.adu.cookbook.model.UserInfo;
import com.adu.cookbook.service.UserInfoService;
import com.adu.cookbook.util.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yunjie.du
 * @date 2016/7/6 15:38
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(value = "/getCurrentUser", produces = {"application/json", "text/javascript;charset=UTF-8"})
    @ResponseBody
    public ApiResult<UserInfo> getCurrentUser() {
        logger.info("getCurrentUser_start");

        UserInfo userInfo = UserContext.getUserInfo();
        return ApiResult.buildSuccessDataApiResult(userInfo);
    }
}
