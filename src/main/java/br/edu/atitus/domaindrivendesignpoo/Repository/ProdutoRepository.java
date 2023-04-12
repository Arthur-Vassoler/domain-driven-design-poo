package br.edu.atitus.domaindrivendesignpoo.Repository;

import br.edu.atitus.domaindrivendesignpoo.Models.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, UUID> {
    boolean existsByNome(String name);
}
