package co.com.Serrato.CarlosSerratoApiMongo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.Serrato.CarlosSerratoApiMongo.Model.GroceryItem;
import co.com.Serrato.CarlosSerratoApiMongo.Service.ItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("item")
@Tag(name = "Item", description = "Operaciones relacionadas con los ítems del inventario.")
public class ItemController {
    
    @Autowired
    private ItemService itemService;

    @Operation(
        summary = "Obtener todos los ítems",
        description = "Recupera una lista de todos los ítems almacenados.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Lista de ítems obtenida exitosamente."
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Error interno del servidor."
            )
        }
    )
    @GetMapping("/getAll")
    public String getAll() {
        return itemService.getAll();
    }

    @Operation(
        summary = "Obtener un ítem por ID",
        description = "Recupera un ítem específico basado en su ID.",
        parameters = {
            @io.swagger.v3.oas.annotations.Parameter(
                name = "id",
                description = "ID del ítem que se desea recuperar.",
                required = true
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Ítem recuperado exitosamente."
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Ítem no encontrado."
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Error interno del servidor."
            )
        }
    )
    @GetMapping("/get/{id}")
    public String getAny(@PathVariable("id") String id) {
        return itemService.getAny(id);
    }

    @Operation(
        summary = "Insertar un nuevo ítem",
        description = "Crea un nuevo ítem en el sistema.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto `GroceryItem` que se desea insertar.",
            content = @Content(
                schema = @Schema(implementation = GroceryItem.class)
            ),
            required = true
        ),
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "Ítem creado exitosamente."
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Solicitud inválida."
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Error interno del servidor."
            )
        }
    )
    @PostMapping("/insert")
    public String insert(@RequestBody GroceryItem groceryItem) {
        return itemService.insert(groceryItem);
    }

    @Operation(
        summary = "Actualizar un ítem completo",
        description = "Actualiza completamente un ítem existente.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto `GroceryItem` con la información actualizada.",
            content = @Content(
                schema = @Schema(implementation = GroceryItem.class)
            ),
            required = true
        ),
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Ítem actualizado exitosamente."
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Solicitud inválida."
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Ítem no encontrado."
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Error interno del servidor."
            )
        }
    )
    @PutMapping("/update")
    public String update(@RequestBody GroceryItem groceryItem) {
        return itemService.update(groceryItem);
    }

    @Operation(
        summary = "Eliminar un ítem por ID",
        description = "Elimina un ítem específico basado en su ID.",
        parameters = {
            @io.swagger.v3.oas.annotations.Parameter(
                name = "id",
                description = "ID del ítem que se desea eliminar.",
                required = true
            )
        },
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Ítem eliminado exitosamente."
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Ítem no encontrado."
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Error interno del servidor."
            )
        }
    )
    @DeleteMapping("/delete/{id}")
    public String deleteItem(@PathVariable("id") String id) {
        return itemService.delete(id);
    }

    @Operation(
        summary = "Actualizar parcialmente un ítem",
        description = "Actualiza parcialmente un ítem existente utilizando solo los campos proporcionados.",
        parameters = {
            @io.swagger.v3.oas.annotations.Parameter(
                name = "id",
                description = "ID del ítem que se desea actualizar.",
                required = true
            )
        },
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto `GroceryItem` con los campos a actualizar.",
            content = @Content(
                schema = @Schema(implementation = GroceryItem.class)
            ),
            required = true
        ),
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Ítem actualizado parcialmente exitosamente."
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Solicitud inválida."
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Ítem no encontrado."
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Error interno del servidor."
            )
        }
    )
    @PatchMapping("/updateData/{id}")
    public String updateData(@PathVariable String id, @RequestBody GroceryItem groceryItem) {
        return itemService.updateData(id, groceryItem);
    }

    @Operation(
        summary = "Verificar disponibilidad de ítems",
        description = "Verifica si hay ítems disponibles en el sistema.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Solicitud procesada correctamente."
            ),
            @ApiResponse(
                responseCode = "500",
                description = "Error interno del servidor."
            )
        }
    )
    @RequestMapping(value = "/getAll", method = RequestMethod.HEAD)
    public ResponseEntity<?> handleHeadRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}