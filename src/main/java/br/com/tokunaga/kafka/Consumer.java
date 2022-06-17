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

    private final Producer producer;

    @KafkaListener(topics = "${spring.kafka.consumer.topic1.name}", groupId = "test")
    public void consume(ConsumerRecord<String, String> payload) {
        log.info("Evento recebido");

        producer.send(topic, payload.value());
    }
}