package com.course.server.service;

import com.course.server.domain.TestExample;
import com.course.server.mapper.TestMapper;
import com.course.server.domain.Test;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author EaApple
 * @2020/4/25 20:38
 * description：
 */
@Service
public class TestService {
    @Resource
    private TestMapper testMapper;

    public List<Test> list(){
        TestExample testExample = new TestExample();
        testExample.setOrderByClause("id desc");
//        testExample.createCriteria();
//        testExample.createCriteria().andIdEqualTo("1");
//        testExample.setOrderByClause("id desc");
        return testMapper.selectByExample(testExample);
    }
}
