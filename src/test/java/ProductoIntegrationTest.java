import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.junit.jupiter.api.Assertions.*;

import parcial3.controllers.Producto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class ProductoIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    private Producto productoCreado;

    @BeforeEach
    void setUp() {
        // Crear un producto antes de cada prueba para asegurar que exista
        productoCreado = webTestClient.post()
                .uri("/api/productos")
                .bodyValue(new Producto(null, "Producto Test"))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Producto.class)
                .returnResult()
                .getResponseBody();
    }

    @Test
    public void testCrearProducto() {
        Producto producto = new Producto(null, "Nuevo Producto");

        Producto resultado = webTestClient.post()
                .uri("/api/productos")
                .bodyValue(producto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Producto.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(resultado);
        assertEquals("Nuevo Producto", resultado.getNombre());
    }

    @Test
    public void testObtenerProductoPorId() {
        webTestClient.get()
                .uri("/api/productos/{id}", productoCreado.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Producto.class)
                .value(p -> assertEquals(productoCreado.getId(), p.getId()));
    }

    @Test
    public void testEliminarProducto() {
        webTestClient.delete()
                .uri("/api/productos/{id}", productoCreado.getId())
                .exchange()
                .expectStatus().isNoContent();
    }
}