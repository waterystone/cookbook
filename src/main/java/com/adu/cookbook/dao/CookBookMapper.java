package com.adu.cookbook.dao;

import com.adu.cookbook.model.CookBook;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yunjie.du
 * @date 2016/7/6 15:42
 */
public interface CookBookMapper {
    CookBook queryCookBookById(@Param("id") long id);

    List<CookBook> queryCookBookByIds(@Param("ids") List<Long> ids);

    List<CookBook> queryCookBookByUserId(@Param("userId") int userId,
                                         @Param("degree") Integer degree,
                                         @Param("categoriesPattern") String categoriesPattern,
                                         @Param("tagsPattern") String tagsPattern,
                                         @Param("materialsPattern") String materialsPattern,
                                         @Param("isHasCoverPic") boolean isHasCoverPic,
                                         @Param("offset") int offset,
                                         @Param("limit") int limit);

    List<CookBook> queryCookBookOrderByCnt(@Param("userId") int userId,
                                           @Param("offset") int offset,
                                           @Param("limit") int limit);

    List<Integer> queryDegreesByUserId(@Param("userId") int userId);

    List<String> queryCategoriesByUserId(@Param("userId") int userId);

    List<String> queryTagsByUserId(@Param("userId") int userId);

    List<String> queryMaterialsByUserId(@Param("userId") int userId);

    int updateById(@Param("userId") int userId, @Param("cookBook") CookBook cookBook);

    int addCnt(@Param("userId") int userId, @Param("id") long id, @Param("cnt") int cnt);

    int insert(CookBook cookBook);

    int deleteById(@Param("userId") int userId, @Param("id") long id);
}
