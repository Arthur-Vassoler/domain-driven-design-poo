package br.edu.atitus.domaindrivendesignpoo.Controlers;

import br.edu.atitus.domaindrivendesignpoo.Dtos.ProdutoDto;
import br.edu.atitus.domaindrivendesignpoo.Models.ProdutoModel;
import br.edu.atitus.domaindrivendesignpoo.Services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/produto")
public class ProdutoController {
    final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllProdutos() {
        return new ResponseEntity<>(produtoService.getAllProdutos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProdutoById(@PathVariable("id") UUID id) {
        ProdutoModel produto = produtoService.getProdutoById(id);

        if (produto == null) {
            return new ResponseEntity<>("Produto not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> saveProduto(@RequestBody @Valid ProdutoDto produtoDto) {
        if(produtoService.existsByNome(produtoDto.getNome())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Produto is already in use!");
        }

        var produtoModel = new ProdutoModel();

        BeanUtils.copyProperties(produtoDto, produtoModel);

        return new ResponseEntity<>(produtoService.save(produtoModel), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduto(@PathVariable("id") UUID id, @RequestBody ProdutoModel produto) {
        ProdutoModel currentProduto = produtoService.getProdutoById(id);

        if (currentProduto == null) {
            return new ResponseEntity<>("produto not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(produtoService.updateProduto(id, produto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduto(@PathVariable("id") UUID id) {
        ProdutoModel produto = produtoService.getProdutoById(id);

        if (produto == null) {
            return new ResponseEntity<>("Produto not found", HttpStatus.NOT_FOUND);
        }

        produtoService.deleteProduto(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
