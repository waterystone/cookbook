package com.adu.cookbook.controller;

import com.adu.cookbook.constants.CookieKeyConstant;
import com.adu.cookbook.model.ApiResult;
import com.adu.cookbook.model.UserInfo;
import com.adu.cookbook.service.UserInfoService;
import com.adu.cookbook.util.JsonUtil;
import com.adu.cookbook.util.ResponseUtil;
import com.adu.cookbook.util.UserContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yunjie.du
 * @date 2016/6/29 15:42
 */
@Controller
@RequestMapping("/user")
public class userController {
    @Autowired
    private UserInfoService userInfoService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = {"application/json", "text/javascript;charset=UTF-8"})
    @ResponseBody
    public ApiResult login(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "account") String account,
                           @RequestParam(value = "password") String password,
                           @RequestParam(value = "srcUrl", required = false) String srcUrl) throws IOException {
        logger.info("login_start,account={},password={}", account, password);

        UserInfo userInfo = userInfoService.getUserInfoByAccount(account);
        if (userInfo == null) {
            return ApiResult.buildFailedDataApiResult("账户不存在，请重试");
        }
        if (!StringUtils.equals(password, userInfo.getPassword())) {
            return ApiResult.buildFailedDataApiResult("密码错误，请重试");
        }


        ResponseUtil.addCookie(response, CookieKeyConstant.USER_INFO, JsonUtil.toString(userInfo), CookieKeyConstant.USER_INFO_SECONDS);// 用户信息加入Cookie，标记登陆状态
        response.sendRedirect(StringUtils.isEmpty(srcUrl) ? "/" : srcUrl);
        return ApiResult.SUCCESS;
    }

    /**
     * 注销
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("op=logout_start,userInfo={}", UserContext.getUserInfo());
        ResponseUtil.deleteCookie(response, CookieKeyConstant.USER_INFO);

        request.getSession().invalidate();
        return "redirect:/";

    }

    @RequestMapping(value = "/getCurrentUser", produces = {"application/json", "text/javascript;charset=UTF-8"})
    @ResponseBody
    public ApiResult<UserInfo> getCurrentUser() {
        logger.info("getCurrentUser_start");

        UserInfo userInfo = UserContext.getUserInfo();
        return ApiResult.buildSuccessDataApiResult(userInfo);
    }
}
