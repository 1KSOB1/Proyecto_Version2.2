package parcial3.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * Representa un pedido en el sistema.
 * Contiene información sobre el producto, cantidad, precio total y fecha del pedido.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    /**
     * Identificador único del pedido.
     */
    private String id;

    /**
     * Nombre del producto solicitado en el pedido.
     */
    private String producto;

    /**
     * Cantidad de productos en el pedido.
     */
    private int cantidad;

    /**
     * Precio total del pedido, calculado como cantidad * precio unitario.
     */
    private double precioTotal;

    /**
     * Fecha y hora en que se realizó el pedido.
     */
    private LocalDateTime fecha;
}
