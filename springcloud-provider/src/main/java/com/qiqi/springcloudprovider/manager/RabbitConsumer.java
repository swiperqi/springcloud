package com.qiqi.springcloudprovider.manager;


import com.qiqi.springcloudprovider.configuration.RabbitConfig;
import com.qiqi.springcloudprovider.repository.domain.User;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author qiqi
 * @date 2020-06-09
 *
 * 消费者：可以使用 @RabbitListener 和 @RabbitHandler 在方法头部指定消费者， @RabbitListener中可以直接指定 queues 或者
 * 使用 bindings 绑定要消费的队列
 *
 * 或者实现 {@link ChannelAwareMessageListener} 接口，如 {@link MyConsumerListener}，
 * 然后定义一个 {@link SimpleMessageListenerContainer} Bean，如：{@link RabbitConfig#testMessageListenerContainer}，
 * 在Bean中指定消费队列、消费者数量等
 */
@Component
public class RabbitConsumer {

    private ExecutorService executorService = new ThreadPoolExecutor(
            20,
            20,
            0L,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(2000));

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitConfig.TEST_QUEUE),
            exchange = @Exchange(value = RabbitConfig.TEST_TOPIC_EXCHANGE, type = ExchangeTypes.TOPIC),
            key = "test.*"))
    @RabbitHandler
    public void consumeTopicMessage(@Payload User user,
                                    Channel channel,
                                    @Headers Map<String, Object> headers) {
        executorService.execute(() -> {
            Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

            try {
                channel.basicAck(deliveryTag, true);
                System.out.println("receive user: " + user);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
