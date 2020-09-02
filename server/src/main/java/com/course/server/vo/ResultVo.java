package com.course.server.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 包装结果
 * @param <T>
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVo<T> {
    private Integer code;
    private String msg;
    private T data;
}
