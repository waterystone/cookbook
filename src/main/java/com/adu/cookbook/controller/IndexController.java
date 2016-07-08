package com.adu.cookbook.controller;

import com.adu.cookbook.biz.CookBookBiz;
import com.adu.cookbook.model.ApiResult;
import com.adu.cookbook.model.CookBook;
import com.adu.cookbook.model.UserInfo;
import com.adu.cookbook.service.CookBookService;
import com.adu.cookbook.util.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

/**
 * @author yunjie.du
 * @date 2016/7/7 10:11
 */
@Controller
public class IndexController {
    @Autowired
    private CookBookBiz cookBookBiz;
    @Autowired
    private CookBookService cookBookService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView getSlides(Model model, HttpServletRequest request, HttpServletResponse response) {
        UserInfo userInfo = UserContext.getUserInfo();
        logger.info("index_start,account={}", userInfo.getAccount());
        List<CookBook> slideCookBookList = cookBookBiz.getSlides(userInfo.getId(), 0, 10);
        model.addAttribute("slideCookBookList", slideCookBookList);

        List<CookBook> recentCookBookList = cookBookBiz.getRecentBooks(userInfo.getId(), 0, 10);
        model.addAttribute("recentCookBookList", recentCookBookList);

        List<CookBook> recentCookingBookList = cookBookBiz.getRecentCookings(userInfo.getId(), 0, 10);
        model.addAttribute("recentCookingBookList", recentCookingBookList);

        List<CookBook> cookingCountRankBookList = cookBookBiz.getCookingCountRank(userInfo.getId(), 0, 10);
        model.addAttribute("cookingCountRankBookList", cookingCountRankBookList);

        List<CookBook> guessLikeCookBookList = cookBookBiz.getGuessLike(userInfo.getId(), 0, 10);
        model.addAttribute("guessLikeCookBookList", guessLikeCookBookList);

        Set<String> degrees = cookBookService.getDegreesByUserId(userInfo.getId());
        model.addAttribute("degrees", degrees);

        Set<String> categories = cookBookService.getCategoriesByUserId(userInfo.getId());
        model.addAttribute("categories", categories);

        Set<String> tags = cookBookService.getTagsByUserId(userInfo.getId());
        model.addAttribute("tags", tags);

        Set<String> materials = cookBookService.getMaterialsByUserId(userInfo.getId());
        model.addAttribute("materials", materials);


        return new ModelAndView("index");
    }
}
