package com.yc.resweb.controller;

import com.yc.resweb.bean.Resorder;
import com.yc.resweb.bean.Resuser;
import com.yc.resweb.util.ResultUtil;
import com.yc.resweb.vo.Result;
import org.springframework.stereotype.Component;

/**
 * @program: cloud-res
 * @description:
 * @author: 作者
 * @create: 2021-05-13 20:34
 */
@Component
public class FallbackController implements ServiceController, UserController {

    @Override
    public String getSer() {
        return "hyservice";
    }

    @Override
    public Result findALlFoods() {
        return ResultUtil.toFalse("远程服务失效。。");
    }

    @Override
    public Result login(Resuser resuser) {
        return ResultUtil.toFalse("远程服务失效。。");
    }

    @Override
    public Result logout() {
        return ResultUtil.toFalse("远程服务失效。。");
    }

    @Override
    public Result checkLogin() {
        return ResultUtil.toFalse("远程服务失效。。");
    }

    @Override
    public Result addCart(int id, int num) {
        return ResultUtil.toFalse("远程服务失效。。");
    }

    @Override
    public Result clearAll() {
        return ResultUtil.toFalse("远程服务失效。。");
    }

    @Override
    public Result getCartInfo() {
        return ResultUtil.toFalse("远程服务失效。。");
    }

    @Override
    public Result addorder(Resorder resorder) {
        return ResultUtil.toFalse("远程服务失效。。");
    }
}
