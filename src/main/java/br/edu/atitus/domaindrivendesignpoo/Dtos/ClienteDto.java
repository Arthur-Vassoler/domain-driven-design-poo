package br.edu.atitus.domaindrivendesignpoo.Dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ClienteDto {
    @NotBlank(message = "O nome não pode ser vazio")
    private String nome;

    @NotBlank(message = "O e-mail não pode ser vazio")
    @Email(message = "O e-mail precisa ser válido")
    private String email;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
