package com.example;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@KafkaListener(
        offsetReset = OffsetReset.EARLIEST
)
public class TestListener {

    boolean receivedMessagesAfterExceptionInPollLoop;

    @Topic("test-topic")
    public void receive(@KafkaKey UUID key, TestEvent event) {
        log.warn("**** RECEIVED: {}", event.getCount());
        if (event.getCount() == 3) {
            throw new IllegalArgumentException("BOOM");
        }
        if (event.getCount() >= 4 && event.getCount() < 10) {
            receivedMessagesAfterExceptionInPollLoop = true;
        }
    }

}


