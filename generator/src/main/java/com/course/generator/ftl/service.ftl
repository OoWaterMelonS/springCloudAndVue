package com.course.server.service;

import com.course.server.domain.${Domain};
import com.course.server.domain.${Domain}Example;
import com.course.server.dto.${Domain}Dto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.${Domain}Mapper;
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

@Service
public class ${Domain}Service {
@Resource
private ${Domain}Mapper ${domain}Mapper;

public PageDto list(PageDto pageDto){
// 查第一页，查一条
// 注意：pageNum是从1开始的，不是从0开始的
PageHelper.startPage(pageDto.getPage(),pageDto.getSize());

${Domain}Example ${domain}Example = new ${Domain}Example();
List<${Domain}> ${domain}List = ${domain}Mapper.selectByExample(${domain}Example);
        PageInfo<${Domain}> pageInfo = new PageInfo<>(${domain}List);
                pageDto.setTotal(pageInfo.getTotal());
                List<${Domain}Dto> ${domain}DtoList = new ArrayList<${Domain}Dto>();
                                for ( int i = 0,length = ${domain}List.size(); i < length; i++){
                                ${Domain}Dto ${domain}Dto = new ${Domain}Dto();
                                ${Domain} ${domain} = ${domain}List.get(i);
                                if(${domain}!=null) {
                                BeanUtils.copyProperties(${domain}, ${domain}Dto);
                                ${domain}DtoList.add(${domain}Dto);
                                }
                                }
                                pageDto.setList(${domain}List);
                                // 此处的pageDto是从前端拿到的  后端不返回，前端也能拿到这个对象
                                return pageDto;
                                }

                                public ${Domain}Dto save(${Domain}Dto ${domain}Dto) {
                                ${Domain} ${domain} = CopyUtil.copy(${domain}Dto,${Domain}.class);
                                if(StringUtils.isEmpty(${domain}.getId())){
                                this.insert(${domain});
                                }
                                else{
                                this.update(${domain});
                                }
                                return ${domain}Dto;
                                }

                                private void insert(${Domain} ${domain}) {
                                ${domain}.setId(UuidUtil.getShortUuid());

                                ${domain}Mapper.insert(${domain});
                                }

                                private void update(${Domain} ${domain}) {
                                ${domain}Mapper.updateByPrimaryKey(${domain});
                                }

                                public void delete(String id) {
                                ${domain}Mapper.deleteByPrimaryKey(id);
                                }
                                }
