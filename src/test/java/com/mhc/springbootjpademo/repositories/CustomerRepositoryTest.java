package com.mhc.springbootjpademo.repositories;

import com.mhc.springbootjpademo.entity.Customer;
import com.mhc.springbootjpademo.entity.CustomerProjection;
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
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


/**
 * @description:
 * @author: mahongcheng
 * @createDate: 2019/10/11
 * @version: 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerRepositoryTest {

    private static final Logger log = LoggerFactory.getLogger(CustomerRepositoryTest.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void test1() throws Exception {

        final Customer customer=customerRepository.save(new Customer("xiaoma","小马"));
        log.info("[添加成功] - [{}]", customer);

        final List<Customer> u1 = customerRepository.findAllByFirstName("xiaoma");
        log.info("[条件查询] - [{}]", u1);
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.desc("firstName")));
        final Page<Customer> users = customerRepository.findAll(pageable);
        log.info("[分页+排序+查询所有] - [{}]", users.getContent());

    }


    @Test
    public void saveEntity(){

        customerRepository.save(new Customer("Jack","Bauer"));
        customerRepository.save(new Customer("Chloe", "O'Brian"));
        customerRepository.save(new Customer("Kim", "Bauer"));
        customerRepository.save(new Customer("David", "Palmer"));
        customerRepository.save(new Customer("Michelle", "Dessler"));
        customerRepository.save(new Customer("Bauer", "Dessler"));
    }


    @Test
    public void findAllCustomer(){
        List<Customer> resultList=customerRepository.findAll();
        for (Customer customer : resultList) {
            log.info("[查询列表] -[{}]",customer);
            log.info("=====================");
        }
    }

    @Test
    public void findOneById(){

        Optional<Customer> result = customerRepository.findById(1L);
        if (result!=null){
            log.info("[查询到的数据] - [{}] ",result);
        }
    }

    @Test
    public void delete(){
        log.info("删除前的数据为：-----");
        List<Customer> customerList = customerRepository.findAll();
        for (Customer customer : customerList) {
            log.info("【删除前的数据列表】 - [{}]",customer);
        }


        log.info("删除指定id为3的数据：");
        customerRepository.deleteById(3l);
        log.info("删除之后的数据列表为：======");
        List<Customer> resultList= customerRepository.findAll();
        for (Customer customer : resultList) {
            log.info("【删除后的数据列表】 - [{}]",customer);
        }
    }

    @Test
    public void findByLastName(){
        List<Customer> customerList = customerRepository.findByLastName("小马");

        for (Customer customer : customerList) {
            log.info("[通过lastName查询：]- [{}]",customer.toString());
        }


    }

    @Test
    public void findByFirstName(){

        Customer byFirstName = customerRepository.findByFirstName("Bauer");
        log.info("[使用预定于测试 查询条件：] - [{}]",byFirstName);
    }



    @Test
    public void findByFirstName2(){

        Customer bauer = customerRepository.findByFirstName2("Bauer");
        log.info("[@Query注解方式查询 ] - [{}] ",bauer.toString());
    }


    /**
     * 查询LastName为指定字符串
     */
    @Test
    public void findByLastName2(){
        List<Customer> customerList = customerRepository.findByLastName2("Bauer");
        for (Customer customer : customerList) {
            log.info("[@Query注解方式查询 查询LastName为指定字符串] - [{}]" ,customer.toString());
        }
    }

    @Test
    public void findByName(){
        List<Customer> customerList = customerRepository.findByName("Bauer");
        for (Customer customer:customerList){
            log.info("[用@Param指定参数，匹配firstName和lastName] - [{}]",customer);
        }
    }


    @Test
    public void findByName2(){
        List<Customer> customerList = customerRepository.findByName2("e");
        for (Customer customer:customerList){
            log.info("[用@Param指定参数，firstName的结尾为e的字符串 ]- [{}]",customer);
        }
    }

    @Test
    public void findByName3(){
        List<Customer> customerList = customerRepository.findByName3("e");
        for (Customer customer:customerList){
            log.info("[@Query注解方式查询，模糊匹配关键字e ] - [{}]",customer);
        }
        System.out.println("-------------------------------------------");
    }



    // 测试Sort 排序  方式

    /**
     * 1）直接创建Sort对象，适合对单一属性做排序
     * 2）通过Sort.Order对象创建Sort对象，适合对单一属性做排序
     * 3）通过属性的List集合创建Sort对象，适合对多个属性，采取同一种排序方式的排序
     * 4）通过Sort.Order对象的List集合创建Sort对象，适合所有情况，比较容易设置排序方式
     */
    @Test
    public void findByName4(){
        //按照ID倒序排列
        log.info("直接创建sort对象，通过排序方法和属性名");
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        List<Customer> customerList = customerRepository.findByName4("Bauer", sort);
        for (Customer customer:customerList){
            log.info("[按照ID倒叙排列] - [{}]",customer);
        }
        log.info("-------------------------------------------");
        //按照ID倒序排列
        log.info("通过Sort.Order对象创建sort对象");
        Sort sortx = new Sort(new Sort.Order(Sort.Direction.DESC,"id"));
        List<Customer> customerList1 = customerRepository.findByName4("Bauer", sort);
        for (Customer customer:customerList1){
            log.info("[按照ID倒叙排列 ]- [{}]",customer);
        }
        log.info("-------------------------------------------");

        log.info("通过排序方法和属性List创建sort对象");
        List<String> sortProperties = new ArrayList<>();
        sortProperties.add("id");
        sortProperties.add("firstName");
        Sort sort2 = new Sort(Sort.Direction.DESC,sortProperties);
        List<Customer> customerList2 = customerRepository.findByName4("Bauer", sort2);
        for (Customer customer:customerList2){
            log.info("[通过排序方法和属性List创建sort对象] -[{}]" ,customer);
        }
        log.info("-------------------------------------------");

        log.info("通过创建Sort.Order对象的集合创建sort对象");
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"id"));
        orders.add(new Sort.Order(Sort.Direction.ASC,"firstName"));
        List<Customer> customerList3 = customerRepository.findByName4("Bauer", new Sort(orders));
        for (Customer customer:customerList3){
            log.info("[通过创建Sort.Order对象的集合创建sort对象] -[{}]",customer);
        }
        log.info("-------------------------------------------");
    }


    @Test
    public void modifying(){
        Integer result = customerRepository.setFixedFirstnameFor("Bauorx", "Bauer");
        if(result!=null){
            log.info("modifying result:"+result);
        }
        log.info("-------------------------------------------");

    }


    @Test
    public void pageable(){
        //Pageable是接口，PageRequest是接口实现
        //PageRequest的对象构造函数有多个，page是页数，初始值是0，size是查询结果的条数，后两个参数参考Sort对象的构造方法
        Pageable pageable = new PageRequest(0,3, Sort.Direction.DESC,"id");
        Page<Customer> page = customerRepository.findByName6("bauer", pageable);
        //查询结果总行数
        log.info("查询结果总行数"+page.getTotalElements());
        //按照当前分页大小，总页数
        log.info("按照当前分页大小，总页数:" +page.getTotalPages());
        //按照当前页数、分页大小，查出的分页结果集合
        for (Customer customer: page.getContent()) {
            log.info("[分页查询集合列表] - [{}]",customer.toString());
        }
        log.info("-------------------------------------------");
    }


    @Test
    public void findAllProjections(){
        Collection<CustomerProjection> projections = customerRepository.findAllProjectedBy();
        log.info("[projections] -[{}]",projections);
        log.info("[projections.size()] - [{}]",projections.size());
        for (CustomerProjection projection:projections){
            log.info("FullName:"+projection.getFullName());
            log.info("FirstName:"+projection.getFirstName());
            log.info("LastName:"+projection.getLastName());
        }
    }
}