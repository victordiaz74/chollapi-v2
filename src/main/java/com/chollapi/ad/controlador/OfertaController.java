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

    @RequestMapping(method = RequestMethod.GET, value = {"/obtenerOfertaID/{id}"})
    public ResponseEntity<String> obtenerOfertaID(@PathVariable Long id){
        //ofertaService.obtenerOfertaID(id);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson(ofertaService.obtenerOfertaID(id)));
    }
    @RequestMapping(method = RequestMethod.POST, value = {"/crearOferta"})
    public ResponseEntity<String> crearOferta(@RequestBody Oferta oferta, @RequestParam (name = "idProducto") Long idProducto){
        //ofertaService.crearOferta(oferta);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson(ofertaService.crearOferta(oferta, idProducto)));
    }

    @RequestMapping(method = RequestMethod.PUT, value = {"/modificarOferta"})
    public ResponseEntity<String> modificarOferta(@RequestBody Oferta oferta){
        //ofertaService.modificarOferta(oferta);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson(ofertaService.modificarOferta(oferta)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = {"/eliminarOferta/{id}"})
    public ResponseEntity<Boolean> eliminarOferta(@PathVariable Long id){
        //ofertaService.eliminarOferta(id);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/boolean")
                .body(ofertaService.eliminarOferta(id));
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/ultimas5/{id}"})
    public ResponseEntity<String> ultimas5(@PathVariable Long id){
        //ofertaService.ultimas5(idProducto);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson(ofertaService.ultimas5(id)));
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/ultimas5Categoria/{idCategoria}"})
    public ResponseEntity<String> ultimas5Categoria(@PathVariable Long idCategoria){
        //ofertaService.ultimas5(idProducto);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson(ofertaService.ultimas5Categoria(idCategoria)));
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/list"})
    public ResponseEntity<String> ultimasOfertas(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "5") int size,
                                                 @SortDefault(sort = "fechaPublicacion", direction = Sort.Direction.DESC) Sort sort){
        Pageable pageable = PageRequest.of(page, size, sort);

        List<OfertaDto> ofertas = ofertaService.ultimasOfertas(pageable);

        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson(ofertas));
    }

    @RequestMapping(method = RequestMethod.GET, value = {"/mejores10oferta/{id}"})
    public ResponseEntity<String> mejores10(@PathVariable Long id){
        //productoService.obtenerProductoID(id);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(toJson(ofertaService.mejores10(id)));
    }

}
