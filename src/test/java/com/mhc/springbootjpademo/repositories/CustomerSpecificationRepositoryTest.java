package com.mhc.springbootjpademo.repositories;

import com.mhc.springbootjpademo.entity.Customer;
import com.mhc.springbootjpademo.util.SpecificationFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @description:
 * @author: mahongcheng
 * @createDate: 2019/10/11
 * @version: 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerSpecificationRepositoryTest {
    private static final Logger log = LoggerFactory.getLogger(CustomerSpecificationRepositoryTest.class);

    @Autowired
    private CustomerSpecificationRepository csr;


    // 单一条件查询
    @Test
    public void specificationQuery(){
        Specification<Customer> spec = SpecificationFactory.containsLike("lastName", "bau");
        Pageable pageable = new PageRequest(0,5, Sort.Direction.DESC,"id");
        Page<Customer> page = csr.findAll(spec, pageable);
        log.info("[page] - [{}]",page);
        log.info("查询的总个数"+page.getTotalElements());
        log.info("查询的总页数"+page.getTotalPages());
        for (Customer c:page.getContent()){
            log.info("[查询的分页列表数据] - [{}]",c.toString());
        }
    }


    //符合条件查询
    @Test
    public void specificationQuery2(){
        Specifications<Customer> spec2 = Specifications
                .where(SpecificationFactory.containsLike("firstName", "bau"))
                .or(SpecificationFactory.containsLike("lastName", "bau"));
        Pageable pageable = new PageRequest(0,5, Sort.Direction.DESC,"id");
        Page<Customer> page = csr.findAll(spec2, pageable);
        log.info("[page] - [{}]",page);
        log.info("查询的总个数"+page.getTotalElements());
        log.info("查询的总页数"+page.getTotalPages());
        for (Customer customer:page.getContent()){
            log.info("[查询的分页列表数据] - [{}]",customer.toString());
        }
    }

}