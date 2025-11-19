package com.br.devborges.sistema_locacao.entity;
import com.br.devborges.sistema_locacao.enums.StatusEquipamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "equipamento_tb")
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "valorDiaria")
    private String valorDiaria;

    @Column(name = "status")
    private StatusEquipamento status;
}
