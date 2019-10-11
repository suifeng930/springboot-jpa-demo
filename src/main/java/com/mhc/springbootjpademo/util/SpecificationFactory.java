package com.mhc.springbootjpademo.util;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;

/**
 * @description:
 * @author: mahongcheng
 * @createDate: 2019/10/11
 * @version: 1.0
 *
 * 这个工具类定义了一些基本的查询，包括模糊匹配（containsLike）,
 * 数值区间（isBetween）以及枚举类参数匹配（enumMatcher），
 * 这些也是在查询中常用的方法，在查询的时候，通过Specifications进行调用组织参数，即可获得接口Specification的实例，然后用于查询。
 */
public final class SpecificationFactory {
    /**
     * 模糊匹配（containsLike）
     * @param attribute
     * @param value
     * @return
     */
    public static Specification containsLike(String attribute, String value) {
        return (root, query, cb) -> cb.like(root.get(attribute), "%" + value + "%");
    }

    /**
     * 数值区间（isBetween）
     * @param attribute
     * @param min
     * @param max
     * @return
     */
    public static Specification isBetween(String attribute, int min, int max) {
        return (root, query, cb) -> cb.between(root.get(attribute), min, max);
    }
    /**
     * 数值区间（isBetween）
     * @param attribute
     * @param min
     * @param max
     * @return
     */
    public static Specification isBetween(String attribute, double min, double max) {
        return (root, query, cb) -> cb.between(root.get(attribute), min, max);
    }

    /**
     * 枚举类参数匹配（enumMatcher）
     * @param attribute
     * @param queriedValue
     * @param <T>
     * @return
     */
    public static<T extends Enum<T>>  Specification enumMatcher(String attribute, T queriedValue) {
        return (root, query, cb) -> {
            Path<T> actualValue = root.get(attribute);
            if (queriedValue == null) {
                return null;
            }
            return cb.equal(actualValue, queriedValue);
        };
    }
}
