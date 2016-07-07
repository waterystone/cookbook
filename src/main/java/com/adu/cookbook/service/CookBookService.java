package com.adu.cookbook.service;

import com.adu.cookbook.model.CookBook;

import java.util.List;
import java.util.Set;

/**
 * @author yunjie.du
 * @date 2016/7/6 16:34
 */
public interface CookBookService {

    CookBook getCookBookById(long id);

    List<CookBook> getCookBookByIds(List<Long> ids);

    List<CookBook> getCookBookByUserId(int userId,
                                       Integer degree,
                                       List<String> categories,
                                       List<String> tags,
                                       List<String> materials,
                                       boolean isHasCoverPic,
                                       int offset,
                                       int limit);

    List<CookBook> getCookBookOrderByCnt(int userId,
                                         int offset,
                                         int limit);

    List<CookBook> getGuessLike(int userId,
                                int offset,
                                int limit);

    Set<String> getDegreesByUserId(int userId);

    Set<String> getCategoriesByUserId(int userId);

    Set<String> getTagsByUserId(int userId);

    Set<String> getMaterialsByUserId(int userId);

    int updateById(int userId, CookBook cookBook);

    int addCnt(int userId, long id, int cnt);

    int insert(CookBook cookBook);

    int deleteById(int userId, long id);
}
