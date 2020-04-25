package com.course.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author EaApple
 * @2020/4/25 17:35
 * description：
 */
@RestController
public class TestController {


    @RequestMapping("/test")
    public String test(){
        return "system module";
    }
}
