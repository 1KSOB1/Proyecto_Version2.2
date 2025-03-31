package parcial3.services;

import org.springframework.stereotype.Service;
import parcial3.dtos.Producto;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * Servicio para la gestión de productos.
 * <p>
 * Proporciona funcionalidades para obtener la lista de productos disponibles.
 */
@Service
public class ProductoService {

    // Lista de productos predefinidos
    private final List<Producto> productos = List.of(
            new Producto("1", "Laptop", 1200.0),
            new Producto("2", "Mouse", 25.0),
            new Producto("3", "Teclado", 45.0)
    );

    /**
     * Obtiene la lista de productos disponibles.
     *
     * @return Un flujo reactivo (Flux) con los productos disponibles.
     */
    public Flux<Producto> obtenerProductos() {
        return Flux.fromIterable(productos);
    }
}
