package com.adu.cookbook.dao;

import com.adu.cookbook.model.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * @author yunjie.du
 * @date 2016/6/29 15:29
 */
public interface UserInfoMapper {
    public UserInfo queryUserInfoById(@Param("id") int id);

    public UserInfo queryUserInfoByAccount(@Param("account") String account);

    public int addUser(@Param("userInfo") UserInfo userInfo);
}
