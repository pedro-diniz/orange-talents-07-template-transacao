package br.com.zup.desafiotransacao.model;

import javax.persistence.Embeddable;

@Embeddable
public class Cartao {

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
}
