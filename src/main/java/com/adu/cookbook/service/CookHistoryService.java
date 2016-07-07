package com.adu.cookbook.service;

import com.adu.cookbook.model.CookHistory;

import java.util.Date;
import java.util.List;

/**
 * @author yunjie.du
 * @date 2016/7/7 10:00
 */
public interface CookHistoryService {
    List<CookHistory> getCookHistoryByUserId(int userId, Date startDate, Date endDate, int offset, int limit);

    int insert(CookHistory cookHistory);

    int deleteById(int userId, long id);
}
