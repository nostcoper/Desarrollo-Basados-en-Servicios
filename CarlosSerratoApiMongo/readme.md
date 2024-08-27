# Documentación de la API de Ítems


## Descripción General

Esta API permite gestionar ítems de un supermercado. Los ítems son representados por la clase `GroceryItem`, que se almacena en MongoDB.

## Acceso a la Interfaz Swagger

Para obtener información más detallada sobre los endpoints de la API, parámetros, respuestas y ejemplos, puedes acceder a la interfaz Swagger a través del siguiente enlace:

**http://localhost:8090/swagger-ui/index.html**

## Definición de HTTP Verbs

### 1. Obtener todos los ítems
- **Verbo HTTP**: `GET`
- **Ruta**: `/item/getAll`
- **Descripción**: Recupera una lista de todos los ítems almacenados.
- **Respuesta**: 
  - Código `200 OK`: Lista de ítems obtenida exitosamente.

### 2. Obtener un ítem específico
- **Verbo HTTP**: `GET`
- **Ruta**: `/item/get/{id}`
- **Descripción**: Recupera un ítem específico basado en su ID.
- **Parámetros**:
  - `id` (Path Variable): El ID del ítem a recuperar.
- **Respuesta**: 
  - Código `200 OK`: Ítem obtenido exitosamente.

### 3. Insertar un nuevo ítem
- **Verbo HTTP**: `POST`
- **Ruta**: `/item/insert`
- **Descripción**: Inserta un nuevo ítem en la base de datos.
- **Cuerpo de la Solicitud**: Un objeto JSON representando el ítem a insertar.
- **Respuesta**: 
  - Código `201 Created`: Ítem creado exitosamente.

### 4. Actualizar un ítem completo
- **Verbo HTTP**: `PUT`
- **Ruta**: `/item/update`
- **Descripción**: Actualiza un ítem completo en la base de datos.
- **Cuerpo de la Solicitud**: Un objeto JSON representando el ítem con datos actualizados.
- **Respuesta**: 
  - Código `200 OK`: Ítem actualizado exitosamente.

### 5. Eliminar un ítem
- **Verbo HTTP**: `DELETE`
- **Ruta**: `/item/delete/{id}`
- **Descripción**: Elimina un ítem basado en su ID.
- **Parámetros**:
  - `id` (Path Variable): El ID del ítem a eliminar.
- **Respuesta**: 
  - Código `204 No Content`: Ítem eliminado exitosamente.

### 6. Actualizar parcialmente un ítem
- **Verbo HTTP**: `PATCH`
- **Ruta**: `/item/updateData/{id}`
- **Descripción**: Actualiza parcialmente un ítem en la base de datos.
- **Parámetros**:
  - `id` (Path Variable): El ID del ítem a actualizar.
- **Cuerpo de la Solicitud**: Un objeto JSON con los datos a actualizar.
- **Respuesta**: 
  - Código `200 OK`: Ítem actualizado parcialmente exitosamente.

### 7. Verificar disponibilidad de ítems
- **Verbo HTTP**: `HEAD`
- **Ruta**: `/item/getAll`
- **Descripción**: Verifica si hay ítems disponibles sin recuperar el contenido de la lista.
- **Respuesta**: 
  - Código `200 OK`: Indica que hay ítems disponibles.

## Anotaciones en el Controlador

### `@RestController`
- **Descripción**: Indica que la clase es un controlador de Spring que maneja solicitudes HTTP y produce respuestas JSON. Combina `@Controller` y `@ResponseBody`.

### `@RequestMapping("item")`
- **Descripción**: Define el prefijo de la ruta para todos los métodos en el controlador. En este caso, todas las rutas comenzarán con `/item`.

### `@Autowired`
- **Descripción**: Permite la inyección automática de dependencias en la clase. En este caso, inyecta una instancia del servicio `ItemService`.

## Clase `GroceryItem`

La clase `GroceryItem` representa un ítem del supermercado y contiene los siguientes campos:

- **`id`**: Identificador único del ítem.
- **`name`**: Nombre del ítem.
- **`quantity`**: Cantidad del ítem disponible.
- **`category`**: Categoría del ítem.

### Etiqueta `@Override`

- **Descripción**: Indica que el método está sobrescribiendo un método de una clase base o interfaz. En la clase `GroceryItem`, el método `toString()` es sobrescrito para proporcionar una representación en cadena del ítem.

---

Este README proporciona una visión general de los endpoints de la API, las anotaciones usadas en el controlador, y detalles sobre la clase `GroceryItem`.
