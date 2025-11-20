package com.br.devborges.sistema_locacao.repository;
import com.br.devborges.sistema_locacao.entity.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
}
