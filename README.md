# Adsi Agendecitas

Proyecto web sencillo en Java para evidenciar:

- Formularios HTML con Servlets
- Uso de metodos GET y POST
- Uso de JSP
- Versionamiento con Git

## Estructura

- `src/main/java`: clases Java, modelo, repositorio y servlets
- `src/main/webapp`: vistas JSP y recursos estaticos

## Flujo basico

1. El usuario entra a `inicio` y registra una cita.
2. El formulario envia los datos con `POST` al servlet `RegistrarCitaServlet`.
3. El administrador entra por `login`.
4. El servlet `DashboardServlet` muestra las citas guardadas usando `GET`.
5. Desde el panel se puede eliminar una cita con `POST`.

## Credenciales de prueba

- Usuario: `admin`
- Clave: `1234`

## Ejecucion

1. Tener Java y Maven instalados.
2. Ejecutar `mvn clean package`.
3. Desplegar el archivo `target/adsi-agendecitas.war` en Apache Tomcat 10 o superior.

## Nota academica

Las citas se guardan en memoria para mantener el proyecto simple. Si el servidor se reinicia, los datos se pierden.
