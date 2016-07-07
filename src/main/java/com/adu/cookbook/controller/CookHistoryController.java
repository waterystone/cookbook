package com.adu.cookbook.controller;

import com.adu.cookbook.model.ApiResult;
import com.adu.cookbook.model.CookHistory;
import com.adu.cookbook.model.UserInfo;
import com.adu.cookbook.service.CookBookService;
import com.adu.cookbook.service.CookHistoryService;
import com.adu.cookbook.util.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yunjie.du
 * @date 2016/7/7 15:03
 */
@Controller
@RequestMapping("history")
public class CookHistoryController {
    @Autowired
    private CookHistoryService cookHistoryService;

    @Autowired
    private CookBookService cookBookService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/add")
    @ResponseBody
    public ApiResult add(@RequestParam(value = "cookBookId") long cookBookId) {
        UserInfo userInfo = UserContext.getUserInfo();
        logger.info("add_start,account={},cookBookId", userInfo.getAccount(), cookBookId);


        CookHistory history = new CookHistory(userInfo.getId(), cookBookId);
        int insertCount = cookHistoryService.insert(history);
        if (insertCount > 0) {
            return ApiResult.SUCCESS;
        }


        return ApiResult.buildFailedDataApiResult("创建失败，请重试");
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public ApiResult delete(long cookHistoryId) {
        UserInfo userInfo = UserContext.getUserInfo();
        logger.info("delete_start,account={},cookHistoryId={}", userInfo.getAccount(), cookHistoryId);


        cookHistoryService.deleteById(userInfo.getId(), cookHistoryId);


        return ApiResult.buildSuccessDataApiResult(null);
    }

}
