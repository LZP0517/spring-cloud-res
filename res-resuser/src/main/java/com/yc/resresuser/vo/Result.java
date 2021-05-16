package com.yc.resresuser.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: cloud-res
 * @description:
 * @author: 作者
 * @create: 2021-05-15 12:51
 */
@Data
public class Result implements Serializable {
    private static final long serialVersionUID = -7493097546580681446L;
    private Integer code;
    private Object obj;
    private String msg;
}
