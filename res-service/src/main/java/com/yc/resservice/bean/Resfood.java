package com.yc.resservice.bean;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @program: cloud-res
 * @description:
 * @author: 作者
 * @create: 2021-05-15 12:09
 */
@Data
@Entity
@Table(name = "resfood")
public class Resfood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fid;
    private String fname;
    private BigDecimal normprice;
    private BigDecimal realprice;
    private String detail;
    private String fphoto;
}
