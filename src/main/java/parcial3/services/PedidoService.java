package parcial3.services;

import org.springframework.stereotype.Service;
import parcial3.dtos.Pedido;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Servicio para la gestión de pedidos.
 * Proporciona métodos para obtener y crear pedidos de manera reactiva.
 */
@Service
public class PedidoService {

    /** Lista en memoria para almacenar los pedidos. */
    private final List<Pedido> pedidos = new ArrayList<>();

    /**
     * Obtiene la lista de pedidos disponibles.
     *
     * @return Un {@link Flux} que emite los pedidos almacenados.
     */
    public Flux<Pedido> obtenerPedidos() {
        return Flux.fromIterable(pedidos);
    }

    /**
     * Crea un nuevo pedido, asignando un ID único y la fecha actual.
     *
     * @param pedido Objeto con la información del pedido a crear.
     * @return Un {@link Mono} con el pedido creado.
     */
    public Mono<Pedido> crearPedido(Pedido pedido) {
        pedido.setId(UUID.randomUUID().toString()); // Genera un ID único para el pedido
        pedido.setFecha(LocalDateTime.now()); // Asigna la fecha y hora actual
        pedidos.add(pedido); // Almacena el pedido en la lista
        return Mono.just(pedido);
    }
}
