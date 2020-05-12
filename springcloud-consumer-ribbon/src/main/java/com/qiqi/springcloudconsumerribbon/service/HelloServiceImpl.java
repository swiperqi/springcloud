package com.qiqi.springcloudconsumerribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author qiqi
 * @date 2020-05-12
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String hello(String name) {
        // 直接使用restTemplate调用其他服务
        return restTemplate.getForObject("http://springcloud-provider/provider/hello/" + name, String.class);
    }

    @Override
    public String helloFeign(String name) {
        return restTemplate.getForObject("http://springcloud-consumer/consumer/hello/" + name, String.class);
    }


}
