package com.example;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.awaitility.Awaitility.await;

@Slf4j
@MicronautTest(environments = "kafka")
public class MissedMessageTest {

    @Inject
    TestListener listener;
    @Inject
    TestProducer producer;

    /**
     * Send 30 messages to the test listener. The listener will throw an exception on message number 3.
     * We will try to assert that we receive one of the six messages that remain in the initial poll loop
     * (the poll size is set to 10).
     * This test fails as messages numbered 4 to 9 are skipped.
     * Logs show that messages from the next poll onwards are processed ok.
     */
    @Test
    void test_shouldPass() {
        IntStream.range(0, 30).forEach(i -> producer.send(UUID.randomUUID(), new TestEvent(i)));

        await().atMost(10, TimeUnit.SECONDS).until(() -> listener.receivedMessagesAfterExceptionInPollLoop);
    }

}
