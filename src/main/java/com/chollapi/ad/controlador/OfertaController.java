package com.chollapi.ad.controlador;

import com.chollapi.ad.dto.OfertaDto;
import com.chollapi.ad.modelo.Oferta;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.chollapi.ad.service.OfertaService;

import java.util.List;

//http://localhost:8080/oferta/list
@RestController
@RequestMapping("/oferta")
public class OfertaController {
    @Autowired
    private OfertaService ofertaService;

    public String toJson(Object object) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        return gson.toJson(object);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> obtenerOfertaID(@RequestParam(value = "idOferta", defaultValue = "0") Long id){
        //ofertaService.obtenerOfertaID(id);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson(ofertaService.obtenerOfertaID(id)));
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> crearOferta(@RequestBody OfertaDto ofertaDto){
        //ofertaService.crearOferta(oferta);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson(ofertaService.crearOferta(ofertaDto)));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> modificarOferta(@RequestBody Oferta oferta){
        //ofertaService.modificarOferta(oferta);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson(ofertaService.modificarOferta(oferta)));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> eliminarOferta(@RequestParam(value = "idOferta", defaultValue = "0") Long id){
        //ofertaService.eliminarOferta(id);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(ofertaService.eliminarOferta(id));
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/ultimas5ofertas"})
    public ResponseEntity<String> ultimas5(@RequestParam(value = "idProducto", defaultValue = "0") Long idProducto){
        //ofertaService.ultimas5(idProducto);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson(ofertaService.ultimas5(idProducto)));
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/ultimas5"})
    public ResponseEntity<String> ultimas5Categoria(@RequestParam(value = "idCategoria", defaultValue = "0") Long idCategoria){
        //ofertaService.ultimas5(idProducto);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson(ofertaService.ultimas5Categoria(idCategoria)));
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/list"}, params = {"page", "count"})
    public ResponseEntity<String> ultimasOfertas(@RequestParam(name ="count", defaultValue = "5") int count,
                                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                                 @SortDefault(sort = "fechaPublicacion", direction = Sort.Direction.DESC) Sort sort){
        Pageable pageable = PageRequest.of(page, count, sort);

        List<OfertaDto> ofertas = ofertaService.ultimasOfertas(pageable);

        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson(ofertas));
    }



}
