package com.yc.resservice.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: cloud-res
 * @description:
 * @author: 作者
 * @create: 2021-05-15 12:09
 */
@Data
@Entity
@Table(name = "resorderitem")
public class Resorderitem implements Serializable {


    private static final long serialVersionUID = -7870756598363772352L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roiid;
    private Integer roid;
    private Integer fid;
    private BigDecimal dealprice;
    private Integer num;
}
