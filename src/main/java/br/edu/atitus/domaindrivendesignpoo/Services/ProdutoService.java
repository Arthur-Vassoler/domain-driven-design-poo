package br.edu.atitus.domaindrivendesignpoo.Services;

import br.edu.atitus.domaindrivendesignpoo.Models.ProdutoModel;
import br.edu.atitus.domaindrivendesignpoo.Repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutoService {
    final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoModel> getAllProdutos() {
        return produtoRepository.findAll();
    }

    public ProdutoModel getProdutoById(UUID id) {
        return produtoRepository.findById(id).orElse(null);
    }

    @Transactional
    public Object save(ProdutoModel produtoModel) {
        return produtoRepository.save(produtoModel);
    }

    @Transactional
    public Object updateProduto(UUID id, ProdutoModel produto) {
        ProdutoModel currentProduto = getProdutoById(id);

        if (currentProduto != null) {
            currentProduto.setNome(produto.getNome());
            currentProduto.setDescricao(produto.getDescricao());
            currentProduto.setPreco(produto.getPreco());

            return produtoRepository.save(currentProduto);
        }

        return produto;
    }

    @Transactional
    public void deleteProduto(UUID id) {
        produtoRepository.deleteById(id);
    }

    public boolean existsByNome(String name) {
        return produtoRepository.existsByNome(name);
    }
}
