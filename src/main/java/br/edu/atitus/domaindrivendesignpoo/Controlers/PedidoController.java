package br.edu.atitus.domaindrivendesignpoo.Controlers;

import br.edu.atitus.domaindrivendesignpoo.Dtos.PedidoDto;
import br.edu.atitus.domaindrivendesignpoo.Models.PedidoModel;
import br.edu.atitus.domaindrivendesignpoo.Services.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/pedido")
public class PedidoController {
    final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllPedidos() {
        return new ResponseEntity<>(pedidoService.getAllPedidos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPedidoById(@PathVariable("id") UUID id) {
        PedidoModel pedido = pedidoService.getPedidoById(id);

        if (pedido == null) {
            return new ResponseEntity<>("Pedido not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(pedido, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> savePedido(@RequestBody @Valid PedidoDto pedidoDto) {
        var pedidoModel = new PedidoModel();

        BeanUtils.copyProperties(pedidoDto, pedidoModel);

        return new ResponseEntity<>(pedidoService.save(pedidoModel), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePedido(@PathVariable("id") UUID id, @RequestBody PedidoModel pedido) {
        PedidoModel currentPedido = pedidoService.getPedidoById(id);

        if (currentPedido == null) {
            return new ResponseEntity<>("pedido not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(pedidoService.updatePedido(id, pedido), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePedido(@PathVariable("id") UUID id) {
        PedidoModel pedido = pedidoService.getPedidoById(id);

        if (pedido == null) {
            return new ResponseEntity<>("Pedido not found", HttpStatus.NOT_FOUND);
        }

        pedidoService.deletePedido(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
