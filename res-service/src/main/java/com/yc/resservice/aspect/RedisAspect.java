package com.yc.resservice.aspect;

import com.alibaba.fastjson.JSONObject;
import com.yc.resservice.bean.Resuser;
import com.yc.resservice.vo.YcContent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @program: cloud-res
 * @description:
 * @author: 作者
 * @create: 2021-05-15 16:16
 */
@Component
@Aspect
@Slf4j
public class RedisAspect {
    @Resource
    private RedisTemplate template;

    @Around("execution(public * com.yc.resservice.biz.ResfoodBizImpl.findAll(..))")
    public Object findAll(ProceedingJoinPoint point) throws Throwable {
        if (template.hasKey(YcContent.ALLFOODS)) {
            log.info("从redis中获取所有菜品。。。。");
            return template.opsForValue().get(YcContent.ALLFOODS);
        }
        log.info("从数据库中获取所有菜品。。。。");
        Object o = point.proceed();
        template.opsForValue().set(YcContent.ALLFOODS, o);
        return o;
    }

    @Around("execution(public * com.yc.resservice.biz.ResorderBizImpl.findCart(..))")
    public Object findCart(ProceedingJoinPoint point) throws Throwable {
        Object o = null;
        if (!template.hasKey(YcContent.LOGINUSER)) {
            log.info("用户未登录..");
            o = point.proceed();
            return o;
        }
        ValueOperations<String, String> valueOperations = template.opsForValue();
        String json = valueOperations.get(YcContent.LOGINUSER);
        Resuser user = JSONObject.parseObject(json, Resuser.class);
        if (!template.hasKey(YcContent.CART + "-" + user.getUserid())) {
            log.info(user.getUserid() + ":购物车为空");
            o = point.proceed();
            return o;
        }
        return template.opsForValue().get(YcContent.CART + "-" + user.getUserid());
    }

    @Around("execution(public * com.yc.resservice.biz.ResorderBizImpl.addCart(..))")
    public Object addCart(ProceedingJoinPoint point) throws Throwable {
        Object o = null;
        if (!template.hasKey(YcContent.LOGINUSER)) {
            log.info("用户未登录..");
            o = point.proceed();
            return o;
        }
        ValueOperations<String, String> valueOperations = template.opsForValue();
        String json = valueOperations.get(YcContent.LOGINUSER);
        Resuser user = JSONObject.parseObject(json, Resuser.class);

        Object obj = point.getArgs()[0];
        template.opsForValue().set(YcContent.CART + "-" + user.getUserid(), obj);
        return 1;
    }

    @Around("execution(public * com.yc.resservice.biz.ResorderBizImpl.clearAll(..))")
    public Object clearAll(ProceedingJoinPoint point) throws Throwable {
        Object o = null;
        if (!template.hasKey(YcContent.LOGINUSER)) {
            log.info("用户未登录..");
            o = point.proceed();
            return o;
        }
        ValueOperations<String, String> valueOperations = template.opsForValue();
        String json = valueOperations.get(YcContent.LOGINUSER);
        Resuser user = JSONObject.parseObject(json, Resuser.class);
        if (template.hasKey(YcContent.CART + "-" + user.getUserid())) {
            log.info(user.getUserid() + ":购物车清空");
            template.delete(YcContent.CART + "-" + user.getUserid());
            return 1;
        }
        return 1;
    }
}