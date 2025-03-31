package parcial3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import parcial3.dtos.Pedido;
import parcial3.services.PedidoService;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Controlador para la gestión de pedidos en la API.
 * Soporta operaciones de listado, creación de pedidos y recuperación de mensajes en múltiples idiomas.
 */
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private MessageSource messageSource;

    /**
     * Determina el idioma preferido del usuario basado en el parámetro "lang" o los headers de la solicitud.
     *
     * @param lang    Parámetro opcional para especificar el idioma.
     * @param headers Cabeceras HTTP que pueden contener "Accept-Language".
     * @return El {@link Locale} correspondiente al idioma detectado.
     */
    private Object getLocale(String lang, HttpHeaders headers) {
        if (lang != null) {
            return new Locale(lang);
        } else if (!headers.getAcceptLanguage().isEmpty()) {
            return headers.getAcceptLanguage().get(0).getRange().isEmpty() ? Locale.ENGLISH : headers.getAcceptLanguage().get(0);
        }
        return Locale.ENGLISH; // 🔹 Idioma por defecto
    }

    /**
     * Lista todos los pedidos disponibles en la base de datos.
     *
     * @param lang    Parámetro opcional para definir el idioma de la respuesta.
     * @param headers Cabeceras HTTP con información de idioma.
     * @return Un {@link Mono} con un mapa conteniendo el mensaje y la lista de pedidos.
     */
    @GetMapping
    public Mono<Map<String, Object>> listarPedidos(@RequestParam(name = "lang", required = false) String lang,
                                                   @RequestHeader HttpHeaders headers) {
        Locale locale = (Locale) getLocale(lang, headers);
        return pedidoService.obtenerPedidos()
                .collectList()
                .map(pedidos -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("mensaje", messageSource.getMessage("mensaje.lista", null, locale));
                    response.put("pedidos", pedidos);
                    return response;
                });
    }

    /**
     * Crea un nuevo pedido y devuelve un mensaje en el idioma seleccionado.
     *
     * @param pedido  Objeto recibido en el cuerpo de la solicitud con la información del pedido.
     * @param lang    Parámetro opcional para definir el idioma de la respuesta.
     * @param headers Cabeceras HTTP con información de idioma.
     * @return Un {@link Mono} con un mapa conteniendo el mensaje de confirmación y el pedido creado.
     */
    @PostMapping("/crear")
    public Mono<Map<String, Object>> crearPedido(@RequestBody Pedido pedido,
                                                 @RequestParam(name = "lang", required = false) String lang,
                                                 @RequestHeader HttpHeaders headers) {
        Locale locale = (Locale) getLocale(lang, headers);
        return pedidoService.crearPedido(pedido)
                .map(p -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("mensaje", messageSource.getMessage("mensaje.creado", null, locale));
                    response.put("pedido", p);
                    return response;
                });
    }

    /**
     * Retorna un mensaje de saludo en el idioma solicitado.
     *
     * @param lang    Parámetro opcional para definir el idioma de la respuesta.
     * @param headers Cabeceras HTTP con información de idioma.
     * @return Un {@link Mono} con un mapa conteniendo el mensaje localizado.
     */
    @GetMapping("/mensaje")
    public Mono<Map<String, String>> obtenerSaludo(@RequestParam(name = "lang", required = false) String lang,
                                                   @RequestHeader HttpHeaders headers) {
        Locale locale = (Locale) getLocale(lang, headers); //Casteo de tipo de dato
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", messageSource.getMessage("mensaje.pedido", null, locale));
        return Mono.just(response);
    }
}
