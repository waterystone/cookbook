package com.adu.cookbook;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试服务基类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/app-root.xml"
})
public abstract class BaseTest {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
}
