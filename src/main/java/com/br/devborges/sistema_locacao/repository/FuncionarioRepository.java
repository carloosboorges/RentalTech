package com.br.devborges.sistema_locacao.repository;
import com.br.devborges.sistema_locacao.entity.FuncionarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, Long> {

    boolean existsByEmail(String email);

    boolean existsByMatricula(String matricula);

    Optional<FuncionarioModel> findByMatricula(String matricula);


}
