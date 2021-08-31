package br.com.zup.desafiotransacao.controller.DTO.request;

public class TransacaoRequest {

    private String id;
    private String email;

    public TransacaoRequest(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public TransacaoRequest() {
    }
}
