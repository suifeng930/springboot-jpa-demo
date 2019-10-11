package com.mhc.springbootjpademo.entity;


import org.springframework.beans.factory.annotation.Value;

/**
 * @description:
 * @author: mahongcheng
 * @createDate: 2019/10/11
 * @version: 1.0
 *
 * 在JPA的查询中，有一个不方便的地方，@Query注解，如果查询直接是Select C from Customer c,这时候，查询的返回对象就是Customer这个完整的对象
 * ，包含所有字段，对于我们的示例并没有什么问题，但是对于比较庞大的domain类，这个查询时就比较要命，并不是所有的字段都能用到，比较头疼。
 * 另外，如果定义select c.firstName as firstName,c.lastName as lastName from Customer c这个查询结果，
 * 返回的对象是Object类型，而且无法直接转换成Customer对象，这样用起来就不是很方便。
 * 对于这种情况，JPA提供了一种声明方式来解决，即声明一个接口类，然后直接使用这个接口类接受返回的数据即可。
 */
public interface CustomerProjection {

    /**
     * 是可以直接通过get+属性名，这是普通的，另外也可以通过@Value注解来实现指定字段，
     * 除了指定字段也可以做聚合展示，比如有些地方需要展示客户的全名，
     * 这里定义的getFullName()方法及注解@Value即完成这一操作。
     * 需要注意这里的@Value中的target表达式写法及拼接方法。
     * @return
     */

    @Value("#{target.firstName + ' ' + target.lastName}")
    String getFullName();

    String getFirstName();

    String getLastName();
}
