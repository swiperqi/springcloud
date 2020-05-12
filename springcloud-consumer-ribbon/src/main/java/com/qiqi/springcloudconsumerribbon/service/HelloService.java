package com.qiqi.springcloudconsumerribbon.service;

/**
 * @author qiqi
 * @date 2020-05-12
 */

public interface HelloService {
    String hello(String name);

    String helloFeign(String name);
}
