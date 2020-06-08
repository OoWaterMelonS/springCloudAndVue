package com.course.generator.util;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author EaApple
 * @6/8/2020 10:38 AM
 * description：
 */
public class FreeMarkerUtil {
    // 模板文件一般是固定的   输出文件一般则不固定
    static String ftlPath = "generator\\src\\main\\java\\com\\course\\generator\\ftl\\";
    static Template temp;


    public static void initConfig(String ftlName) throws IOException{
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        cfg.setDirectoryForTemplateLoading(new File(ftlPath));//拿到文件所在文件夹对象
        cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_29));
        temp = cfg.getTemplate(ftlName);//读取文件夹具体模板文件
    }
    public static void generator(String fileNAme) throws IOException, TemplateException {
        FileWriter fw = new FileWriter(fileNAme);
        BufferedWriter bw = new BufferedWriter(fw);
        temp.process(null, bw);
        bw.flush();
        fw.close();
    }


}
