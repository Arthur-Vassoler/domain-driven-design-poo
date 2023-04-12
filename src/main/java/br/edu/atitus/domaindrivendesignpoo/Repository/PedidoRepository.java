package br.edu.atitus.domaindrivendesignpoo.Repository;

import br.edu.atitus.domaindrivendesignpoo.Models.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, UUID> {
}
