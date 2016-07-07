package com.adu.cookbook.dao;

import com.adu.cookbook.model.CookHistory;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author yunjie.du
 * @date 2016/7/6 17:41
 */
public interface CookHistoryMapper {
    List<CookHistory> queryCookHistoryByUserId(@Param("userId") int userId,
                                               @Param("startDate") Date startDate,
                                               @Param("endDate") Date endDate,
                                               @Param("offset") int offset,
                                               @Param("limit") int limit);

    int insert(@Param("cookHistory") CookHistory cookHistory);

    int deleteById(@Param("userId")int userId, @Param("id") long id);
}
