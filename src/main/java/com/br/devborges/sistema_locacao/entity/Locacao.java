package com.br.devborges.sistema_locacao.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "locacao_tb")
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "data_retirada")
    private LocalDate dataRetirada;

    @Column(name = "data_devolucao")
    private LocalDate dataDevolucao;

    @Column(name = "total")
    private Double total;

    @Column(name = "observacao")
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "equipamento_id")
    private Equipamento equipamento;
}
