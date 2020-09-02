package com.course.server.util;


import com.course.server.vo.ResultVo;

/**
 * 通用返回工具
 */
public class ResultVoUtil {
    public static  <T> ResultVo<T> success(T object){
        ResultVo<T> resultVo = new ResultVo<>();
        resultVo.setData(object);
        resultVo.setCode(0);
        resultVo.setMsg("request success");
        return resultVo;
    }
    public static <T> ResultVo<T> success(){
        return success(null);
    }
    public static <T> ResultVo<T> error(Integer code ,String msg){
        ResultVo<T> resultVo = new ResultVo<>();
        resultVo.setCode(code);
        resultVo.setMsg(msg);
        return  resultVo;
    }

    public static <T> ResultVo<T> error(){
        ResultVo<T> resultVo = new ResultVo<>();
        resultVo.setCode(-1);
        resultVo.setMsg("error");
        return  resultVo;
    }
    public static <T> ResultVo<T> error(String errorMessage){
        ResultVo<T> resultVo = new ResultVo<>();
        resultVo.setCode(-1);
        resultVo.setMsg(errorMessage);
        return  resultVo;
    }
}
