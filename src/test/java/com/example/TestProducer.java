package com.example;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

import java.util.UUID;

@KafkaClient
public interface TestProducer {

    @Topic("test-topic")
    void send(@KafkaKey UUID key, TestEvent event);
}
