package com.yc.resweb.controller;

import com.yc.resweb.bean.Resorder;
import com.yc.resweb.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @program: cloud-res
 * @description:
 * @author: 作者
 * @create: 2021-05-13 19:37
 */
@FeignClient(value = "res-service-9200", fallback = FallbackController.class)
public interface ServiceController {

    @GetMapping(value = "feign/service")
    public String getSer();

    @RequestMapping(value = "findAllFoods", method = RequestMethod.GET)
    public Result findALlFoods();

    @RequestMapping(value = "addCart", method = RequestMethod.POST)
    public Result addCart(@RequestParam("fid") int fid, @RequestParam("num") int num);

    @RequestMapping(value = "clearAll", method = RequestMethod.POST)
    public Result clearAll();

    @RequestMapping(value = "getCartInfo", method = RequestMethod.POST)
    public Result getCartInfo();

    @RequestMapping(value = "addorder", method = RequestMethod.POST)
    public Result addorder(@RequestBody Resorder resorder);
}
