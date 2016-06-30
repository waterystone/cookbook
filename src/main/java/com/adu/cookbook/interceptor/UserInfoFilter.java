package com.adu.cookbook.interceptor;


import com.adu.cookbook.constants.CookieKeyConstant;
import com.adu.cookbook.model.UserInfo;
import com.adu.cookbook.util.JsonUtil;
import com.adu.cookbook.util.RequestUtil;
import com.adu.cookbook.util.ResponseUtil;
import com.adu.cookbook.util.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 用户信息拦截器
 */
public class UserInfoFilter implements Filter {
    private FilterConfig filterConfig;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("op=servlet_init_start,filterConfig={}", filterConfig);
        this.filterConfig = filterConfig;
        printFilterConfig();

    }

    private void printFilterConfig() {
        Enumeration enumeration = this.filterConfig.getInitParameterNames();
        int n = 1;
        while (enumeration.hasMoreElements()) {
            String key = (String) enumeration.nextElement();
            String value = this.filterConfig.getInitParameter(key);
            logger.info("[filterConfig-{}]{}={}", n++, key, value);
        }

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            UserInfo userInfo = getUserInfoFromCookie(request);
            if (userInfo == null) {
                String srcUrl = RequestUtil.getHttpServletRequest(request).getRequestURI();
                ResponseUtil.getHttpServletResponse(response).sendRedirect("/login?srcUrl=" + srcUrl);
                return;
            }

            UserContext.setUserInfo(userInfo);
            chain.doFilter(request, response);
        } finally {
            UserContext.clear();
        }

    }

    @Override
    public void destroy() {
        logger.info("op=servlet_destroy_start");

    }

    private UserInfo getUserInfoFromCookie(ServletRequest request) {
        UserInfo res = null;

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        Cookie userCookie = RequestUtil.getCookie(httpServletRequest, CookieKeyConstant.USER_INFO);

        if (userCookie != null) {
            res = JsonUtil.toObject(userCookie.getValue(), UserInfo.class);
        }

        return res;
    }


}
