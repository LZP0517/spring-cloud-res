package com.yc.resservice.biz;

import com.yc.resservice.bean.Resorder;
import com.yc.resservice.vo.CartItem;

import java.util.Map;

/**
 * @program: cloud-res
 * @description:
 * @author: 作者
 * @create: 2021-05-16 10:30
 */
public interface ResorderBiz {

    public Map<String, CartItem> findCart();

    public int addCart(Map<String, CartItem> map);

    public int clearAll();


    public int addorder(Resorder resorder);
}
