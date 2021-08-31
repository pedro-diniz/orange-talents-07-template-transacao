package br.com.zup.desafiotransacao.controller;

import br.com.zup.desafiotransacao.controller.DTO.request.TransacaoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RestController
public class KafkaController {

    @GetMapping("/iniciarTransacoes")
    private ResponseEntity<?> inicializar() {

        try {
            RestTemplate restTemplate = new RestTemplate();
            TransacaoRequest request = restTemplate.postForObject(
                    "http://localhost:7777/api/cartoes",
                    new TransacaoRequest(UUID.randomUUID().toString(), "zupper@zup.com.br"),
                    TransacaoRequest.class
            );

            return ResponseEntity.ok(request);
        }
        catch (Exception e) {
            return ResponseEntity.status(503).body("Serviço de transações encontra-se indisponível. " +
                    "Tente novamente mais tarde.");
        }

    }

    @GetMapping("/pararTransacoes/{idCartao}")
    private void parar(@PathVariable String idCartao) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            restTemplate.delete("http://localhost:7777/api/cartoes/"+idCartao);

        }
        catch (Exception e) {
            throw new RuntimeException("Erro ao tentar parar transações");
        }
    }

}
