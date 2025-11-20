package com.br.devborges.sistema_locacao.service;
import com.br.devborges.sistema_locacao.dto.EquipamentoDTO;
import com.br.devborges.sistema_locacao.entity.EquipamentoModel;
import com.br.devborges.sistema_locacao.mapper.EquipamentoMapper;
import com.br.devborges.sistema_locacao.repository.EquipamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipamentoService {

    private final EquipamentoMapper equipamentoMapper;
    private final EquipamentoRepository equipamentoRepository;

    public EquipamentoService(EquipamentoMapper equipamentoMapper, EquipamentoRepository equipamentoRepository) {
        this.equipamentoMapper = equipamentoMapper;
        this.equipamentoRepository = equipamentoRepository;
    }

    public EquipamentoDTO adicionarEquipamento(EquipamentoDTO novoEquipamento){
        EquipamentoModel equipamento = equipamentoMapper.toModel(novoEquipamento);
        return equipamentoMapper.toDto(equipamentoRepository.save(equipamento));
    }

    public List<EquipamentoDTO> listarEquipamentos(){
        List<EquipamentoModel> lista = equipamentoRepository.findAll();
        return lista.stream()
                .map(equipamentoMapper::toDto)
                .toList();
    }

    public EquipamentoDTO listarPorId(Long id){
        return equipamentoRepository.findById(id)
                .map(equipamentoMapper::toDto)
                .orElseThrow(()-> new EntityNotFoundException("Equipamento com ID " + id + " não encontrado."));
    }

    public EquipamentoDTO atualizarEquipamento (Long id, EquipamentoDTO equipamentoDTO){
        EquipamentoModel equipamentoEncontrado = equipamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipamento com ID " + id + " não encontrado."));

        EquipamentoModel equipamentoAtualizado = equipamentoMapper.toModel(equipamentoDTO);
        equipamentoAtualizado.setId(id);
        return equipamentoMapper.toDto(equipamentoRepository.save(equipamentoAtualizado));
    }

    public void deletarEquipameno(Long id){
        if(!equipamentoRepository.existsById(id)){
            throw new EntityNotFoundException("Equipamento com ID " + id + " não encontrado.");
        }
        equipamentoRepository.deleteById(id);
    }



}
