package com.adu.cookbook.service.impl;

import com.adu.cookbook.dao.UserInfoMapper;
import com.adu.cookbook.model.UserInfo;
import com.adu.cookbook.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author yunjie.du
 * @date 2016/6/29 15:38
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public UserInfo getUserInfoById(int id) {
        return userInfoMapper.queryUserInfoById(id);
    }

    @Override
    public UserInfo getUserInfoByAccount(String account) {
        if (StringUtils.isEmpty(account)) {
            logger.error("[ERROR-getUserInfoByAccount-NULL-account]");
            return null;
        }

        return userInfoMapper.queryUserInfoByAccount(account);
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
