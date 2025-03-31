package parcial3.config;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.server.i18n.AcceptHeaderLocaleContextResolver;
import org.springframework.web.server.i18n.LocaleContextResolver;

/**
 * Configuración de la internacionalización (i18n) en la aplicación.
 * Define la fuente de mensajes y el manejo del contexto de idioma basado en los headers de la solicitud.
 */
@Configuration
public class LocaleConfig {

    /**
     * Configura la fuente de mensajes para la internacionalización.
     * Carga los archivos de mensajes desde la ubicación especificada y define la codificación.
     *
     * @return Un {@link MessageSource} que carga los archivos messages_xx.properties.
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages"); // Carga los archivos de mensajes
        messageSource.setDefaultEncoding("UTF-8"); // Asegura compatibilidad con caracteres especiales
        return messageSource;
    }

    /**
     * Configura el resolver de contexto de idioma.
     * Utiliza los headers de la solicitud para determinar el idioma preferido por el usuario.
     *
     * @return Un {@link LocaleContextResolver} que resuelve el idioma según el "Accept-Language" del header HTTP.
     */
    @Bean
    public LocaleContextResolver localeContextResolver() {
        AcceptHeaderLocaleContextResolver resolver = new AcceptHeaderLocaleContextResolver();
        resolver.setDefaultLocale(Locale.ENGLISH); // Establece el inglés como idioma predeterminado
        return resolver;
    }
}
