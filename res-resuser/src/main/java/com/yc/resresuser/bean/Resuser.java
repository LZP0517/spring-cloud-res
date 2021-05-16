package com.yc.resresuser.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @program: cloud-res
 * @description:
 * @author: 作者
 * @create: 2021-05-15 12:08
 */
@Data
@Entity
@Table(name = "resuser")
public class Resuser implements Serializable {
    private static final long serialVersionUID = 5712492979805249361L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer userid;
    private String username;
    private String pwd;
    private String email;
}
