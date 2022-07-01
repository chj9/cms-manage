package com.chj9.cms.common.util;

/**
 * 只提供执行体，不需要参数也没有返回值
 *
 * @author chj
 * @date 2022/7/1
 **/
@FunctionalInterface
public interface VoidConsumer {


    /**
     * 执行体
     */
    void consume();
}