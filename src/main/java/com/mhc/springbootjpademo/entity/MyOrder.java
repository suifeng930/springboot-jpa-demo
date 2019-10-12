package com.mhc.springbootjpademo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @description:
 * @author: mahongcheng
 * @createDate: 2019/10/12
 * @version: 1.0
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * 订单编号
     */
    private String code;
    /**
     * 客户表id 外键
     */
    private Long cId;
    /**
     * 订单金额
     */
    private BigDecimal total;
    /**
     *实体映射重复列必须设置：insertable = false,updatable = false
     *
     * 订单和客户是一对一关系
     */
    @OneToOne
    @JoinColumn(name = "cId",insertable = false,updatable = false)
    private Customer customer;

    public MyOrder(String code,Long cId,BigDecimal total){
        this.code=code;
        this.cId=cId;
        this.total=total;
    }
    @Override
    public String toString() {
        return "MyOrder{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", cId=" + cId +
                ", total=" + total +
                ", customer=" + customer +
                '}';
    }
}
