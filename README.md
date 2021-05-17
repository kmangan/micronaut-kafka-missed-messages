# micronaut-kafka-missed-messages

### An example of micronaut kafka skipping remaining polled messages on error.

When an exception is thrown from a @KafkaListener, the remaining messages
in the poll loop are skipped. 

This project is built from an example created with [Micronaut Launch](https://micronaut.io/launch).

To run:

- `./gradlew clean build` 

You'll notice that, because of the skipped messages, [MissedMessageTest](src/test/java/com/example/MissedMessageTest.java) fails.