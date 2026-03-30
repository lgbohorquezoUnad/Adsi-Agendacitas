# Pruebas de la API

Este archivo sirve como apoyo para la evidencia de desempeno sobre servicios web.

## Requisitos para probar

1. Tener el proyecto desplegado en Tomcat.
2. Verificar la ruta base del proyecto.
3. Usar Postman o Thunder Client en VS Code.

Ejemplo de URL base:

```text
http://localhost:8080/adsi-agendecitas
```

## Servicio 1: listar citas

- Metodo: `GET`
- URL:

```text
http://localhost:8080/adsi-agendecitas/api/citas
```

- Resultado esperado:
  - Si no hay datos, devuelve `[]`
  - Si hay datos, devuelve una lista en formato JSON

## Servicio 2: registrar cita

- Metodo: `POST`
- URL:

```text
http://localhost:8080/adsi-agendecitas/api/citas
```

- Body tipo `form-urlencoded`:

```text
nombre=Laura Gomez
correo=laura@gmail.com
telefono=3001234567
servicio=Abogados
fecha=2026-04-10
mensaje=Necesito asesoria
```

- Resultado esperado:
  - Codigo `201`
  - Respuesta JSON con los datos registrados

## Servicio 3: eliminar cita

- Metodo: `POST`
- URL:

```text
http://localhost:8080/adsi-agendecitas/api/citas/eliminar
```

- Body tipo `form-urlencoded`:

```text
id=1
```

- Resultado esperado:
  - Codigo `200`
  - Mensaje JSON confirmando la eliminacion

### Caso alterno: eliminar una cita que no existe

- Metodo: `POST`
- URL:

```text
http://localhost:8080/adsi-agendecitas/api/citas/eliminar
```

- Body tipo `form-urlencoded`:

```text
id=999
```

- Resultado esperado:
  - Codigo `404`
  - Mensaje JSON indicando que no existe una cita con ese id

## Servicio 4: login

- Metodo: `POST`
- URL:

```text
http://localhost:8080/adsi-agendecitas/api/login
```

- Body tipo `form-urlencoded`:

```text
usuario=admin
clave=1234
```

- Resultado esperado:
  - Codigo `200`
  - Mensaje JSON de acceso correcto

## Pruebas recomendadas para capturas

Para la evidencia puedes tomar capturas de:

1. `GET /api/citas`
2. `POST /api/citas` con registro exitoso
3. `POST /api/login` con acceso correcto
4. `POST /api/login` con clave incorrecta
5. `POST /api/citas/eliminar`

## Errores que tambien puedes mostrar

### Registrar cita sin campos completos

- Metodo: `POST`
- URL:

```text
http://localhost:8080/adsi-agendecitas/api/citas
```

- Body incompleto:

```text
nombre=Laura Gomez
correo=
telefono=3001234567
servicio=Abogados
fecha=2026-04-10
```

- Resultado esperado:
  - Codigo `400`
  - Mensaje JSON con error

### Login incorrecto

- Metodo: `POST`
- URL:

```text
http://localhost:8080/adsi-agendecitas/api/login
```

- Body:

```text
usuario=admin
clave=9999
```

- Resultado esperado:
  - Codigo `401`
  - Mensaje JSON con error

## Explicacion simple para sustentar

Este proyecto tiene servicios web hechos con Servlets en Java. Los servicios reciben datos por HTTP, procesan la informacion y responden en formato JSON. Se implementaron metodos `GET` para consultar datos y `POST` para registrar, eliminar y validar acceso. La documentacion de cada servicio se encuentra en `README.md` y en este archivo de pruebas.
