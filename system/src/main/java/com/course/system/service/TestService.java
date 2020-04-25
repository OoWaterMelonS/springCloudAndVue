package com.course.system.service;

import com.course.system.domain.Test;
import com.course.system.mapper.TestMapper;
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
        return testMapper.list();
    }
}
