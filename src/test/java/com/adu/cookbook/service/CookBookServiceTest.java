package com.adu.cookbook.service;

import com.adu.cookbook.BaseTest;
import com.adu.cookbook.model.CookBook;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * @author yunjie.du
 * @date 2016/7/7 16:16
 */
public class CookBookServiceTest extends BaseTest {
    @Autowired
    private CookBookService cookBookService;

    @Test
    public void getCookBookById() {
        long cookBookId = 1;
        CookBook res = cookBookService.getCookBookById(cookBookId);
        logger.info("res={}", res);
    }

    @Test
    public void getCookBookByIds() {
        List<Long> ids = Lists.newArrayList(1L);
        List<CookBook> res = cookBookService.getCookBookByIds(ids);
        logger.info("res={}", res);
    }

    @Test
    public void getCookBookByUserId() {
        int userId = 1;
        Integer degree = 3;
        List<String> categories = Lists.newArrayList("大餐");
        List<String> tags = Lists.newArrayList("清新", "爱心");
        List<String> materials = Lists.newArrayList("大葱", "食盐");
        boolean isHasCoverPic = false;
        int offset = 0, limit = 10;
        List<CookBook> res = cookBookService.getCookBookByUserId(userId, degree, categories, tags, materials, isHasCoverPic, offset, limit);
        logger.info("res={}", res);
    }


    @Test
    public void getCookBookOrderByCnt() {
        int userId = 1;
        int offset = 0, limit = 10;
        List<CookBook> res = cookBookService.getCookBookOrderByCnt(userId, offset, limit);
        logger.info("res={}", res);
    }

    @Test
    public void getGuessLike() {
        int userId = 1;
        int offset = 0, limit = 10;
        List<CookBook> res = cookBookService.getGuessLike(userId, offset, limit);
        logger.info("res={}", res);
    }

    @Test
    public void getDegreesByUserId() {
        int userId = 1;
        Set<String> res = cookBookService.getDegreesByUserId(userId);
        logger.info("res={}", res);
    }

    @Test
    public void getCategoriesByUserId() {
        int userId = 1;
        Set<String> res = cookBookService.getCategoriesByUserId(userId);
        logger.info("res={}", res);
    }

    @Test
    public void getTagsByUserId() {
        int userId = 1;
        Set<String> res = cookBookService.getTagsByUserId(userId);
        logger.info("res={}", res);
    }

    @Test
    public void getMaterialsByUserId() {
        int userId = 1;
        Set<String> res = cookBookService.getMaterialsByUserId(userId);
        logger.info("res={}", res);
    }

    @Test
    public void updateById() {
        int userId = 1;
        CookBook cookBook = buildCookBook();
        int res = cookBookService.updateById(userId, cookBook);
        logger.info("res={}", res);
    }


    @Test
    public void addCnt() {
        int userId = 1;
        long cookBookId = 1;
        int cnt = 1;
        int res = cookBookService.addCnt(userId, cookBookId, cnt);
        logger.info("res={}", res);
    }

    @Test
    public void insert() {
        CookBook cookBook = buildCookBook();
        int res = cookBookService.insert(cookBook);
        logger.info("res={}", res);
    }

    @Test
    public void deleteById() {
        int userId = 1;
        long cookBookId = 1;
        int res = cookBookService.deleteById(userId, cookBookId);
        logger.info("res={}", res);
    }

    private CookBook buildCookBook() {
        CookBook res = new CookBook();
        res.setId(1);
        res.setTitle("title-test");
        res.setUserId(1);
        res.setDegree(3);
        res.setCategories("午餐;大餐");
        res.setTags("爱心;清新");
        res.setMaterials("食盐;大葱;猪肉;大米;姜");
        res.setCoverPic("");
        res.setDescription("description-test");
        res.setHtmlContent("<ul style=\"list-style-type: none;\"class=\" list-paddingleft-2\"><li><p><img src=\"http://i3.meishichina.com/attachment/recipe/201206/p320_201206301909431341241876.jpg\"alt=\"丝瓜面筋的做法步骤：1\"/></p><p>&nbsp;</p><p><img src=\"http://static.meishichina.com/res/num/1.png\"alt=\"1\"/></p><p>原料图</p></li><li><p><img src=\"http://i3.meishichina.com/attachment/recipe/201206/p320_201206301910301341789372.jpg\"alt=\"丝瓜面筋的做法步骤：2\"/></p><p>&nbsp;</p><p><img src=\"http://static.meishichina.com/res/num/2.png\"alt=\"2\"/></p><p>扁尖笋去掉老硬的部分，纵向撕成粗的条，然后切成寸把长短的条，放入碗中，加足量的冷水浸泡2小时，以去除多于的盐分。</p></li><li><p><img src=\"http://i3.meishichina.com/attachment/recipe/201206/p320_201206301910491341300821.jpg\"alt=\"丝瓜面筋的做法步骤：3\"/></p><p>&nbsp;</p><p><img src=\"http://static.meishichina.com/res/num/3.png\"alt=\"3\"/></p><p>丝瓜洗净去皮，切滚刀块备用。</p></li><li><p><img src=\"http://i3.meishichina.com/attachment/recipe/201206/p320_201206301911261341339943.jpg\"alt=\"丝瓜面筋的做法步骤：4\"/></p><p>&nbsp;</p><p><img src=\"http://static.meishichina.com/res/num/4.png\"alt=\"4\"/></p><p>油面筋用刀切两半备用.</p></li><li><p><img src=\"http://i3.meishichina.com/attachment/recipe/201206/p320_201206301911561341928145.jpg\"alt=\"丝瓜面筋的做法步骤：5\"/></p><p>&nbsp;</p><p><img src=\"http://static.meishichina.com/res/num/5.png\"alt=\"5\"/></p><p>锅中大火加油热至8成熟，放入丝瓜翻炒，至5分熟。</p></li><li><p><img src=\"http://i3.meishichina.com/attachment/recipe/201206/p320_201206301912451341900691.jpg\"alt=\"丝瓜面筋的做法步骤：6\"/></p><p>&nbsp;</p><p><img src=\"http://static.meishichina.com/res/num/6.png\"alt=\"6\"/></p><p>放入备好的面筋,加入高汤、白砂糖、盐翻炒。</p></li></ul><p><br/></p>");
        return res;
    }
}
