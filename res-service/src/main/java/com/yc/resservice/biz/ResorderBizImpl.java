package com.yc.resservice.biz;

import com.alibaba.fastjson.JSONObject;
import com.yc.resservice.bean.Resorder;
import com.yc.resservice.bean.Resorderitem;
import com.yc.resservice.bean.Resuser;
import com.yc.resservice.dao.ResorderDao;
import com.yc.resservice.dao.ResorderitemDao;
import com.yc.resservice.vo.CartItem;
import com.yc.resservice.vo.YcContent;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;

/**
 * @program: cloud-res
 * @description:
 * @author: 作者
 * @create: 2021-05-16 10:31
 */
@Service
@Transactional
public class ResorderBizImpl implements ResorderBiz {
    @Resource
    private ResorderDao resorderDao;
    @Resource
    private ResorderitemDao resorderitemDao;

    @Override
    public Map<String, CartItem> findCart() {
        return null;
    }

    @Override
    public int addCart(Map<String, CartItem> map) {
        return 0;
    }

    @Override
    public int clearAll() {
        return 0;
    }

    @Resource
    private RedisTemplate template;

    @Override
    public int addorder(Resorder resorder) {
        Object o = null;
        if (!template.hasKey(YcContent.LOGINUSER)) {
            return 0;
        }
        ValueOperations<String, String> valueOperations = template.opsForValue();
        String json = valueOperations.get(YcContent.LOGINUSER);
        Resuser user = JSONObject.parseObject(json, Resuser.class);
        if (!template.hasKey(YcContent.CART + "-" + user.getUserid())) {
            return 0;
        }
        Map<String, CartItem> map = (Map<String, CartItem>) template.opsForValue().get(YcContent.CART + "-" + user.getUserid());
        if (map == null || map.size() <= 0) {
            return 0;
        }
        
        System.out.println(resorder);
        Resorder resorder1 = resorderDao.save(resorder);
        CartItem item = null;
        Resorderitem resorderitem = new Resorderitem();
        Set<String> keys = map.keySet();
        for (String key : keys) {
            item = map.get(key);
            resorderitem.setDealprice(item.getSmallCount());
            resorderitem.setNum(item.getNum());
            resorderitem.setRoid(resorder1.getRoid());
            resorderitem.setFid(item.getFood().getFid());
            resorderitemDao.save(resorderitem);
        }
        clearAll();

        return 1;
    }


}
