package com.yc.resweb.controller;

import com.yc.resweb.bean.Resuser;
import com.yc.resweb.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @program: cloud-res
 * @description:
 * @author: 作者
 * @create: 2021-05-15 13:02
 */
@FeignClient(value = "res-resuser-9600", fallback = FallbackController.class)
public interface UserController {

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Result login(@RequestBody Resuser resuser);

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public Result logout();

    @RequestMapping(value = "checkLogin", method = RequestMethod.GET)
    public Result checkLogin();
}
