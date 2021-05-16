package com.yc.resservice.biz;

import com.yc.resservice.bean.Resfood;

import java.util.List;

/**
 * @program: cloud-res
 * @description:
 * @author: 作者
 * @create: 2021-05-15 15:04
 */
public interface ResfoodBiz {
    public List<Resfood> findAll();
}
