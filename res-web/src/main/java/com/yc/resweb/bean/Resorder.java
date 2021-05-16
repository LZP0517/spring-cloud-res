package com.yc.resweb.bean;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: cloud-res
 * @description:
 * @author: 作者
 * @create: 2021-05-15 12:08
 */
@Data
public class Resorder implements Serializable {
    private static final long serialVersionUID = 5023361450826079785L;
    private Integer roid;
    private Integer userid;
    private String address;
    private String tel;
    private Timestamp ordertime; //时间戳
    private Timestamp deliverytime;
    private String ps;
    private Integer status;

    private String deliverytimeString;

    public Timestamp getDeliverytime() {
        Date d = null;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            d = sf.parse(this.deliverytimeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.deliverytime = new Timestamp(d.getTime());
        return this.deliverytime;
    }

    public Resorder() {
        this.ordertime = new Timestamp(System.currentTimeMillis());
    }
}
