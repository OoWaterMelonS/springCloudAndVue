package com.course.generator.server;

import com.course.generator.util.FreeMarkerUtil;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * @author EaApple
 * @6/8/2020 10:45 AM
 * description：
 */
public class ServerGenerator {
    static String toPath = "generator\\src\\main\\java\\com\\course\\generator\\test\\";
    public static void main(String[] args) throws IOException, TemplateException {
        // 配置输入
        FreeMarkerUtil.initConfig("test.ftl");
//        配置输出
        FreeMarkerUtil.generator(toPath+"Test.java");
    }
}
