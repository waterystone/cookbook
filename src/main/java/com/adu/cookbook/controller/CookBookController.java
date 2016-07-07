package com.adu.cookbook.controller;

import com.adu.cookbook.model.ApiResult;
import com.adu.cookbook.model.CookBook;
import com.adu.cookbook.model.CookHistory;
import com.adu.cookbook.model.UserInfo;
import com.adu.cookbook.service.CookBookService;
import com.adu.cookbook.service.CookHistoryService;
import com.adu.cookbook.util.UserContext;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Set;

/**
 * @author yunjie.du
 * @date 2016/7/7 10:10
 */
@Controller
@RequestMapping("cookbook")
public class CookBookController {
    @Autowired
    private CookBookService cookBookService;

    @Autowired
    private CookHistoryService cookHistoryService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 最近创建的有封面的食谱
     *
     * @param offset
     * @param limit
     * @return
     */
    @RequestMapping(value = "/getSlides", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<List<CookBook>> getSlides(@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                               @RequestParam(value = "limit", required = false, defaultValue = "5") int limit) {
        UserInfo userInfo = UserContext.getUserInfo();
        logger.info("getSlides_start,account={}", userInfo.getAccount());


        List<CookBook> cookBookList = cookBookService.getCookBookByUserId(userInfo.getId(), null, null, null, null, true, offset, limit);
        clearContent(cookBookList);

        return ApiResult.buildSuccessDataApiResult(cookBookList);
    }

    /**
     * 最近创建的食谱
     *
     * @param offset
     * @param limit
     * @return
     */
    @RequestMapping(value = "/getRecentBooks", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<List<CookBook>> getRecentBooks(@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                                    @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

        UserInfo userInfo = UserContext.getUserInfo();
        logger.info("getRecentBooks_start,account={}", userInfo.getAccount());


        List<CookBook> cookBookList = cookBookService.getCookBookByUserId(userInfo.getId(), null, null, null, null, false, offset, limit);
        clearContent(cookBookList);

        return ApiResult.buildSuccessDataApiResult(cookBookList);
    }

    /**
     * 最新烹饪的食谱
     *
     * @param offset
     * @param limit
     * @return
     */
    @RequestMapping(value = "/getRecentCookings", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<List<CookBook>> getRecentCookings(@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                                       @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        UserInfo userInfo = UserContext.getUserInfo();
        logger.info("getRecentCookings_start,account={}", userInfo.getAccount());


        List<CookHistory> cookHistoryList = cookHistoryService.getCookHistoryByUserId(userInfo.getId(), null, null, offset, limit);
        List<Long> cookBookIdList = Lists.transform(cookHistoryList, new Function<CookHistory, Long>() {
            @Override
            public Long apply(CookHistory input) {
                return input.getCookBookId();
            }
        });
        List<CookBook> cookBookByIds = cookBookService.getCookBookByIds(cookBookIdList);
        List<CookBook> cookBookList = cookBookByIds;
        clearContent(cookBookList);

        return ApiResult.buildSuccessDataApiResult(cookBookList);
    }

    /**
     * 烹饪次数的排行榜
     *
     * @param offset
     * @param limit
     * @return
     */
    @RequestMapping(value = "/getCookingsCountRank", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<List<CookBook>> getCookingsCountRank(@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                                          @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        UserInfo userInfo = UserContext.getUserInfo();
        logger.info("getCookingsCountRank_start,account={}", userInfo.getAccount());


        List<CookBook> cookBookList = cookBookService.getCookBookOrderByCnt(userInfo.getId(), offset, limit);
        clearContent(cookBookList);


        return ApiResult.buildSuccessDataApiResult(cookBookList);
    }


    /**
     * 猜你喜欢
     *
     * @param offset
     * @param limit
     * @return
     */
    @RequestMapping(value = "/getGuessLike", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<List<CookBook>> getGuessLike(@RequestParam(value = "offset", required = false, defaultValue = "0") int offset, @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        UserInfo userInfo = UserContext.getUserInfo();
        logger.info("getGuessLike_start,account={}", userInfo.getAccount());


        List<CookBook> cookBookList = cookBookService.getGuessLike(userInfo.getId(), offset, limit);
        clearContent(cookBookList);

        return ApiResult.buildSuccessDataApiResult(cookBookList);
    }


    /**
     * @return
     */
    @RequestMapping(value = "/getDegrees", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<Set<String>> getDegrees() {
        UserInfo userInfo = UserContext.getUserInfo();
        logger.info("getDegrees_start,account={}", userInfo.getAccount());


        Set<String> degrees = cookBookService.getDegreesByUserId(userInfo.getId());

        return ApiResult.buildSuccessDataApiResult(degrees);
    }

    @RequestMapping(value = "/getCategories", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<Set<String>> getCategories(@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                                @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        UserInfo userInfo = UserContext.getUserInfo();
        logger.info("getCategories_start,account={}", userInfo.getAccount());


        Set<String> categories = cookBookService.getCategoriesByUserId(userInfo.getId());

        return ApiResult.buildSuccessDataApiResult(categories);
    }

    @RequestMapping(value = "/getTags", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<Set<String>> getTags(@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                          @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        UserInfo userInfo = UserContext.getUserInfo();
        logger.info("getTags_start,account={}", userInfo.getAccount());


        Set<String> tags = cookBookService.getTagsByUserId(userInfo.getId());

        return ApiResult.buildSuccessDataApiResult(tags);
    }

    @RequestMapping(value = "/getMaterials", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<Set<String>> getMaterials(@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                               @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        UserInfo userInfo = UserContext.getUserInfo();
        logger.info("getMaterials_start,account={}", userInfo.getAccount());


        Set<String> materials = cookBookService.getMaterialsByUserId(userInfo.getId());

        return ApiResult.buildSuccessDataApiResult(materials);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult create(CookBook cookBook) {
        UserInfo userInfo = UserContext.getUserInfo();
        cookBook.setUserId(userInfo.getId());//填充用户ID
        logger.info("getRecentBooks_start,account={},cookBook={}", userInfo.getAccount(), cookBook);

        int insertCount = cookBookService.insert(cookBook);
        if (insertCount > 0) {
            return ApiResult.SUCCESS;
        }

        return ApiResult.buildFailedDataApiResult("创建失败，请重试");
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<List<String>> update(CookBook cookBook) {
        UserInfo userInfo = UserContext.getUserInfo();
        logger.info("update_start,account={},cookBook={}", userInfo.getAccount(), cookBook);


        int updateCount = cookBookService.updateById(userInfo.getId(), cookBook);
        if (updateCount > 0) {
            return ApiResult.SUCCESS;
        }

        return ApiResult.buildFailedDataApiResult("修改失败，请重试");
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<List<String>> delete(@RequestParam(value = "cookBookId") long cookBookId) {
        UserInfo userInfo = UserContext.getUserInfo();
        logger.info("delete_start,account={},cookBookId={}", userInfo.getAccount(), cookBookId);


        int deleteCount = cookBookService.deleteById(userInfo.getId(), cookBookId);
        if (deleteCount > 0) {
            return ApiResult.SUCCESS;
        }

        return ApiResult.buildFailedDataApiResult("删除失败，请重试");
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
