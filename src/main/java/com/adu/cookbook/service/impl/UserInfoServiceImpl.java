package com.adu.cookbook.service.impl;

import com.adu.cookbook.dao.UserInfoMapper;
import com.adu.cookbook.model.UserInfo;
import com.adu.cookbook.service.UserInfoService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author yunjie.du
 * @date 2016/6/29 15:38
 */
@Service
public class UserInfoServiceImpl implements InitializingBean, UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    private LoadingCache<String, UserInfo> cache;//缓存,key为account
    private static final int CACHE_SIZE_LIIMIT = 3000;//缓存的大小
    private static final int CACHE_TIME_LIMIT = 2 * 60 * 60;// 缓存的时间阈值(秒)。

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void afterPropertiesSet() throws Exception {
        cache = CacheBuilder.newBuilder().maximumSize(CACHE_SIZE_LIIMIT).expireAfterWrite(CACHE_TIME_LIMIT, TimeUnit.SECONDS).build(new CacheLoader<String, UserInfo>() {
            @Override
            public UserInfo load(String key) throws Exception {
                logger.info("[cache-miss-UserInfo]account={}", key);
                return getUserInfoByAccount(key);
            }
        });
    }

    @Override
    public UserInfo getUserInfoById(int id) {
        return userInfoMapper.queryUserInfoById(id);
    }

    @Override
    public UserInfo getUserInfoByAccount(String account) {
        UserInfo res = null;

        if (StringUtils.isEmpty(account)) {
            logger.error("[ERROR-getUserInfoByAccount-NULL-account]");
            return res;
        }

        try {
            res = cache.get(account);
        } catch (Exception e) {
            res = userInfoMapper.queryUserInfoByAccount(account);
        }
        return res;
    }

    @Override
    public int addUser(UserInfo userInfo) {
        if (Objects.isNull(userInfo)) {
            logger.error("[ERROR-addUser-NULL-userInfo]");
            return 0;
        }
        return userInfoMapper.addUser(userInfo);
    }


}
