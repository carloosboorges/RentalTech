package com.br.devborges.sistema_locacao.service;

import com.br.devborges.sistema_locacao.dto.FuncionarioDTO;
import com.br.devborges.sistema_locacao.entity.FuncionarioModel;
import com.br.devborges.sistema_locacao.mapper.FuncionarioMapper;
import com.br.devborges.sistema_locacao.repository.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioMapper funcionarioMapper;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, FuncionarioMapper funcionarioMapper) {
        this.funcionarioRepository = funcionarioRepository;
        this.funcionarioMapper = funcionarioMapper;
    }

    public FuncionarioDTO adicionarFuncionario(FuncionarioDTO novoFuncionario) {
        FuncionarioModel funcionario = funcionarioMapper.toModel(novoFuncionario);

        if (funcionarioRepository.existsByEmail(novoFuncionario.email()))
            throw new IllegalArgumentException("Email: " + novoFuncionario.email() + " ja cadastrado");

        if (funcionarioRepository.existsByMatricula(novoFuncionario.matricula()))
            throw new IllegalArgumentException("Matricula: " + novoFuncionario.matricula() + " ja cadastrado");

        return funcionarioMapper.toDto(funcionarioRepository.save(funcionario));
    }

    public List<FuncionarioDTO> listarAll() {
        List<FuncionarioModel> lista = funcionarioRepository.findAll();
        return lista.stream()
                .map(funcionarioMapper::toDto)
                .toList();
    }

    public FuncionarioDTO listarPorId(Long id) {
        return funcionarioRepository.findById(id)
                .map(funcionarioMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Funcionario com ID" + id + " nao encontrado."));
    }

    public FuncionarioDTO buscarPorMatricula(String matricula) {
        return funcionarioRepository.findByMatricula(matricula)
                .map(funcionarioMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Funcionario com a matricula " + matricula + " nao encontrado."));
    }

    public FuncionarioDTO alterarFuncionario(Long id, FuncionarioDTO funcionarioDTO) {
        FuncionarioModel funcionarioEncontrado = funcionarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionario com a ID " + id + " nao encontrado."));

        funcionarioMapper.updateModelFromDto(funcionarioDTO, funcionarioEncontrado);

        return funcionarioMapper.toDto(funcionarioRepository.save(funcionarioEncontrado));
    }

    public void deletarFuncionario(Long id) {
        FuncionarioModel funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionario com ID " + id + " não encontrado."));

        funcionarioRepository.delete(funcionario);
    }


}