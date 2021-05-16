package com.yc.resservice.controller;

import com.yc.resservice.bean.Resfood;
import com.yc.resservice.bean.Resorder;
import com.yc.resservice.biz.ResfoodBiz;
import com.yc.resservice.biz.ResorderBiz;
import com.yc.resservice.util.ResultUtil;
import com.yc.resservice.vo.CartItem;
import com.yc.resservice.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * @program: cloud-res
 * @description:
 * @author: 作者
 * @create: 2021-05-16 10:20
 */
@RestController
public class ResorderController {
    @Resource
    private ResorderBiz resorderBiz;
    @Resource
    private ResfoodBiz resfoodBiz;


    @RequestMapping(value = "getCartInfo", method = RequestMethod.POST)
    public Result getCartInfo() {
        List<CartItem> cList = new ArrayList<>();
        Map<String, CartItem> map = resorderBiz.findCart();
        if (map == null) {
            return ResultUtil.toTrue(cList, null);
        }
        Set<String> keys = map.keySet();
        for (String key : keys) {
            cList.add(map.get(key));
        }
        return ResultUtil.toTrue(cList, null);
    }

    @RequestMapping(value = "clearAll", method = RequestMethod.POST)
    public Result clearALl() {
        int z = resorderBiz.clearAll();
        if (z == 1) {
            return ResultUtil.toTrue(null, "清空成功..");
        }
        return ResultUtil.toFalse("清空失败..");
    }

    @RequestMapping(value = "addorder", method = RequestMethod.POST)
    public Result addorder(@RequestBody Resorder resorder) {
        System.out.println(resorder + "11111");
        int z = resorderBiz.addorder(resorder);
        if (z == 1) {
            return ResultUtil.toTrue(null, "提交成功.");
        }
        return ResultUtil.toFalse("提交失败");
    }

    @RequestMapping(value = "addCart", method = RequestMethod.POST)
    public Result addCart(@RequestParam("fid") int fid, @RequestParam("num") int num) {
        List<Resfood> list = resfoodBiz.findAll();
        Resfood f = null;
        for (Resfood r : list) {
            if (fid == r.getFid()) {
                f = r;
                break;
            }
        }
        CartItem cartItem = new CartItem(f, num);
        Map<String, CartItem> map = resorderBiz.findCart();
        if (map == null) {
            map = new HashMap<String, CartItem>();
        }
        if (map.containsKey("" + fid)) {
            CartItem item = map.get("" + fid);
            cartItem.setNum(item.getNum() + cartItem.getNum());
            cartItem.setSmallCount(item.getSmallCount().add(cartItem.getSmallCount()));
        }
        if (cartItem.getNum() == 0) {
            map.remove("" + fid);
        } else {
            map.put("" + fid, cartItem);
        }

        int z = resorderBiz.addCart(map);
        if (z == 1) {
            List<CartItem> cList = new ArrayList<>();
            Set<String> keys = map.keySet();
            for (String key : keys) {
                cList.add(map.get(key));
            }
            return ResultUtil.toTrue(cList, null);
        }

        return ResultUtil.toFalse("添加失败..");
    }
}
