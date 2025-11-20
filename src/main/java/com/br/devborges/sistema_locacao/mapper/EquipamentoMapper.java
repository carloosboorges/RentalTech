package com.br.devborges.sistema_locacao.mapper;
import com.br.devborges.sistema_locacao.dto.EquipamentoDTO;
import com.br.devborges.sistema_locacao.entity.EquipamentoModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipamentoMapper {


    EquipamentoDTO toDto(EquipamentoModel equipamentoModel);

    EquipamentoModel toModel(EquipamentoDTO equipamentoDTO);
}
