package br.edu.atitus.domaindrivendesignpoo.Controlers;

import br.edu.atitus.domaindrivendesignpoo.Dtos.ClienteDto;
import br.edu.atitus.domaindrivendesignpoo.Models.ClienteModel;
import br.edu.atitus.domaindrivendesignpoo.Services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/cliente")
public class ClienteController {
    final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllClientes() {
        return new ResponseEntity<>(clienteService.getAllClientes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getClienteById(@PathVariable("id") UUID id) {
        ClienteModel cliente = clienteService.getClienteById(id);

        if (cliente == null) {
            return new ResponseEntity<>("Cliente not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> saveCliente(@RequestBody @Valid ClienteDto clienteDto) {
        if(clienteService.existsByNome(clienteDto.getNome())){
            return new ResponseEntity<>("Conflict: Name is already in use!", HttpStatus.CONFLICT);
        }

        if(clienteService.existsByEmail(clienteDto.getEmail())){
            return new ResponseEntity<>("Conflict: Email is already in use!", HttpStatus.CONFLICT);
        }

        var clienteModel = new ClienteModel();

        BeanUtils.copyProperties(clienteDto, clienteModel);

        return new ResponseEntity<>(clienteService.save(clienteModel), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable("id") UUID id, @RequestBody ClienteModel cliente) {
        ClienteModel currentCliente = clienteService.getClienteById(id);

        if (currentCliente == null) {
            return new ResponseEntity<>("cliente not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(clienteService.updateCliente(id, cliente), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable("id") UUID id) {
        ClienteModel cliente = clienteService.getClienteById(id);

        if (cliente == null) {
            return new ResponseEntity<>("Cliente not found", HttpStatus.NOT_FOUND);
        }

        clienteService.deleteCliente(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
