package br.com.tokunaga.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Consumer {

    @Value("${spring.kafka.producer.topic2.name}")
    private String topic;
    private final Producer topic2producer;

    @KafkaListener(topics = "${spring.kafka.consumer.topic1.name}", groupId = "test")
    public void consume(ConsumerRecord<String, String> payload) {
        log.info("Evento consumido com sucesso do topic1");
        topic2producer.send(topic, payload.value());
    }
    @KafkaListener(topics = "${spring.kafka.consumer.topic2.name}", groupId = "test")
    public void consume2(ConsumerRecord<String, String> payload) {
        log.info("Evento consumido com sucesso do topic2");
    }
}