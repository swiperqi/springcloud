package com.qiqi.springcloudprovider.service;

import com.alibaba.fastjson.JSONObject;
import com.qiqi.springcloudprovider.configuration.RabbitConfig;
import com.qiqi.springcloudprovider.repository.domain.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * @author qiqi
 * @date 2020-06-09
 */
@Service
public class RabbitProducerServiceImpl implements RabbitProducerService {

    private ExecutorService executorService = new ThreadPoolExecutor(20,
            20,
            0L,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(200));

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public Boolean sendTopicMessage(User user) {
        executorService.execute(() -> {
            try {
                // 生产者发送消息
                rabbitTemplate.convertAndSend(RabbitConfig.TEST_TOPIC_EXCHANGE, "test.topic", JSONObject.toJSONString(user));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return true;
    }
}
