package com.br.devborges.sistema_locacao.repository;
import com.br.devborges.sistema_locacao.entity.EquipamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipamentoRepository extends JpaRepository<EquipamentoModel, Long> {
}
