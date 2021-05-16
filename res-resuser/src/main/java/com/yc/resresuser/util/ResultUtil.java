package com.yc.resresuser.util;

import com.yc.resresuser.vo.Result;

/**
 * @program: cloud-res
 * @description:
 * @author: 作者
 * @create: 2021-05-15 12:53
 */
public class ResultUtil {

    public static Result toTrue(Object obj, String msg) {
        Result result = new Result();
        result.setCode(1);
        result.setObj(obj);
        result.setMsg(msg);
        return result;
    }

    public static Result toFalse(String msg) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg(msg);
        return result;
    }
}
