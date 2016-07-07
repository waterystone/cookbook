package com.adu.cookbook.controller;

import com.adu.cookbook.constants.CookDegreeEnum;
import com.adu.cookbook.constants.CookieKeyConstant;
import com.adu.cookbook.model.ApiResult;
import com.adu.cookbook.model.UserInfo;
import com.adu.cookbook.service.UserInfoService;
import com.adu.cookbook.util.RequestUtil;
import com.adu.cookbook.util.ResponseUtil;
import com.adu.cookbook.util.UserContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yunjie.du
 * @date 2016/6/29 15:42
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserInfoService userInfoService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView login(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "srcUrl", required = false) String srcUrl) throws IOException {
        logger.info("login_start,uri={},srcUrl={}", request.getRequestURI(), srcUrl);

        if (RequestUtil.getCookie(request, CookieKeyConstant.USER_ACCOUNT) != null) {//如果已登陆
            response.sendRedirect(srcUrl == null ? "/" : srcUrl);
            return null;
        }

        model.addAttribute("srcUrl", srcUrl);
        return new ModelAndView("/login/login");

    }

    @RequestMapping(value = "/commit", method = RequestMethod.POST, produces = {"application/json", "text/javascript;charset=UTF-8"})
    @ResponseBody
    public ApiResult commit(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "account") String account,
                            @RequestParam(value = "password") String password,
                            @RequestParam(value = "isRemember", required = false) Boolean isRemember) throws IOException {
        logger.info("commit_start,account={},password={},isRemember={}", account, password, isRemember);

        UserInfo userInfo = userInfoService.getUserInfoByAccount(account);
        if (userInfo == null) {
            return ApiResult.buildFailedDataApiResult("账户不存在，请重试");
        }
        if (!StringUtils.equals(password, userInfo.getPassword())) {
            return ApiResult.buildFailedDataApiResult("密码错误，请重试");
        }

        int cookieSeconds = CookieKeyConstant.USER_INFO_DEFAULT_SECONDS;
        if (Boolean.TRUE.equals(isRemember)) {
            cookieSeconds = CookieKeyConstant.USER_INFO_LONG_SECONDS;
        }

        ResponseUtil.addCookie(response, CookieKeyConstant.USER_ACCOUNT, userInfo.getAccount(), cookieSeconds);// 用户信息加入Cookie，标记登陆状态
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
    @RequestMapping(value = "/logout", produces = {"application/json", "text/javascript;charset=UTF-8"})
    @ResponseBody
    public ApiResult logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("op=logout_start,userInfo={}", UserContext.getUserInfo());
        ResponseUtil.deleteCookie(response, CookieKeyConstant.USER_ACCOUNT);

        request.getSession().invalidate();
        return ApiResult.SUCCESS;

    }

}
