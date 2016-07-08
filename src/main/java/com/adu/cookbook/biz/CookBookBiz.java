package com.adu.cookbook.biz;

import com.adu.cookbook.model.CookBook;
import com.adu.cookbook.model.CookHistory;
import com.adu.cookbook.service.CookBookService;
import com.adu.cookbook.service.CookHistoryService;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yunjie.du
 * @date 2016/7/7 10:10
 */
@Component
public class CookBookBiz {
    @Autowired
    private CookBookService cookBookService;

    @Autowired
    private CookHistoryService cookHistoryService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    public List<CookBook> getSlides(int userId, int offset, int limit) {
        List<CookBook> cookBookList = cookBookService.getCookBookByUserId(userId, null, null, null, null, true, offset, limit);
        clearContent(cookBookList);

        return cookBookList;
    }

    /**
     * 最近创建的食谱
     *
     * @param offset
     * @param limit
     * @return
     */

    public List<CookBook> getRecentBooks(int userId, int offset, int limit) {
        List<CookBook> cookBookList = cookBookService.getCookBookByUserId(userId, null, null, null, null, false, offset, limit);
        clearContent(cookBookList);

        return cookBookList;
    }

    /**
     * 最新烹饪的食谱
     *
     * @param offset
     * @param limit
     * @return
     */

    public List<CookBook> getRecentCookings(int userId, int offset, int limit) {
        List<CookHistory> cookHistoryList = cookHistoryService.getCookHistoryByUserId(userId, null, null, offset, limit);
        List<Long> cookBookIdList = Lists.transform(cookHistoryList, new Function<CookHistory, Long>() {
            @Override
            public Long apply(CookHistory input) {
                return input.getCookBookId();
            }
        });

        List<CookBook> cookBookByIds = cookBookService.getCookBookByIds(cookBookIdList);
        List<CookBook> cookBookList = cookBookByIds;
        clearContent(cookBookList);

        return cookBookList;
    }

    /**
     * 烹饪次数的排行榜
     *
     * @param offset
     * @param limit
     * @return
     */

    public List<CookBook> getCookingCountRank(int userId, int offset, int limit) {
        List<CookBook> cookBookList = cookBookService.getCookBookOrderByCnt(userId, offset, limit);
        clearContent(cookBookList);

        return cookBookList;
    }


    /**
     * 猜你喜欢
     *
     * @param offset
     * @param limit
     * @return
     */

    public List<CookBook> getGuessLike(int userId, int offset, int limit) {
        List<CookBook> cookBookList = cookBookService.getGuessLike(userId, offset, limit);
        clearContent(cookBookList);

        return cookBookList;
    }


    /**
     * @param cookBookList
     */
    private void clearContent(List<CookBook> cookBookList) {
        if (CollectionUtils.isEmpty(cookBookList)) {
            return;
        }
        for (CookBook cookBook : cookBookList) {
            clearContent(cookBook);
        }
    }

    /**
     * 清空食谱的具体内容，在一些列表页展示时并不需要，以减少数据传输
     *
     * @param cookBook
     */
    private void clearContent(CookBook cookBook) {
        if (cookBook == null) {
            return;
        }
        cookBook.setHtmlContent(null);
    }

}
