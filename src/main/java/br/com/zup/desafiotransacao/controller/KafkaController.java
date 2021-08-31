package br.com.zup.desafiotransacao.controller;

import br.com.zup.desafiotransacao.controller.DTO.request.TransacaoRequest;
import br.com.zup.desafiotransacao.controller.DTO.response.TransacaoResponse;
import br.com.zup.desafiotransacao.model.Transacao;
import br.com.zup.desafiotransacao.repository.TransacaoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
public class KafkaController {

    @PersistenceContext
    private EntityManager entityManager;

    private TransacaoRepository transacaoRepository;

    public KafkaController(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    @PostMapping("/iniciarTransacoes")
    private ResponseEntity<?> inicializar(@RequestBody @Valid TransacaoRequest transacaoRequest) {

        if (cartaoExiste(transacaoRequest.getId())) {

            try {
                RestTemplate restTemplate = new RestTemplate();
                TransacaoRequest request = restTemplate.postForObject(
                        "http://localhost:7777/api/cartoes",
                        transacaoRequest,
                        TransacaoRequest.class
                );

                return ResponseEntity.ok(request);
            } catch (Exception e) {
                return ResponseEntity.status(503).body("Serviço de transações encontra-se indisponível. " +
                        "Tente novamente mais tarde.");
            }
        }
        return ResponseEntity.status(404).body("Cartão não encontrado");
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

    @GetMapping("/cartoes/{idCartao}/transacoes")
    private ResponseEntity<?> listar(@PathVariable String idCartao, @PageableDefault(
            sort = "efetivadaEm", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao) {

        if (cartaoExiste(idCartao)) {

            Page<Transacao> transacoes = transacaoRepository.findByCartao_Id(idCartao, paginacao);
            Page<TransacaoResponse> transacoesResponse = new PageImpl<>(transacoes
                    .stream().map(Transacao::toResponse).collect(Collectors.toList()));

            return ResponseEntity.ok(transacoesResponse);

        }

        return ResponseEntity.status(404).body("Cartão não encontrado");
    }

    private boolean cartaoExiste(String idCartao) {

        Query query = entityManager.createNativeQuery(
                "SELECT EXISTS(SELECT 1 FROM cartao WHERE id = :idCartao)");
        query.setParameter("idCartao", idCartao);

        return (Boolean) query.getSingleResult();
    }

}
