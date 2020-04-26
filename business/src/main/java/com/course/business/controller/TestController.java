package com.course.business.controller;

import com.course.server.domain.Test;
import com.course.server.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author EaApple
 * @2020/4/25 23:18
 * description：
 */
@RestController
public class TestController {

    @Resource
    private TestService testService;

    @RequestMapping("/test1")
    public String test1(){
        return "system module";
    }

    @RequestMapping("/test")
    public List<Test> test(){
        return testService.list();
    }
}
