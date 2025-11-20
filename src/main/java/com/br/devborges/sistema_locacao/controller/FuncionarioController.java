package com.br.devborges.sistema_locacao.controller;

import com.br.devborges.sistema_locacao.dto.FuncionarioDTO;
import com.br.devborges.sistema_locacao.service.FuncionarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping("/adicionar")
    public ResponseEntity<FuncionarioDTO> adicionarFuncionario(@RequestBody FuncionarioDTO novoFuncionario) {
        FuncionarioDTO funcionario = funcionarioService.adicionarFuncionario(novoFuncionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);
    }

    @GetMapping()
    public ResponseEntity<List<FuncionarioDTO>> listarAll() {
        List<FuncionarioDTO> lista = funcionarioService.listarAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            FuncionarioDTO funcionario = funcionarioService.listarPorId(id);
            return ResponseEntity.ok().body(funcionario);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<?> buscarPorMatricula(@PathVariable String matricula) {
        try {
            FuncionarioDTO funcioanrio = funcionarioService.buscarPorMatricula(matricula);
            return ResponseEntity.ok().body(funcioanrio);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarFuncionario(@PathVariable Long id, @RequestBody FuncionarioDTO funcionarioDTO) {
        try {
            FuncionarioDTO funcionario = funcionarioService.alterarFuncionario(id, funcionarioDTO);
            return ResponseEntity.ok().body(funcionario);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarFuncionario(@PathVariable Long id) {
        try {
            funcionarioService.deletarFuncionario(id);
            return ResponseEntity.ok().body("Funcionario com ID" + id + " deletado com sucesso.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
