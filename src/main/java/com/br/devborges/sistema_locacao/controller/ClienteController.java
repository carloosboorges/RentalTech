package com.br.devborges.sistema_locacao.controller;

import com.br.devborges.sistema_locacao.dto.ClienteDTO;
import com.br.devborges.sistema_locacao.service.ClienteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    @PostMapping("/adicionar")
    public ResponseEntity<ClienteDTO> adicionarCliente(@RequestBody ClienteDTO clienteDTO){
        ClienteDTO novoCliente = clienteService.adicionarCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }

    @GetMapping()
    public ResponseEntity<List<ClienteDTO>> listarClientes(){
        List<ClienteDTO> lista = clienteService.listarClientes();
        if(lista.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarClientePorId(@PathVariable Long id){
        try{
            ClienteDTO cliente = clienteService.clientePorId(id);
            return ResponseEntity.ok().body(cliente);

        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO){
        try{
            ClienteDTO cliente = clienteService.atualizarCliente(id, clienteDTO);
            return ResponseEntity.ok().body(cliente);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCliente(@PathVariable Long id){
        try{
            clienteService.deletarCliente(id);
            return ResponseEntity.ok().body("Cliente com ID " + id + " deletado com sucesso.");
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
