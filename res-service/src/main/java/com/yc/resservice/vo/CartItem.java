package com.yc.resservice.vo;

import com.yc.resservice.bean.Resfood;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: cloud-res
 * @description:
 * @author: 作者
 * @create: 2021-05-16 10:22
 */
@Data
public class CartItem implements Serializable {
    private static final long serialVersionUID = 7015879309494365558L;
    private Resfood food;
    private BigDecimal smallCount; //小计
    private Integer num;

    public BigDecimal getSmallCount() {
        this.smallCount = food.getRealprice().multiply(new BigDecimal(num));
        return this.smallCount;
    }

    public CartItem(Resfood resfood, Integer num) {
        this.food = resfood;
        this.num = num;
        this.smallCount = resfood.getRealprice().multiply(new BigDecimal(num));
    }

    public CartItem() {

    }
}
