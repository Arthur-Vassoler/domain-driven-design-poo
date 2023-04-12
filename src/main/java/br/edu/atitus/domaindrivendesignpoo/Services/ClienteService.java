package br.edu.atitus.domaindrivendesignpoo.Services;

import br.edu.atitus.domaindrivendesignpoo.Models.ClienteModel;
import br.edu.atitus.domaindrivendesignpoo.Repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {
    final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteModel> getAllClientes() {
        return clienteRepository.findAll();
    }

    public ClienteModel getClienteById(UUID id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Transactional
    public Object save(ClienteModel clienteModel) {
        return clienteRepository.save(clienteModel);
    }

    @Transactional
    public Object updateCliente(UUID id, ClienteModel cliente) {
        ClienteModel currentCliente = getClienteById(id);

        if (currentCliente != null) {
            currentCliente.setNome(cliente.getNome());
            currentCliente.setEmail(cliente.getEmail());

            return clienteRepository.save(currentCliente);
        }

        return cliente;
    }

    @Transactional
    public void deleteCliente(UUID id) {
        clienteRepository.deleteById(id);
    }

    public boolean existsByNome(String name) {
        return clienteRepository.existsByNome(name);
    }

    public boolean existsByEmail(String email) {
        return clienteRepository.existsByEmail(email);
    }
}
