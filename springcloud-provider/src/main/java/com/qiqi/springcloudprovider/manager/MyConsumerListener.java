package com.qiqi.springcloudprovider.manager;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author qiqi
 * @date 2020-06-13
 */
@Component
public class MyConsumerListener implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        String user = new String(message.getBody());

        System.out.println(LocalDateTime.now() + ": " + user);

        channel.basicAck(deliveryTag, true);
    }
}
