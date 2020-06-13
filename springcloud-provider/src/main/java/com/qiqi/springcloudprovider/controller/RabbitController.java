package com.qiqi.springcloudprovider.controller;

import com.qiqi.springcloudprovider.repository.domain.User;
import com.qiqi.springcloudprovider.service.RabbitProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author qiqi
 * @date 2020-06-09
 */

@RestController
@RequestMapping("/rabbit")
public class RabbitController {

    @Autowired
    private RabbitProducerService rabbitProducerService;

    @PostMapping("/sendTopicMessage")
    public String sendTopicMessage(@RequestBody User user) {
        return rabbitProducerService.sendTopicMessage(user) ? "success" : "failed";
    }
}
