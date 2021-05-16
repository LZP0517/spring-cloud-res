package com.yc.resresuser.biz;

import com.yc.resresuser.bean.Resuser;
import com.yc.resresuser.dao.ResuserDao;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @program: cloud-res
 * @description:
 * @author: 作者
 * @create: 2021-05-15 12:23
 */
@Service
public class ResuserBizImpl implements ResuserBiz {
    @Resource
    private ResuserDao resuserDao;

    @Override
    public Resuser login(Resuser resuser) {
        if (resuser.getPwd().equals("") || resuser.getPwd() == null || resuser.getUsername() == null || resuser.getUsername().equals("")) {
            return null;
        }
        Example<Resuser> example = Example.of(resuser);
        Optional<Resuser> o = resuserDao.findOne(example);
        return o.orElse(null);
    }
}
