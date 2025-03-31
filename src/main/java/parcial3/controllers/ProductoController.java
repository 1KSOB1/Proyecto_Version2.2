package parcial3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import parcial3.dtos.Producto;
import parcial3.services.ProductoService;
import reactor.core.publisher.Flux;

/**
 * Controlador REST para gestionar productos.
 * <p>
 * Proporciona un endpoint para listar todos los productos disponibles.
 * Usa programación reactiva con Spring WebFlux.
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    /**
     * Obtiene la lista de productos disponibles.
     *
     * @return Un flujo reactivo (Flux) con la lista de productos.
     */
    @GetMapping
    public Flux<Producto> listarProductos() {
        return productoService.obtenerProductos();
    }
}
