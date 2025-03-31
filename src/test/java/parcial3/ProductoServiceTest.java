package parcial3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import parcial3.controllers.Producto;
import parcial3.controllers.ProductoRepository;
import parcial3.controllers.ProductoService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarProductos() {
        List<Producto> productos = Arrays.asList(new Producto("1", "Producto 1"), new Producto("2", "Producto 2"));
        when(productoRepository.findAll()).thenReturn(productos);

        List<Producto> result = productoService.listarProductos();
        assertEquals(2, result.size());
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    public void testObtenerProductoPorId() {
        Producto producto = new Producto("1", "Producto 1");
        when(productoRepository.findById("1")).thenReturn(Optional.of(producto));

        Producto result = productoService.obtenerProductoPorId("1");
        assertEquals("Producto 1", result.getNombre());
        verify(productoRepository, times(1)).findById("1");
    }

    @Test
    public void testCrearProducto() {
        Producto producto = new Producto("1", "Producto 1");
        when(productoRepository.save(producto)).thenReturn(producto);

        Producto result = productoService.crearProducto(producto);
        assertEquals("Producto 1", result.getNombre());
        verify(productoRepository, times(1)).save(producto);
    }
}
