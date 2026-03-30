# Adsi Agendecitas

Proyecto web sencillo en Java que sirve como evidencia para:

- Modulo web con `Servlets` y `JSP`
- Formularios HTML con metodos `GET` y `POST`
- Diseno y desarrollo de servicios web
- Documentacion de endpoints
- Versionamiento con Git y GitHub

## Estructura

- `src/main/java`: clases Java, modelo, repositorio, servlets web y servlets API
- `src/main/webapp`: vistas JSP y recursos estaticos

## Modulo web tradicional

1. El usuario entra a `inicio` y registra una cita.
2. El formulario envia los datos con `POST` al servlet `RegistrarCitaServlet`.
3. El administrador entra por `login`.
4. El servlet `DashboardServlet` muestra las citas guardadas usando `GET`.
5. Desde el panel se puede eliminar una cita con `POST`.

## Servicios web creados

Las APIs se hicieron con codigo simple y respuestas en `JSON`.

### 1. Listar citas

- URL: `/api/citas`
- Metodo: `GET`
- Descripcion: devuelve todas las citas registradas

Ejemplo de respuesta:

```json
[
  {
    "id": 1,
    "nombre": "Laura Gomez",
    "correo": "laura@gmail.com",
    "telefono": "3001234567",
    "servicio": "Abogados",
    "fecha": "2026-04-10",
    "mensaje": "Necesito asesoria"
  }
]
```

### 2. Registrar cita

- URL: `/api/citas`
- Metodo: `POST`
- Descripcion: registra una nueva cita
- Parametros:
  - `nombre`
  - `correo`
  - `telefono`
  - `servicio`
  - `fecha`
  - `mensaje` opcional

Ejemplo de envio:

```text
nombre=Laura Gomez
correo=laura@gmail.com
telefono=3001234567
servicio=Abogados
fecha=2026-04-10
mensaje=Necesito asesoria
```

Ejemplo de respuesta:

```json
{
  "id": 1,
  "nombre": "Laura Gomez",
  "correo": "laura@gmail.com",
  "telefono": "3001234567",
  "servicio": "Abogados",
  "fecha": "2026-04-10",
  "mensaje": "Necesito asesoria"
}
```

### 3. Eliminar cita

- URL: `/api/citas/eliminar`
- Metodo: `POST`
- Descripcion: elimina una cita enviando el identificador
- Parametro:
  - `id`

Ejemplo de respuesta:

```json
{
  "mensaje": "Cita eliminada correctamente."
}
```

Ejemplo si la cita no existe:

```json
{
  "error": "No existe una cita con el id enviado."
}
```

### 4. Login de administrador

- URL: `/api/login`
- Metodo: `POST`
- Descripcion: valida el acceso del administrador
- Parametros:
  - `usuario`
  - `clave`

Ejemplo de envio:

```text
usuario=admin
clave=1234
```

Ejemplo de respuesta correcta:

```json
{
  "mensaje": "Inicio de sesion correcto.",
  "usuario": "admin"
}
```

Ejemplo de respuesta con error:

```json
{
  "error": "Credenciales incorrectas."
}
```

## Credenciales de prueba

- Usuario: `admin`
- Clave: `1234`

## Ejecucion

1. Tener Java y Maven instalados.
2. Ejecutar `mvn clean package`.
3. Desplegar el archivo `target/adsi-agendecitas.war` en Apache Tomcat 10 o superior.

## Pruebas simples sugeridas

Puedes probar los servicios con Postman o Thunder Client en VS Code.

- `GET /api/citas`
- `POST /api/citas`
- `POST /api/citas/eliminar`
- `POST /api/login`

## Archivos de evidencia incluidos

- `README.md`: descripcion general del proyecto y de los endpoints
- `PRUEBAS_API.md`: guia de pruebas para Postman o Thunder Client
- `docs/documentacion_servicios.*`: documentacion de servicios en varios formatos
- `ENLACE_REPOSITORIO.txt`: enlace del repositorio para anexar en la entrega

## Nota academica

Las citas se guardan en memoria para mantener el proyecto simple. Si el servidor se reinicia, los datos se pierden.
