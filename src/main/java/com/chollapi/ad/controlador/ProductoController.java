package com.chollapi.ad.controlador;

import com.chollapi.ad.dto.ProductoDto;
import com.chollapi.ad.modelo.Producto;
import com.chollapi.ad.service.CategoriaService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.chollapi.ad.service.ProductoService;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    public String toJson(Object object) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        return gson.toJson(object);
    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> obtenerProductoID(@RequestParam(value = "idProducto", defaultValue = "0") Long id){

        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson(productoService.obtenerProductoID(id)));
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> crearProducto(@RequestBody ProductoDto productoDto){
        //productoService.crearProducto(producto);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson(productoService.crearProducto(productoDto)));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> modificarProducto(@RequestBody ProductoDto productoDto){
        //productoService.modificarProducto(producto);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson(productoService.modificarProducto(productoDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<String> eliminarProducto(@RequestParam(value = "idProducto", defaultValue = "0") Long id){
        //productoService.eliminarProducto(id);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson(productoService.eliminarProducto(id)));
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/list"}, params = {"page", "count"})
    public ResponseEntity<String> listar5Pag(@RequestParam(name ="count", defaultValue = "5") int count, @RequestParam(name = "page", defaultValue = "0") int page){
        Pageable pageable = PageRequest.of(page, count);

        List<ProductoDto> productos = productoService.listar5Pag(pageable);

        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson(productos));
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/mejores10oferta"})
    public ResponseEntity<String> mejores10(@RequestParam(value = "id", defaultValue = "0") Long id){
        //productoService.obtenerProductoID(id);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson(productoService.mejores10(id)));
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/buscar"})
    public ResponseEntity<String> buscarProducto(@RequestParam (value = "texto", defaultValue = "0") String texto){
        //productoService.obtenerProductoID(id);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson(productoService.buscarProducto(texto)));
    }

}
