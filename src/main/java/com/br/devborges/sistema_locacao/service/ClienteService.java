package com.br.devborges.sistema_locacao.service;
import com.br.devborges.sistema_locacao.dto.ClienteDTO;
import com.br.devborges.sistema_locacao.entity.ClienteModel;
import com.br.devborges.sistema_locacao.mapper.ClienteMapper;
import com.br.devborges.sistema_locacao.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public ClienteDTO adicionarCliente(ClienteDTO novoCliente) {

        if (clienteRepository.existsByCpf(novoCliente.cpf()))
            throw new IllegalArgumentException("CPF ja cadastrado.");

        if (clienteRepository.existsByEmail(novoCliente.email()))
            throw new IllegalArgumentException("Email ja cadastrado.");

        ClienteModel cliente = clienteMapper.toModel(novoCliente);
        return clienteMapper.toDto(clienteRepository.save(cliente));

    }

    public List<ClienteDTO> listarClientes(){
        List<ClienteModel> lista = clienteRepository.findAll();
        return lista.stream()
                .map(clienteMapper::toDto)
                .toList();
    }

    public ClienteDTO clientePorId(Long id){
        return clienteRepository.findById(id)
                .map(clienteMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Cliente com ID " + id + "não encontrado."));

    }

    public ClienteDTO atualizarCliente(Long id, ClienteDTO clienteDTO){
       ClienteModel clienteEncontrado = clienteRepository.findById(id)
               .orElseThrow(() -> new EntityNotFoundException("Cliente com ID " + id + "não encontrado."));

       ClienteModel clienteAtualizado = clienteMapper.toModel(clienteDTO);
       clienteAtualizado.setId(id);
       return clienteMapper.toDto(clienteRepository.save(clienteAtualizado));
    }

    public void deletarCliente(Long id){
        if(!clienteRepository.existsById(id)){
            throw new EntityNotFoundException("Cliente com ID" + id + "não encontrado.");
        }
        clienteRepository.deleteById(id);

    }

}
