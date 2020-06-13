package com.qiqi.springcloudprovider.configuration;

import com.qiqi.springcloudprovider.manager.MyConsumerListener;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author qiqi
 * @date 2020-06-09
 */

@Configuration
public class RabbitConfig {

    public static final String TEST_QUEUE = "test_queue";

    public static final String TEST_TOPIC_EXCHANGE = "test_topic_exchange";

    public static final String TEST_ROUTING_KEY = "test.*";

    @Bean
    public Queue testQueue() {
        return new Queue(TEST_QUEUE, true);
    }

    @Bean
    public Exchange testExchange() {
        return ExchangeBuilder.topicExchange(TEST_TOPIC_EXCHANGE).build();
    }

    @Bean
    public Binding testBinding() {
        return BindingBuilder.bind(testQueue()).to(testExchange()).with(TEST_ROUTING_KEY).noargs();
    }

    @Bean
    public SimpleMessageListenerContainer testMessageListenerContainer(ConnectionFactory connectionFactory, MyConsumerListener listener) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        // 指定消费队列
        container.setQueueNames(TEST_QUEUE);
        // 指定消费者listener
        container.setMessageListener(listener);
        // 消息确认方式为手动确认
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        // 消费者最小数量
        container.setConcurrentConsumers(10);
        // 消费者最大数量
        container.setMaxConcurrentConsumers(10);
        // 每个消费者一次性消费的消息数
        container.setPrefetchCount(1);
        return container;
    }

}
