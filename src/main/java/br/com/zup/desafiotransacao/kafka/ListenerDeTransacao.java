package br.com.zup.desafiotransacao.kafka;

import br.com.zup.desafiotransacao.model.Transacao;
import br.com.zup.desafiotransacao.repository.TransacaoRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ListenerDeTransacao {

    private TransacaoRepository transacaoRepository;

    public ListenerDeTransacao(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    public void ouvir(ConsumerRecord<String, Transacao> record) {

        Transacao transacao = (Transacao) record.value();
        transacaoRepository.save(transacao);

        System.out.println(record.value().toString());
    }

}
