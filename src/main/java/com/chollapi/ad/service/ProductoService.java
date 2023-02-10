package com.chollapi.ad.service;

import com.chollapi.ad.dto.ProductoDto;
import com.chollapi.ad.modelo.Oferta;
import com.chollapi.ad.modelo.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductoService{

    Producto obtenerProductoID(Long id);

    Producto crearProducto(Producto producto, Long idCategoria);

    Producto modificarProducto(Producto producto, Long idCategoria);

    Boolean eliminarProducto(Long id);

    List<Producto> buscarProducto(String texto);

    List<ProductoDto> listar5Pag(Pageable pageable);

    List<Oferta> mejores10(Long id);
}