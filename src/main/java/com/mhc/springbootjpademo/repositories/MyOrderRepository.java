package com.mhc.springbootjpademo.repositories;

import com.mhc.springbootjpademo.entity.MyOrder;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * @description:
 * @author: mahongcheng
 * @createDate: 2019/10/12
 * @version: 1.0
 *  这里主要是继承JpaSpecificationExecutor接口，进行Specification查询
 */
public interface MyOrderRepository extends JpaSpecificationExecutor<MyOrder>, CrudRepository<MyOrder,Long> {
}
