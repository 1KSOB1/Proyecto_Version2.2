package parcial3.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa un producto con un identificador, nombre y precio.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    /** Identificador único del producto. */
    private String id;

    /** Nombre del producto. */
    private String nombre;

    /** Precio del producto. */
    private double precio;
}
