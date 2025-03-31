📌 Proyecto: API REST con Spring WebFlux

Este proyecto es una API REST desarrollada con Spring Boot WebFlux, que maneja pedidos y productos de manera reactiva. Implementa soporte para múltiples idiomas (i18n), pruebas automatizadas y configuración de seguridad.

🚀 Tecnologías Utilizadas

Spring Boot 3

Spring WebFlux (Programación reactiva)

Spring Security (Configuración básica de seguridad)

i18n (Internacionalización)

Lombok (Para reducir código repetitivo)

Reactor Core (Para manejo de datos reactivos)

Mockito y JUnit 5 (Para pruebas automatizadas)

📁 Estructura del Proyecto

parcial3/
│── src/main/java/parcial3/
│   ├── config/         # Configuración de i18n y seguridad
│   ├── controllers/    # Controladores REST
│   ├── dtos/           # Clases DTO (Data Transfer Objects)
│   ├── services/       # Servicios de lógica de negocio
│   ├── LocaleConfig.java  # Configuración de idiomas
│   ├── SecurityConfig.java # Configuración de seguridad
│── src/test/java/parcial3/ # Pruebas unitarias
│── pom.xml              # Dependencias del proyecto
│── README.md            # Documentación

📌 Endpoints Disponibles

📦 Gestión de Productos

Método

Endpoint

Descripción

GET

/api/productos

Listar productos

📋 Gestión de Pedidos

Método

Endpoint

Descripción

GET

/api/pedidos

Listar pedidos

POST

/api/pedidos

Crear pedido

GET

/api/pedidos/mensaje

Obtener mensaje en el idioma solicitado

🌍 Internacionalización (i18n)

Método

Endpoint

Descripción

GET

/api/saludo?lang=es

Mensaje en español

GET

/api/saludo?lang=fr

Mensaje en francés

GET

/api/saludo?lang=en

Mensaje en inglés (por defecto)

🔒 Seguridad

Se ha configurado Spring Security para permitir todas las solicitudes sin autenticación. Sin embargo, está preparado para futuras mejoras de seguridad.

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .authorizeExchange(exchanges -> exchanges.anyExchange().permitAll())
            .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
            .formLogin(ServerHttpSecurity.FormLoginSpec::disable);
        return http.build();
    }
}

🧪 Pruebas Automatizadas

Se implementaron pruebas con JUnit 5 y Mockito para validar la creación de pedidos y productos de manera reactiva.

📝 Ejemplo de Prueba con StepVerifier

@Test
void testCrearPedido() {
    Pedido nuevoPedido = new Pedido("1", "Monitor", 1, 300.0, LocalDateTime.now());
    PedidoService pedidoService = new PedidoService();
    Mono<Pedido> resultado = pedidoService.crearPedido(nuevoPedido);
    
    StepVerifier.create(resultado)
        .expectNextMatches(pedido -> pedido.getProducto().equals("Monitor"))
        .verifyComplete();
}

🛠 Configuración de Internacionalización (i18n)

Los mensajes están definidos en archivos dentro de resources:

src/main/resources/
│── messages_en.properties  # Mensajes en inglés
│── messages_es.properties  # Mensajes en español
│── messages_fr.properties  # Mensajes en francés

Ejemplo (messages_es.properties):

saludo=Hola, bienvenido a nuestra API
mensaje.lista=Lista de pedidos obtenida con éxito
mensaje.creado=Pedido creado exitosamente
