package br.com.zup.desafiotransacao.model;

import br.com.zup.desafiotransacao.controller.DTO.response.TransacaoResponse;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transacao {

    @Id
    private String id;
    private BigDecimal valor;

    @Embedded
    private Estabelecimento estabelecimento;

    @Embedded
    @AttributeOverride(name="id", column=@Column(name="idCartao"))
    private Cartao cartao;

    private LocalDateTime efetivadaEm;

    public Transacao(String id, BigDecimal valor, Estabelecimento estabelecimento,
                     Cartao cartao, LocalDateTime efetivadaEm) {
        this.id = id;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.cartao = cartao;
        this.efetivadaEm = efetivadaEm;

    }

    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }

    public Transacao() {
    }

    @Override
    public String toString() {
        return "TransacaoResponse{" +
                "id='" + id + '\'' +
                ", valor=" + valor +
                ", estabelecimento=" + estabelecimento +
                ", cartao=" + cartao +
                ", efetivadaEm=" + efetivadaEm +
                '}';
    }

    public TransacaoResponse toResponse() {
        return new TransacaoResponse(valor, estabelecimento, efetivadaEm);
    }
}
