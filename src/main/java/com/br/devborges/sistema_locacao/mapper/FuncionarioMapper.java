package com.br.devborges.sistema_locacao.mapper;
import com.br.devborges.sistema_locacao.dto.FuncionarioDTO;
import com.br.devborges.sistema_locacao.entity.FuncionarioModel;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface FuncionarioMapper {

    FuncionarioDTO toDto (FuncionarioModel funcionarioModel);

    FuncionarioModel toModel (FuncionarioDTO funcionarioDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateModelFromDto(FuncionarioDTO dto, @MappingTarget FuncionarioModel model);

}
