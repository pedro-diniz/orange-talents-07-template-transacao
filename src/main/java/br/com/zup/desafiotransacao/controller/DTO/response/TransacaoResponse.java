package br.com.zup.desafiotransacao.controller.DTO.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoResponse {

    private String id;
    private BigDecimal valor;
    private Estabelecimento estabelecimento;
    private Cartao cartao;
    private LocalDateTime efetivadaEm;

    private class Estabelecimento {
        private String nome;
        private String cidade;
        private String endereco;

        public Estabelecimento() {
        }

        public Estabelecimento(String nome, String cidade, String endereco) {
            this.nome = nome;
            this.cidade = cidade;
            this.endereco = endereco;
        }

        public String getNome() {
            return nome;
        }

        public String getCidade() {
            return cidade;
        }

        public String getEndereco() {
            return endereco;
        }

        @Override
        public String toString() {
            return "Estabelecimento{" +
                    "nome='" + nome + '\'' +
                    ", cidade='" + cidade + '\'' +
                    ", endereco='" + endereco + '\'' +
                    '}';
        }
    }

    private class Cartao {
        private String id;
        private String email;

        public Cartao() {
        }

        public Cartao(String id, String email) {
            this.id = id;
            this.email = email;
        }

        public String getId() {
            return id;
        }

        public String getEmail() {
            return email;
        }

        @Override
        public String toString() {
            return "Cartao{" +
                    "id='" + id + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }

    public TransacaoResponse(String id, BigDecimal valor, Estabelecimento estabelecimento,
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

    public TransacaoResponse() {
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
}
