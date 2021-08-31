package br.com.zup.desafiotransacao.kafka;

import br.com.zup.desafiotransacao.controller.DTO.response.TransacaoResponse;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ListenerDeTransacao {

    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    public void ouvir(ConsumerRecord<String, TransacaoResponse> record) {
        System.out.println(record.value().toString());
    }

}
