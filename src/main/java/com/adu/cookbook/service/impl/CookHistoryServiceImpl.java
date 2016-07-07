package com.adu.cookbook.service.impl;

import com.adu.cookbook.dao.CookHistoryMapper;
import com.adu.cookbook.model.CookHistory;
import com.adu.cookbook.service.CookBookService;
import com.adu.cookbook.service.CookHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author yunjie.du
 * @date 2016/7/7 10:03
 */
@Service
public class CookHistoryServiceImpl implements CookHistoryService {
    @Autowired
    private CookHistoryMapper cookHistoryMapper;

    @Autowired
    private CookBookService cookBookService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<CookHistory> getCookHistoryByUserId(int userId, Date startDate, Date endDate, int offset, int limit) {
        return cookHistoryMapper.queryCookHistoryByUserId(userId, startDate, endDate, offset, limit);
    }

    @Override
    public int insert(CookHistory cookHistory) {
        if (cookHistory == null) {
            return 0;
        }
        if (cookHistoryMapper.insert(cookHistory) > 0
                && cookBookService.addCnt(cookHistory.getUserId(), cookHistory.getCookBookId(), 1) > 0) {
            return 1;
        }
        return 0;

    }

    @Override
    public int deleteById(int userId, long id) {
        return cookHistoryMapper.deleteById(userId, id);
    }
}
