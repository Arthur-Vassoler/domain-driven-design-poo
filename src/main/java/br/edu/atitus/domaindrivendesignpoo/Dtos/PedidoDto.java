package br.edu.atitus.domaindrivendesignpoo.Dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public class PedidoDto {
    @NotNull
    private UUID idCliente;

    @NotNull
    private LocalDateTime dataPedido;

    private String itensPedido;

    // getters e setters

    public String getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(String itensPedido) {
        this.itensPedido = itensPedido;
    }

    public UUID getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(UUID idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }
}
