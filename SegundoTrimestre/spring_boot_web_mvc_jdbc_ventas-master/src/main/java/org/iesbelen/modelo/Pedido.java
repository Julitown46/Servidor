package org.iesbelen.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pedido {
    private Integer id;
    private double total;
    private LocalDate fecha;
    private Long id_cliente;
    private Integer id_comercial;
}