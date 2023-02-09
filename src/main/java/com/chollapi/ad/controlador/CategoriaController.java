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

    @RequestMapping(method = RequestMethod.GET, value = {"/obtenerCategoriaID/{id}"})
    public ResponseEntity<String> obtenerCategoriaID(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson( categoriaService.obtenerCategoriaID(id)));
    }

    @RequestMapping(method = RequestMethod.POST, value = {"/crearCategoria"})
    public ResponseEntity<String> crearCategoria(@RequestBody Categoria categoria){
        //categoriaService.crearCategoria(categoria);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson( categoriaService.crearCategoria(categoria)));
    }

    @RequestMapping(method = RequestMethod.PUT, value = {"/modificarCategoria"})
    public ResponseEntity<String> modificarCategoria(@RequestBody Categoria categoria){
        //categoriaService.modificarCategoria(categoria);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson( categoriaService.modificarCategoria(categoria)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = {"/eliminarCategoria/{id}"})
    public ResponseEntity<String> eliminarCategoria(@PathVariable Long id){
        //categoriaService.eliminarCategoria(id);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson(categoriaService.eliminarCategoria(id)));
    }


}
