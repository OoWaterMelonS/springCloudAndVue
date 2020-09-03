package com.course.business.controller.admin;

import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.ChapterService;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author EaApple
 * @2020/4/25 23:18
 * description：
 */
@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {

    private static final Logger LOG = LoggerFactory.getLogger(ChapterController.class);
    //aop 业务反射的约定
    public static final String BUSINESS_NAME="大章";

    @Resource
    private ChapterService chapterService;

    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto){
        // 此处考不考虑直接新建一个静态方法呢
        ResponseDto responseDto = ResponseDto.getInstance();
        // 直接就是使用的前端返回的dto 所以不必返回
        chapterService.list(pageDto);
        LOG.info("pageDto={}",pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }
    @PostMapping("/save")
    public ResponseDto save(@RequestBody ChapterDto chapterDto){
        LOG.info("chapterDto:{}",chapterDto);

        //保存校验
        ValidatorUtil.require(chapterDto.getName(),"名称");
        ValidatorUtil.require(chapterDto.getCourseId(),"课程ID");
        ValidatorUtil.length(chapterDto.getCourseId(),"课程ID",1,8);

        ResponseDto responseDto = ResponseDto.getInstance();
        chapterService.save(chapterDto);
        responseDto.setContent(chapterDto);
        return responseDto;
    }


    @DeleteMapping("/delete/{id}")
//    @RequestMapping("/delete/{id}/{name}")
//    public ResponseDto delete(@PathVariable String id,@PathVariable String name){
    public ResponseDto delete(@PathVariable String id){
        ResponseDto responseDto = ResponseDto.getInstance();
        LOG.info("id:{}",id);
        chapterService.delete(id);
        return responseDto;
    }


    @PostMapping("/save1")
    public ResponseDto save1(@RequestBody ChapterDto chapterDto){
        LOG.info("chapterDto:{}",chapterDto);

        ResponseDto responseDto = ResponseDto.getInstance();
        chapterService.save1(chapterDto);
        responseDto.setContent(chapterDto);
        return responseDto;
    }
}
