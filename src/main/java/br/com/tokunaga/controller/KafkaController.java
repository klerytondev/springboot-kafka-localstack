package br.com.tokunaga.controller;

import br.com.tokunaga.kafka.Producer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class KafkaController {

    @Value("${spring.kafka.producer.topic1.name}")
    private String topic;
    private final Producer topic1Producer;

    @GetMapping(value = "/kafka")
    public String enviaEvento() {
        String uuid = UUID.randomUUID().toString();

        log.info("Evento disparado pela controller ao tópico1");

        topic1Producer.send(topic, "Mensagem de teste enviada ao tópico: " + uuid);

        return "Mensagem de teste enviada ao tópico: " + uuid;

    }
}