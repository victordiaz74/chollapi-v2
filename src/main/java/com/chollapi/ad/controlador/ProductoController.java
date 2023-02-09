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
    @Autowired
    private CategoriaService categoriaService;

    public String toJson(Object object) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        return gson.toJson(object);
    }
    @RequestMapping(method = RequestMethod.GET, value = {"/obtenerProductoID/{id}"})
    public ResponseEntity<String> obtenerProductoID(@PathVariable Long id){
        //productoService.obtenerProductoID(id);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson(productoService.obtenerProductoID(id)));
    }
    @RequestMapping(method = RequestMethod.POST, value = {"/crearProducto"})
    public ResponseEntity<String> crearProducto(@RequestBody Producto producto, Long idCategoria){
        //productoService.crearProducto(producto);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson(productoService.crearProducto(producto, idCategoria)));
    }

    @RequestMapping(method = RequestMethod.PUT, value = {"/modificarProducto"})
    public ResponseEntity<String> modificarProducto(@RequestBody Producto producto, @RequestParam Long idCategoria){
        //productoService.modificarProducto(producto);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson(productoService.modificarProducto(producto, idCategoria)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = {"/eliminarProducto/{id}"})
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id){
        //productoService.eliminarProducto(id);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson(productoService.eliminarProducto(id)));
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/buscar"})
    public ResponseEntity<String> buscarProducto(@RequestParam (name = "nombre") String nombre, @RequestParam (name = "caracteristicas") String caracteristicas){
        //productoService.obtenerProductoID(id);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson(productoService.buscarProducto(nombre, caracteristicas)));
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/list"})
    public ResponseEntity<String> listar5Pag(@RequestParam (defaultValue = "0") int page,
                                             @RequestParam (defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page, size);

        List<ProductoDto> productos = productoService.listar5Pag(pageable);

        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson(productos));
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/ultimos5productos/{idCategoria}"})
    public ResponseEntity<String> ultimos5(@PathVariable Long idCategoria){
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson(productoService.ultimos5(idCategoria)));
    }


}
