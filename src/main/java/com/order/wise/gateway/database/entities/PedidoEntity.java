package com.order.wise.gateway.database.entities;


import com.order.wise.domain.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    @CreationTimestamp
    @Column(name = "data_criacao", nullable = false)
    private ZonedDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private Status status;

    @Column(name = "cartao_credito", nullable = false, length =19 )
    private String cartaoCredito;

    @Column(name = "pagamento_id", nullable = false)
    private Long pagamentoId;

    @DecimalMin(value = "0.00", inclusive = false)
    @Column(name = "valor_total", precision = 10, scale = 2, nullable = false)
    private BigDecimal valorTotal;

    @OneToMany(mappedBy = "pedido_id", cascade = CascadeType.ALL)
    private List<ItensPedidosEntity> itensPedidos;

}
