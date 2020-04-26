package com.course.server.service;

import com.course.server.domain.Chapter;
import com.course.server.domain.ChapterExample;
import com.course.server.dto.ChapterDto;
import com.course.server.mapper.ChapterMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author EaApple
 * @2020/4/25 20:38
 * description：
 */
@Service
public class ChapterService {
    @Resource
    private ChapterMapper chapterMapper;

    public List<ChapterDto> list(){
        ChapterExample chapterExample = new ChapterExample();
        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);
        List<ChapterDto> chapterDtoList = new ArrayList<ChapterDto>();
        for ( int i = 0,length = chapterList.size(); i < length; i++){
            ChapterDto chapterDto = new ChapterDto();
            Chapter chapter = chapterList.get(i);
            if(chapter!=null) {
                BeanUtils.copyProperties(chapter, chapterDto);
                chapterDtoList.add(chapterDto);
            }
        }
        return chapterDtoList;
    }
}
