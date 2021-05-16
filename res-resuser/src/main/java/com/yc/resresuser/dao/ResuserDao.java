package com.yc.resresuser.dao;

import com.yc.resresuser.bean.Resuser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: cloud-res
 * @description:
 * @author: 作者
 * @create: 2021-05-15 12:21
 */
public interface ResuserDao extends JpaRepository<Resuser, Integer> {
}
