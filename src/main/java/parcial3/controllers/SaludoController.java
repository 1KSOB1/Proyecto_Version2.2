package parcial3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Locale;

/**
 * Controlador REST para manejar saludos en diferentes idiomas.
 * <p>
 * Utiliza internacionalización (i18n) para devolver mensajes en el idioma solicitado.
 */
@RestController
@RequestMapping("/api")
public class SaludoController {

    @Autowired
    private MessageSource messageSource;

    /**
     * Obtiene un saludo en el idioma especificado.
     *
     * @param lang Código del idioma opcional (ejemplo: "es" para español, "fr" para francés).
     * @return Mono con el saludo en el idioma correspondiente.
     */
    @GetMapping("/saludo")
    public Mono<String> obtenerSaludo(@RequestParam(name = "lang", required = false) String lang) {
        Locale locale = (lang != null) ? new Locale(lang) : Locale.ENGLISH; // Idioma por defecto: Inglés
        return Mono.just(messageSource.getMessage("saludo", null, locale));
    }
}
