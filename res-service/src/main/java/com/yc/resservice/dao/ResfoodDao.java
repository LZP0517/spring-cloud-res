package com.yc.resservice.dao;

import com.yc.resservice.bean.Resfood;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: cloud-res
 * @description:
 * @author: 作者
 * @create: 2021-05-15 15:04
 */
public interface ResfoodDao extends JpaRepository<Resfood, Integer> {
}
