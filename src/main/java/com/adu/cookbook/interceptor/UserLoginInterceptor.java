package com.adu.cookbook.interceptor;

import com.adu.cookbook.constants.CookieKeyConstant;
import com.adu.cookbook.model.UserInfo;
import com.adu.cookbook.service.UserInfoService;
import com.adu.cookbook.util.RequestUtil;
import com.adu.cookbook.util.ResponseUtil;
import com.adu.cookbook.util.UserContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * @author yunjie.du
 * @date 2016/7/6 14:52
 */
public class UserLoginInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private UserInfoService userInfoService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String srcUrl = getSrcUrl(request);

        UserInfo userInfo = getUserInfoFromCookie(request);
        if (userInfo == null) {
            logger.error("[ERROR-NO-login]ip={},srcUrl={}", RequestUtil.getUserIp(request), srcUrl);
            ResponseUtil.getHttpServletResponse(response).sendRedirect("/login/login?srcUrl=" + URLEncoder.encode(srcUrl, "UTF-8"));
            return false;
        }

        UserContext.setUserInfo(userInfo);
        return true;

    }

    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        UserContext.clear();
    }

    private String getSrcUrl(HttpServletRequest httpServletRequest) {
        String srcUrl = httpServletRequest.getRequestURI();
        String queryString = httpServletRequest.getQueryString();
        if (StringUtils.isNotEmpty(queryString)) {
            srcUrl += "?" + queryString;
        }
        return srcUrl;
    }

    private UserInfo getUserInfoFromCookie(ServletRequest request) {
        UserInfo res = null;

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        Cookie userCookie = RequestUtil.getCookie(httpServletRequest, CookieKeyConstant.USER_ACCOUNT);

        if (userCookie != null) {
            String userAccount = userCookie.getValue();
            res = userInfoService.getUserInfoByAccount(userAccount);
        }

        return res;
    }
}
