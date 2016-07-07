package com.adu.cookbook.service.impl;

import com.adu.cookbook.constants.CookDegreeEnum;
import com.adu.cookbook.dao.CookBookMapper;
import com.adu.cookbook.model.CookBook;
import com.adu.cookbook.service.CookBookService;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author yunjie.du
 * @date 2016/7/6 16:40
 */
@Service
public class CookBookServiceImpl implements CookBookService {
    @Autowired
    private CookBookMapper cookBookMapper;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public CookBook getCookBookById(long id) {
        return cookBookMapper.queryCookBookById(id);
    }

    @Override
    public List<CookBook> getCookBookByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Lists.newArrayList();
        }
        return cookBookMapper.queryCookBookByIds(ids);
    }

    @Override
    public List<CookBook> getCookBookByUserId(int userId, Integer degree, List<String> categories, List<String> tags, List<String> materials, boolean isHasCoverPic, int offset, int limit) {
        return cookBookMapper.queryCookBookByUserId(userId, degree, createPattern(categories), createPattern(tags), createPattern(materials), isHasCoverPic, offset, limit);
    }

    @Override
    public List<CookBook> getCookBookOrderByCnt(int userId, int offset, int limit) {
        return cookBookMapper.queryCookBookOrderByCnt(userId, offset, limit);
    }

    @Override
    public List<CookBook> getGuessLike(int userId, int offset, int limit) {
        //TODO
        return this.getCookBookOrderByCnt(userId, offset, limit);
    }

    @Override
    public Set<String> getDegreesByUserId(int userId) {
        Set<String> res = Sets.newHashSet();

        List<Integer> degrees = cookBookMapper.queryDegreesByUserId(userId);
        for (int degree : degrees) {
            res.add(CookDegreeEnum.codeOf(degree).getDesc());
        }
        return res;
    }


    @Override
    public Set<String> getCategoriesByUserId(int userId) {

        return transform(cookBookMapper.queryCategoriesByUserId(userId));
    }

    @Override
    public Set<String> getTagsByUserId(int userId) {
        return transform(cookBookMapper.queryTagsByUserId(userId));
    }

    @Override
    public Set<String> getMaterialsByUserId(int userId) {
        return transform(cookBookMapper.queryMaterialsByUserId(userId));
    }

    @Override
    public int updateById(int userId, CookBook cookBook) {
        if (cookBook == null) {
            return 0;
        }
        return cookBookMapper.updateById(userId, cookBook);
    }

    @Override
    public int addCnt(int userId, long id, int cnt) {
        return cookBookMapper.addCnt(userId, id, cnt);
    }

    @Override
    public int insert(CookBook cookBook) {
        return cookBookMapper.insert(Objects.requireNonNull(cookBook, "cookBook can NOT be null!"));
    }

    @Override
    public int deleteById(int userId, long id) {
        return cookBookMapper.deleteById(userId, id);
    }

    private String createPattern(List<String> items) {
        if (CollectionUtils.isEmpty(items)) {
            return null;
        }
        String res = Joiner.on("|").join(items);
        return res;
    }

    private Set<String> transform(List<String> list) {
        Set res = Sets.newHashSet();
        if (CollectionUtils.isEmpty(list)) {
            return res;
        }
        for (String str : list) {
            if (StringUtils.isNotEmpty(str)) {
                res.addAll(Splitter.on(";").splitToList(str));
            }
        }
        return res;
    }
}
