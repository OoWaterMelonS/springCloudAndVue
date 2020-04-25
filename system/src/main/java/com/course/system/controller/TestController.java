package com.course.system.controller;

import com.course.system.domain.Test;
import com.course.system.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author EaApple
 * @2020/4/25 17:35
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
