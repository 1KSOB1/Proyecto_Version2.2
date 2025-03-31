Proyecto: Mi Aplicación Spring Boot

Descripción

Este es un proyecto de ejemplo desarrollado con Spring Boot para demostrar la creación de una API REST con pruebas automatizadas utilizando JUnit 5 y WebTestClient.

Tecnologías Utilizadas

Java 17

Spring Boot

Maven

JUnit 5

WebTestClient

IntelliJ IDEA

Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

JDK 17 o superior

Maven

IntelliJ IDEA

Instalación

Clonar el repositorio:

git clone https://github.com/tuusuario/miaplicacion.git
cd miaplicacion

Compilar el proyecto con Maven:

mvn clean install

Ejecución de la Aplicación

Para ejecutar el proyecto, usa el siguiente comando:

mvn spring-boot:run

O desde IntelliJ IDEA:

Abre el proyecto en IntelliJ IDEA.

Dirígete a la clase MiaplicacionApplication.java.

Haz clic en Run.

Una vez iniciado, la API estará disponible en:

http://localhost:8080/api

Endpoints Disponibles

1. Crear un Producto

Método: POST

URL: /api/productos

Cuerpo de la Petición:

{
  "id": null,
  "nombre": "Producto Test"
}

Respuesta Esperada:

{
  "id": "1",
  "nombre": "Producto Test"
}

2. Obtener un Producto por ID

Método: GET

URL: /api/productos/{id}

3. Eliminar un Producto

Método: DELETE

URL: /api/productos/{id}

Ejecución de Pruebas

Para ejecutar las pruebas automatizadas, usa:

mvn test

Esto ejecutará las pruebas de integración en ProductoIntegrationTest.java.

Autores

Kevin Sam - kevinsam31@gmail.com

Licencia

Este proyecto está bajo la licencia MIT.