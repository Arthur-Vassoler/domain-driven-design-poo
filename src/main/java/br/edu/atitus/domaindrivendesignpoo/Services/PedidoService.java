package br.edu.atitus.domaindrivendesignpoo.Services;

import br.edu.atitus.domaindrivendesignpoo.Models.PedidoModel;
import br.edu.atitus.domaindrivendesignpoo.Repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PedidoService {
    final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<PedidoModel> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    public PedidoModel getPedidoById(UUID id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    @Transactional
    public Object save(PedidoModel pedidoModel) {
        return pedidoRepository.save(pedidoModel);
    }

    @Transactional
    public Object updatePedido(UUID id, PedidoModel pedido) {
        PedidoModel currentPedido = getPedidoById(id);

        if (currentPedido != null) {
            currentPedido.setItensPedido(pedido.getItensPedido());

            return pedidoRepository.save(currentPedido);
        }

        return pedido;
    }

    @Transactional
    public void deletePedido(UUID id) {
        pedidoRepository.deleteById(id);
    }

}
