package com.mhc.springbootjpademo.repositories;

import com.mhc.springbootjpademo.entity.Customer;
import com.mhc.springbootjpademo.entity.MyOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static javafx.scene.input.KeyCode.L;


/**
 * @description:
 * @author: mahongcheng
 * @createDate: 2019/10/12
 * @version: 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MyOrderRepositoryTest {

    private static final Logger log = LoggerFactory.getLogger(MyOrderRepositoryTest.class);


    @Autowired
    private MyOrderRepository myOrderRepository;


    @Test
    public void addMyOrder(){

        /**
         * MyOrder{id=5, code='123455', cId=1, total=55.23, customer=Customer[id=1, firstName='Jack', lastName='Bauer']}
         * MyOrder{id=4, code='123459', cId=1, total=9.99, customer=Customer[id=1, firstName='Jack', lastName='Bauer']}
         * MyOrder{id=3, code='123458', cId=1, total=11.90, customer=Customer[id=1, firstName='Jack', lastName='Bauer']}
         * MyOrder{id=2, code='123457', cId=1, total=20.90, customer=Customer[id=1, firstName='Jack', lastName='Bauer']}
         */
        MyOrder save = myOrderRepository.save(new MyOrder("123455", 1L, BigDecimal.valueOf(55.23)));
        log.info("[新增一条order订单记录 ]- [{}]",save);
    }

}