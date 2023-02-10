package com.chollapi.ad.service;

import com.chollapi.ad.modelo.Categoria;
import com.chollapi.ad.modelo.Producto;

import java.util.List;

public interface CategoriaService {

    Categoria obtenerCategoriaID(Long id);

    Categoria crearCategoria(Categoria categoria);

    Categoria modificarCategoria(Categoria categoria);

    Boolean eliminarCategoria(Long id);

    List<Producto> ultimos5(Long idCategoria);

}
