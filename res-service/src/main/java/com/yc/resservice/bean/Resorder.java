package com.yc.resservice.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
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
@Entity
@Table(name = "resorder")
public class Resorder implements Serializable {
    private static final long serialVersionUID = 5023361450826079785L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roid;
    private Integer userid;
    private String address;
    private String tel;
    private Timestamp ordertime; //时间戳
    private Timestamp deliverytime;
    private String ps;
    private Integer status;
    @Transient  //数据库无此键 排除此属性
    private String deliverytimeString;

    public Timestamp getDeliverytime() {
        Date d = null;
        if (deliverytimeString != null) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            try {
                d = df.parse(deliverytimeString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            deliverytime = new Timestamp(d.getTime());
        } else {
            d = new Date();
        }
        deliverytime = new Timestamp(d.getTime());
        return this.deliverytime;
    }

    public Resorder() {
        this.ordertime = new Timestamp(System.currentTimeMillis());
    }
}
