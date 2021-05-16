package com.yc.resresuser.controller;

import com.alibaba.fastjson.JSONObject;
import com.yc.resresuser.bean.Resuser;
import com.yc.resresuser.biz.ResuserBiz;
import com.yc.resresuser.util.ResultUtil;
import com.yc.resresuser.vo.Result;
import com.yc.resresuser.vo.YcContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: cloud-res
 * @description:
 * @author: 作者
 * @create: 2021-05-15 12:49
 */
@RestController
@Slf4j
public class ResuserController {
    @Resource
    private RedisTemplate template;
    @Resource
    private ResuserBiz biz;

    @RequestMapping(value = "login", method = {RequestMethod.GET, RequestMethod.POST})
    public Result login(@RequestBody Resuser resuser) {
        Resuser user = biz.login(resuser);
        if (user == null) {
            return ResultUtil.toFalse("账号或密码错误..");
        }
        log.info("登陆信息存入redis缓存。");

        String s = JSONObject.toJSONString(user);
        ValueOperations<String, String> valueOperations = template.opsForValue();
        valueOperations.set(YcContent.LOGINUSER, s);
        log.info(user.getUserid() + "登录成功..");
        return ResultUtil.toTrue(user, "登陆成功");
    }

    @RequestMapping(value = "logout", method = {RequestMethod.GET, RequestMethod.POST})
    public Result logout() {
        if (template.hasKey(YcContent.LOGINUSER)) {
            ValueOperations<String, String> valueOperations = template.opsForValue();
            String s = valueOperations.get(YcContent.LOGINUSER);
            Resuser user = JSONObject.parseObject(s, Resuser.class);
//            Resuser user = (Resuser) template.opsForValue().get(YcContent.LOGINUSER);
            log.info(user.getUserid() + "退出。。。");
            template.delete(YcContent.LOGINUSER);
            return ResultUtil.toTrue(null, user.getUserid() + "退出。。。");
        }
        return ResultUtil.toTrue(null, "暂无用户登录..");
    }

    @RequestMapping(value = "checkLogin", method = {RequestMethod.GET, RequestMethod.POST})
    public Result checkLogin() {
        if (template.hasKey(YcContent.LOGINUSER)) {
            ValueOperations<String, String> valueOperations = template.opsForValue();
            String s = valueOperations.get(YcContent.LOGINUSER);
            Resuser user = JSONObject.parseObject(s, Resuser.class);
//            Resuser user = (Resuser) template.opsForValue().get(YcContent.LOGINUSER);
            return ResultUtil.toTrue(user, user.getUserid() + "已登录.");
        }
        return ResultUtil.toFalse("暂无用户登录..");
    }
}
