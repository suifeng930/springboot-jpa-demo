package com.mhc.springbootjpademo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * @description:
 * @author: mahongcheng
 * @createDate: 2019/10/11
 * @version: 1.0
 */
@Entity
/**
 * 这里的sql表达式里的表名要和当前的Entity一致，否则会找不到，报错！！！查询参数也要和实体进行对应起来，是firstName而不是first_name,切记！
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name="Customer.findByFirstName",query = "select c from Customer c where c.firstName = ?1")
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;

    /**
     *一对多，一个客户对应多个订单，关联的字段是订单里的cId字段
     *
     */
    @OneToMany
    @JoinColumn(name = "cId")
    private List<MyOrder> myOrders;
    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

}
