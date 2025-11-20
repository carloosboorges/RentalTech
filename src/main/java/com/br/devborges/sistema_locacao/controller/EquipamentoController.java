package com.br.devborges.sistema_locacao.controller;

import com.br.devborges.sistema_locacao.dto.EquipamentoDTO;
import com.br.devborges.sistema_locacao.service.EquipamentoService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipamento")
public class EquipamentoController {

    private final EquipamentoService equipamentoService;


    public EquipamentoController(EquipamentoService equipamentoService) {
        this.equipamentoService = equipamentoService;
    }

    @PostMapping
    public ResponseEntity<EquipamentoDTO> adicionarEquipamento(@RequestBody EquipamentoDTO novoEquipamneto) {
        EquipamentoDTO equipamento = equipamentoService.adicionarEquipamento(novoEquipamneto);
        return ResponseEntity.status(HttpStatus.CREATED).body(equipamento);
    }

    @GetMapping
    public ResponseEntity<List<EquipamentoDTO>> listarAll() {
        List<EquipamentoDTO> lista = equipamentoService.listarEquipamentos();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id) {
        try {
            EquipamentoDTO equipamento = equipamentoService.listarPorId(id);
            return ResponseEntity.ok().body(equipamento);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarEquipamento(@PathVariable Long id, @RequestBody EquipamentoDTO equipamentoDTO) {
        try {
            EquipamentoDTO equipamento = equipamentoService.atualizarEquipamento(id, equipamentoDTO);
            return ResponseEntity.ok().body(equipamento);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarEquipamento(@PathVariable Long id) {
        try {
            equipamentoService.deletarEquipameno(id);
            return ResponseEntity.ok().body("Equipamento com ID " + id + " deletado com sucesso.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
