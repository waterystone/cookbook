package com.adu.cookbook.controller;

import com.adu.cookbook.biz.CookBookBiz;
import com.adu.cookbook.model.ApiResult;
import com.adu.cookbook.model.CookBook;
import com.adu.cookbook.model.UserInfo;
import com.adu.cookbook.service.CookBookService;
import com.adu.cookbook.service.CookHistoryService;
import com.adu.cookbook.util.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    @Autowired
    private CookBookBiz cookBookBiz;

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

        List<CookBook> cookBookList = cookBookBiz.getSlides(userInfo.getId(), offset, limit);

        logger.debug("getSlides_end,cookBookList={}", cookBookList);
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

        List<CookBook> cookBookList = cookBookBiz.getRecentBooks(userInfo.getId(), offset, limit);

        logger.debug("getRecentBooks_end,cookBookList={}", cookBookList);
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

        List<CookBook> cookBookList = cookBookBiz.getRecentCookings(userInfo.getId(), offset, limit);

        logger.debug("getRecentCookings_end,cookBookList={}", cookBookList);
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

        List<CookBook> cookBookList = cookBookBiz.getCookingCountRank(userInfo.getId(), offset, limit);

        logger.debug("getCookingsCountRank_end,cookBookList={}", cookBookList);
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

        List<CookBook> cookBookList = cookBookBiz.getGuessLike(userInfo.getId(), offset, limit);

        logger.debug("getGuessLike_end,cookBookList={}", cookBookList);
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

        logger.debug("getDegrees_end,degrees={}", degrees);
        return ApiResult.buildSuccessDataApiResult(degrees);
    }

    @RequestMapping(value = "/getCategories", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<Set<String>> getCategories(@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                                @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        UserInfo userInfo = UserContext.getUserInfo();
        logger.info("getCategories_start,account={}", userInfo.getAccount());


        Set<String> categories = cookBookService.getCategoriesByUserId(userInfo.getId());

        logger.debug("getCategories_end,categories={}", categories);
        return ApiResult.buildSuccessDataApiResult(categories);
    }

    @RequestMapping(value = "/getTags", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<Set<String>> getTags(@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                          @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        UserInfo userInfo = UserContext.getUserInfo();
        logger.info("getTags_start,account={}", userInfo.getAccount());


        Set<String> tags = cookBookService.getTagsByUserId(userInfo.getId());

        logger.debug("getTags_end,tags={}", tags);
        return ApiResult.buildSuccessDataApiResult(tags);
    }

    @RequestMapping(value = "/getMaterials", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<Set<String>> getMaterials(@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                               @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        UserInfo userInfo = UserContext.getUserInfo();
        logger.info("getMaterials_start,account={}", userInfo.getAccount());


        Set<String> materials = cookBookService.getMaterialsByUserId(userInfo.getId());

        logger.debug("getMaterials_end,materials={}", materials);
        return ApiResult.buildSuccessDataApiResult(materials);
    }

    @RequestMapping(value = "/bookList", method = RequestMethod.GET)
    public ModelAndView getBookList(Model model) {
        UserInfo userInfo = UserContext.getUserInfo();
        logger.info("getBookList_start,account={}", userInfo.getAccount());

        List<CookBook> cookBookList = cookBookService.getCookBookByUserId(userInfo.getId(), null, null, null, null, false, 0, Integer.MAX_VALUE);
        logger.debug("getBookList_end,cookBookList={}", cookBookList);

        model.addAttribute("cookBookList", cookBookList);

        return new ModelAndView("book/bookList");
    }

    @RequestMapping(value = "/book/{cookBookId:\\d+}", method = RequestMethod.GET)
    public ModelAndView getBook(Model model, @PathVariable(value = "cookBookId") long cookBookId) {
        UserInfo userInfo = UserContext.getUserInfo();
        logger.info("getBook_start,account={},cookBookId={}", userInfo.getAccount(), cookBookId);

        CookBook cookBook = cookBookService.getCookBookById(cookBookId);
        logger.debug("getBook_end,cookBook={}", cookBook);

        if (cookBook != null && cookBook.getUserId() != userInfo.getId()) {
            model.addAttribute("errorMsg", "无权查看该文");
        } else {
            model.addAttribute("cookBook", cookBook);
        }

        return new ModelAndView("book/book");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult create(CookBook cookBook) {
        UserInfo userInfo = UserContext.getUserInfo();
        cookBook.setUserId(userInfo.getId());//填充用户ID
        logger.info("create_start,account={},cookBook={}", userInfo.getAccount(), cookBook);

        int insertCount = cookBookService.insert(cookBook);

        logger.debug("create_end,insertCount={}", insertCount);
        if (insertCount > 0) {
            return ApiResult.buildSuccessDataApiResult(cookBook.getId());
        }


        return ApiResult.buildFailedDataApiResult("创建失败，请重试");
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult<List<String>> update(CookBook cookBook) {
        UserInfo userInfo = UserContext.getUserInfo();
        logger.info("update_start,account={},cookBook={}", userInfo.getAccount(), cookBook);


        int updateCount = cookBookService.updateById(userInfo.getId(), cookBook);
        logger.debug("update_end,updateCount={}", updateCount);

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
        logger.debug("delete_end,deleteCount={}", deleteCount);

        if (deleteCount > 0) {
            return ApiResult.SUCCESS;
        }


        return ApiResult.buildFailedDataApiResult("删除失败，请重试");
    }


}
