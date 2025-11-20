package com.br.devborges.sistema_locacao.mapper;
import com.br.devborges.sistema_locacao.dto.ClienteDTO;
import com.br.devborges.sistema_locacao.entity.ClienteModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteDTO toDto(ClienteModel clienteModel);

    ClienteModel toModel(ClienteDTO clienteDTO);
}
