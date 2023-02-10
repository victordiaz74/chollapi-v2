package com.chollapi.ad.controlador;

import com.chollapi.ad.modelo.Categoria;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.chollapi.ad.service.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    public String toJson(Object object) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        return gson.toJson(object);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> obtenerCategoriaID(@RequestParam(value = "idCategoria", defaultValue = "1") Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson( categoriaService.obtenerCategoriaID(id)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> crearCategoria(@RequestBody Categoria categoria){
        //categoriaService.crearCategoria(categoria);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson( categoriaService.crearCategoria(categoria)));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> modificarCategoria(@RequestBody Categoria categoria){
        //categoriaService.modificarCategoria(categoria);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson( categoriaService.modificarCategoria(categoria)));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<String> eliminarCategoria(@RequestParam(value = "idCategoria", defaultValue = "0") Long id){
        //categoriaService.eliminarCategoria(id);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson(categoriaService.eliminarCategoria(id)));
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/ultimos5productos"})
    public ResponseEntity<String> ultimos5(@RequestParam(value = "id", defaultValue = "0") Long idCategoria){
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson(categoriaService.ultimos5(idCategoria)));
    }
}
