package com.mhc.springbootjpademo.repositories;

import com.mhc.springbootjpademo.entity.Customer;
import com.mhc.springbootjpademo.entity.CustomerProjection;
import javafx.print.Collation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;

import javax.persistence.QueryHint;
import javax.swing.*;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

import static org.hibernate.jpa.QueryHints.HINT_COMMENT;

/**
 * @description:
 * @author: mahongcheng
 * @createDate: 2019/10/11
 * @version: 1.0
 */
public interface CustomerRepository extends JpaRepository<Customer,Long> {


    /**
     * 功能描述: 通过第一个名字查询<br>
     * @Param: [firstName] 
     * @Return: java.util.List<com.mhc.springbootjpademo.entity.Customer>
     * @Author: mahongcheng
     * @Date: 2019/10/11 11:01
     */
    List<Customer> findAllByFirstName(String firstName);

    /**
     * 功能描述: 根据lastName查询结果<br>
     * @Param: [lastName]
     * @Return: java.util.List<com.mhc.springbootjpademo.entity.Customer>
     * @Author: mahongcheng
     * @Date: 2019/10/11 14:01
     */
    List<Customer> findByLastName(String lastName);


    /**
     * 新增预定义查询
     * @param firstName
     * @return
     */
    Customer findByFirstName(String firstName);



    // 使用@Query注解，使用注解有两种方式，一种是JPQL的SQL语言方式，一种是原生SQL的语言，略有区别，后者我们更熟悉一些。话不多说，看代码。

    /**
     * 模糊匹配
     * @param bauer
     * @return
     *
     * ？加数字表示占位符，？1代表在方法参数里的第一个参数，区别于其他的index，这里从1开始
     */
    @Query("select c from Customer c where c.firstName=?1")
    Customer findByFirstName2(String bauer);


    /**
     *
     * @param lastName
     * @return
     * =:加上变量名，这里是与方法参数中有@Param的值匹配的，而不是与实际参数匹配的
     */
    @Query("select c from Customer  c where c.lastName=?1 order by c.id desc")
    List<Customer> findByLastName2(String lastName);


    /**
     * 一个参数，匹配两个字段
     * @param name2
     * @return
     * 这里Param的值和=:后面的参数匹配，但不需要和方法名对应的参数值对应
     */
    @Query("select c from Customer  c where c.firstName=:name or c.lastName=:name order by c.id desc")
    List<Customer> findByName(@Param("name") String name2);


    /**
     * 一个参数 匹配两个字段
     * @param name2
     * @return
     * 这里的 % 只能放在占位符的前面 ，后面不行
     */
    @Query("select c from Customer  c where  c.firstName like %?1")
    List<Customer> findByName2(@Param("name") String name2);

    /**
     * 开启nativeQuery=true 在value里可以用原生sql语句完成查询
     * @param name3
     * @return
     */
    @Query(nativeQuery = true,value = "select * from Customer c where c.first_name like concat('%',?1,'%')")
    List<Customer> findByName3(@Param("name") String name3);



    //Spring Data JPA系列：使用Sort进行排序（Using Sort）

    /**
     * 一个参数 匹配两个字段
     * @param name2
     * @param sort
     * @return
     * 这里param的值和=： 后面的参数匹配，但不需要和方法名对应的参数值对应
     */
    @Query("select c from Customer  c where  c.firstName=:name or c.lastName=:name")
    List<Customer> findByName4(@Param("name") String name2, Sort sort);


    //Spring Data JPA系列：使用@Modifying修改（Modifying queries）


    /**
     *  @Modifying//更新查询
     *  @Transactional//开启事务
     * @param firstName
     * @param lastName
     * @return
     * 这里需要注意，在使用@Modifying注解的时候，一定要加上事务注解@Transactional，如果你忘了或者加错了，那很可能报如下错误
     */
    @Modifying
    @Transactional
    @Query("update Customer c set c.firstName= ?1 where c.lastName=?2")
    int setFixedFirstnameFor(String firstName, String lastName);


    //Spring Data JPA系列：应用查询提示（Applying query hints）

    /**
     * 一个参数，匹配两个字段
     * @param name2
     * @Param pageable 分页参数
     * @return
     * 这里Param的值和=:后面的参数匹配，但不需要和方法名对应的参数值对应
     * 这里增加了@QueryHints注解，是给查询添加一些额外的提示
     * 比如当前的name值为HINT_COMMENT是在查询的时候带上一些备注信息
     */
    @QueryHints(value = {@QueryHint(name=HINT_COMMENT,value = "a query for pageable")})
    @Query("select c from Customer c where c.firstName=:name or c.lastName=:name")
    Page<Customer> findByName6(@Param("name") String name2, Pageable pageable);


//    Spring Data JPA系列：投影（Projection）的用法

    @Query("select  c.firstName as firstName,c.lastName as lastName from Customer  c")
    Collection<CustomerProjection> findAllProjectedBy();









}
