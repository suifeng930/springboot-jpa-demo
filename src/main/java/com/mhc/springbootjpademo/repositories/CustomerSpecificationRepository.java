package com.mhc.springbootjpademo.repositories;

import com.mhc.springbootjpademo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description:
 * @author: mahongcheng
 * @createDate: 2019/10/11
 * @version: 1.0
 *
 * 包含了常用的查询单个对象，
 * 查询数据集合，
 * 查询分页数据集合，
 * 查询带排序参数的数据集合，
 * 查询数据的大小，
 * 这些都是常用的数据结果集，因此可以不用做其他定义即可直接使用。
 */
public interface CustomerSpecificationRepository extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {

}
