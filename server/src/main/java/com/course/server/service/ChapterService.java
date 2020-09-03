package com.course.server.service;

import com.course.server.domain.Chapter;
import com.course.server.domain.ChapterExample;
import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.ChapterMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    public void list(PageDto pageDto){
        // 查第一页，查一条
        // 注意：pageNum是从1开始的，不是从0开始的
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());

        ChapterExample chapterExample = new ChapterExample();
        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);
        PageInfo<Chapter> pageInfo = new PageInfo<>(chapterList);
        pageDto.setTotal(pageInfo.getTotal());
        List<ChapterDto> chapterDtoList = new ArrayList<ChapterDto>();

        for ( int i = 0,length = chapterList.size(); i < length; i++){
            ChapterDto chapterDto = new ChapterDto();
            Chapter chapter = chapterList.get(i);
            if(chapter!=null) {
                BeanUtils.copyProperties(chapter, chapterDto);
                chapterDtoList.add(chapterDto);
            }
        }

        pageDto.setList(chapterList);
        // 此处的pageDto是从前端拿到的  后端不返回，前端也能拿到这个对象
//        return pageDto;
    }

    public ChapterDto save(ChapterDto chapterDto) {
        Chapter chapter = CopyUtil.copy(chapterDto,Chapter.class);
        if(StringUtils.isEmpty(chapter.getId())){
            this.insert(chapter);
        }
        else{
            this.update(chapter);
        }
        return chapterDto;
    }

    public void save1(ChapterDto chapterDto) {
        Chapter chapter = CopyUtil.copy(chapterDto,Chapter.class);
        chapter.setId(UuidUtil.getShortUuid());
        chapterMapper.insert(chapter);
    }

    private void insert(Chapter chapter) {
        chapter.setId(UuidUtil.getShortUuid());

        chapterMapper.insert(chapter);
    }

    private void update(Chapter chapter) {
        chapterMapper.updateByPrimaryKey(chapter);
    }

    public void delete(String id) {
        chapterMapper.deleteByPrimaryKey(id);
    }



}
