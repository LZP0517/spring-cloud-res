package com.yc.resweb.bean;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: cloud-res
 * @description:
 * @author: 作者
 * @create: 2021-05-15 12:09
 */
@Data
public class Resfood implements Serializable {
    private static final long serialVersionUID = -6211290303758576355L;
    private Integer fid;
    private String fname;
    private BigDecimal normprice;
    private BigDecimal realprice;
    private String detail;
    private String fphoto;
}
