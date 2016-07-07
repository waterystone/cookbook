package com.adu.cookbook.service;

import com.adu.cookbook.BaseTest;
import com.adu.cookbook.model.CookHistory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author yunjie.du
 * @date 2016/7/7 17:10
 */
public class CookHistoryServiceTest extends BaseTest {
    @Autowired
    private CookHistoryService cookHistoryService;

    @Test
    public void getCookHistoryByUserId() {
        int userId = 1;
        Date startDate = null, endDate = null;
        int offset = 0, limit = 10;
        List<CookHistory> res = cookHistoryService.getCookHistoryByUserId(userId, startDate, endDate, offset, limit);
        logger.info("res={}", res);
    }

    @Test
    public void insert() {
        int userId = 1;
        long cookBookId = 1;
        CookHistory cookHistory = new CookHistory(userId, cookBookId);
        int res = cookHistoryService.insert(cookHistory);
        logger.info("res={}", res);
    }

    @Test
    public void deleteById() {
        int userId = 1;
        long cookHistoryId = 1;
        int res = cookHistoryService.deleteById(userId, cookHistoryId);
        logger.info("res={}", res);
    }
}
