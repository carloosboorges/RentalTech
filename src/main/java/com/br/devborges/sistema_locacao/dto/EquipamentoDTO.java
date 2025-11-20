package com.br.devborges.sistema_locacao.dto;
import com.br.devborges.sistema_locacao.enums.StatusEquipamento;

public record EquipamentoDTO(
        Long id,
        String nome,
        String categoria,
        String descricao,
        String valorDiaria,
        StatusEquipamento status

) {
}
