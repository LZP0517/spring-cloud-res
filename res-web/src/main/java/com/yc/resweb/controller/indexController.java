package com.yc.resweb.controller;

import com.yc.resweb.bean.Resorder;
import com.yc.resweb.bean.Resuser;
import com.yc.resweb.util.ResultUtil;
import com.yc.resweb.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @program: cloud-res
 * @description:
 * @author: 作者
 * @create: 2021-05-13 19:18
 */
@RestController
//@RefreshScope //在git上修改信息后 实时刷新获取到的配置信息
public class indexController {

    @Resource
    private RestTemplate template;
    @Resource
    private ServiceController serviceController;
    @Resource
    UserController userController;

    @RequestMapping(value = "user/login", method = {RequestMethod.GET, RequestMethod.POST})
    public Result login(Resuser resuser, HttpSession session, String valCode) {
        if (!((String) session.getAttribute("validateCode")).equalsIgnoreCase(valCode)) {
            return ResultUtil.toFalse("验证码错误..");
        }
        return userController.login(resuser);
    }

    @RequestMapping(value = "findAllFoods", method = {RequestMethod.GET, RequestMethod.POST})
    public Result findALl() {
        return serviceController.findALlFoods();
    }

    @RequestMapping(value = "logout", method = {RequestMethod.GET, RequestMethod.POST})
    public Result logout() {
        return userController.logout();
    }

    @RequestMapping(value = "checkLogin", method = {RequestMethod.GET, RequestMethod.POST})
    public Result checkLogin() {
        return userController.checkLogin();
    }

    @RequestMapping(value = "addCart", method = {RequestMethod.GET, RequestMethod.POST})
    public Result addCart(@RequestParam("fid") int fid, @RequestParam("num") int num) {
        return serviceController.addCart(fid, num);
    }

    @RequestMapping(value = "clearAll", method = {RequestMethod.GET, RequestMethod.POST})
    public Result clearAll() {
        return serviceController.clearAll();
    }

    @RequestMapping(value = "getCartInfo", method = {RequestMethod.GET, RequestMethod.POST})
    public Result getCartInfo() {
        return serviceController.getCartInfo();
    }

    @RequestMapping(value = "addorder", method = RequestMethod.POST)
    public Result addorder(Resorder resorder) {
        resorder.getDeliverytime();
        System.out.println(resorder + "2222");
        return serviceController.addorder(resorder);
    }
//    @GetMapping(value = "service")
//    public String getService() {
//        String url = "http://res-service-9200/service";
//        return template.getForObject(url, String.class);
//    }
//
//    @Value("${mysql.url}")
//    private String hello;
//
//
//    @Value("${test}")
//    private String test;
//
//    @GetMapping("web/hello")
//    private String getHello() {
//        return this.hello + ",," + this.test;
//    }
//
//    @GetMapping(value = "feign/service")
//    public String getSer() {
//        return serviceController.getSer();
//    }


}
