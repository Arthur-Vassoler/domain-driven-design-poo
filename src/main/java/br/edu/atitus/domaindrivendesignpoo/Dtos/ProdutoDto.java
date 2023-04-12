package br.edu.atitus.domaindrivendesignpoo.Dtos;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public class ProdutoDto {
    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    private BigDecimal preco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
