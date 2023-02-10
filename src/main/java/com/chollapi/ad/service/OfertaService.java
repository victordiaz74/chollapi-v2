package com.chollapi.ad.service;

import com.chollapi.ad.dto.OfertaDto;
import com.chollapi.ad.modelo.Oferta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OfertaService {

    Oferta obtenerOfertaID(Long id);

    Oferta crearOferta(Oferta oferta, Long idProducto);

    Oferta modificarOferta(Oferta oferta);

    Boolean eliminarOferta(Long id);

    List<Oferta> ultimas5(Long idProducto);

    List<OfertaDto> ultimasOfertas(Pageable pageable);

    List<Oferta> ultimas5Categoria(Long idCategoria);
}
