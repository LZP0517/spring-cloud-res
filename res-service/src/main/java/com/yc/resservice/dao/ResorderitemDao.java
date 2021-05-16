package com.yc.resservice.dao;

import com.yc.resservice.bean.Resorderitem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: cloud-res
 * @description:
 * @author: 作者
 * @create: 2021-05-16 15:20
 */
public interface ResorderitemDao extends JpaRepository<Resorderitem, Integer> {
}
