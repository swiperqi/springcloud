package com.qiqi.springcloudprovider.service;

import com.qiqi.springcloudprovider.repository.domain.User;

/**
 * @author qiqi
 * @date 2020-06-09
 */

public interface RabbitProducerService {

    Boolean sendTopicMessage(User user);
}
