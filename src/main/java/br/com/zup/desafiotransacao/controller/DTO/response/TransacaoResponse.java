package br.com.zup.desafiotransacao.controller.DTO.response;

import br.com.zup.desafiotransacao.model.Estabelecimento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoResponse {

    private BigDecimal valor;
    private Estabelecimento estabelecimento;
    private LocalDateTime efetivadaEm;

    public TransacaoResponse(BigDecimal valor, Estabelecimento estabelecimento, LocalDateTime efetivadaEm) {
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.efetivadaEm = efetivadaEm;
    }

    public TransacaoResponse() {
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }
}
