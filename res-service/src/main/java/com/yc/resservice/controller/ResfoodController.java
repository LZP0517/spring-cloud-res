package com.yc.resservice.controller;

import com.yc.resservice.bean.Resfood;
import com.yc.resservice.biz.ResfoodBiz;
import com.yc.resservice.util.ResultUtil;
import com.yc.resservice.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: cloud-res
 * @description:
 * @author: 作者
 * @create: 2021-05-15 16:30
 */
@RestController
public class ResfoodController {
    @Resource
    private ResfoodBiz resfoodBiz;

    @RequestMapping(value = "findAllFoods", method = {RequestMethod.GET, RequestMethod.POST})
    public Result findAllFoods() {
        List<Resfood> list = resfoodBiz.findAll();
        if (list == null || list.isEmpty()) {
            return ResultUtil.toFalse("暂无菜品...");
        }
        return ResultUtil.toTrue(list, null);
    }
}
