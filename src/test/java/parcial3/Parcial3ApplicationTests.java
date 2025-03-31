package parcial3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import parcial3.dtos.Pedido;
import parcial3.services.PedidoService;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

@SpringBootTest
class Parcial3ApplicationTests {

	@Autowired
	private PedidoService pedidoService;

	@Test
	void contextLoads() {
	}

	@Test
	void testCrearPedido() {
		Pedido nuevoPedido = new Pedido(null, "Monitor", 1, 250.0, LocalDateTime.now());

		Mono<Pedido> resultado = pedidoService.crearPedido(nuevoPedido);

		StepVerifier.create(resultado)
				.expectNextMatches(pedido -> pedido.getProducto().equals("Celular") && pedido.getCantidad() == 1)
				.verifyComplete();
	}
}