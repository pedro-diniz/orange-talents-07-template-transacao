package br.com.zup.desafiotransacao.controller.DTO.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class TransacaoRequest {

    @NotBlank
    private String id;

    @Email @NotBlank
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
