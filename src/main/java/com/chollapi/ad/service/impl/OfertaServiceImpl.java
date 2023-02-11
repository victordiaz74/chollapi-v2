package com.chollapi.ad.service.impl;


import com.chollapi.ad.dto.OfertaDto;
import com.chollapi.ad.modelo.Producto;
import com.chollapi.ad.repositorio.OfertaRepository;
import com.chollapi.ad.modelo.Oferta;
import com.chollapi.ad.repositorio.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import com.chollapi.ad.service.OfertaService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class OfertaServiceImpl implements OfertaService {

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Oferta obtenerOfertaID(Long id) {
        return ofertaRepository.findById(id).orElse(null);
    }

    @Override
    public Oferta crearOferta(OfertaDto ofertaDto) {
        Producto producto = productoRepository.findById(ofertaDto.idProducto).get();
        if(existeOferta(ofertaDto)){
            return null;
        }

        Oferta oferta = new Oferta(ofertaDto.getUrl(), ofertaDto.getFechaPublicacion(), ofertaDto.getPrecio(), ofertaDto.getDisponible());
        oferta.addProducto(producto);

        return ofertaRepository.save(oferta);
    }

    private boolean existeOferta(OfertaDto ofertaDto) {

        for(Oferta of: ofertaRepository.findAll()) {
            Date fechaOferta = of.getFechaPublicacion();
            String url = of.getUrl();
            Float precio = of.getPrecio();
            Boolean disponible = of.getDisponible();

            if(fechaOferta.equals(ofertaDto.getFechaPublicacion())) {
                if(url.equals(ofertaDto.getUrl())) {
                    if(precio.equals(ofertaDto.getPrecio())){
                        if(disponible.equals(ofertaDto.getDisponible())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public Oferta modificarOferta(Oferta oferta) {
        return ofertaRepository.save(oferta);
    }

    @Override
    public Boolean eliminarOferta(Long id) {
        ofertaRepository.deleteById(id);
        return !ofertaRepository.existsById(id);
    }


    @Override
    public List<Oferta> ultimas5(Long idProducto) {
        return ofertaRepository.ultimas5(idProducto);
    }

    @Override
    public List<OfertaDto> ultimasOfertas(Pageable pageable) {
        Page<Oferta> ofertas = ofertaRepository.findAll(pageable);

        List<OfertaDto> ofertasDtos = new ArrayList<>();

        for (Oferta o: ofertas){
            ofertasDtos.add(new OfertaDto(o.getIdOferta(), o.getUrl(), o.getFechaPublicacion(), o.getPrecio(), o.getDisponible()));
        }

        return ofertasDtos;
    }

    @Override
    public List<Oferta> ultimas5Categoria(@Param("idCategoria") Long idCategoria) {
        return ofertaRepository.ultimas5Categoria(idCategoria);
    }
}


