package br.edu.atitus.domaindrivendesignpoo.Repository;

import br.edu.atitus.domaindrivendesignpoo.Models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, UUID> {
    boolean existsByNome(String nome);
    boolean existsByEmail(String email);
}
