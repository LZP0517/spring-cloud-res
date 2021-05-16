package com.yc.resservice.biz;

import com.yc.resservice.bean.Resfood;
import com.yc.resservice.dao.ResfoodDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: cloud-res
 * @description:
 * @author: 作者
 * @create: 2021-05-15 15:05
 */
@Service
public class ResfoodBizImpl implements ResfoodBiz {
    @Resource
    private ResfoodDao resfoodDao;

    @Override
    public List<Resfood> findAll() {
        return resfoodDao.findAll();
    }
}
