package com.yc.resservice.dao;

import com.yc.resservice.bean.Resorder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: cloud-res
 * @description:
 * @author: 作者
 * @create: 2021-05-16 15:19
 */
public interface ResorderDao extends JpaRepository<Resorder, Integer> {
}
