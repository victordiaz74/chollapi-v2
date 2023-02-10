package com.chollapi.ad.service.impl;

import com.chollapi.ad.modelo.Producto;
import com.chollapi.ad.repositorio.CategoriaRepository;
import com.chollapi.ad.repositorio.ProductoRepository;
import jakarta.transaction.Transactional;
import com.chollapi.ad.modelo.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chollapi.ad.service.CategoriaService;

import java.util.List;

@Transactional
@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Categoria obtenerCategoriaID(Long id) {

        return categoriaRepository.findById(id).orElse(null);
    }

    @Override
    public Categoria crearCategoria(Categoria categoria) {

        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria modificarCategoria(Categoria categoria) {

        return categoriaRepository.save(categoria);
    }

    @Override
    public Boolean eliminarCategoria(Long id) {
        Categoria categoria = categoriaRepository.findById(id).get();
        List<Producto> productosCat = categoria.getProductos();

        for(Producto p : productosCat){
            if(p.getCategoria().getIdCategoria().equals(id)){
                p.setCategoria(categoriaRepository.getOne(1L));
                productoRepository.save(p);
            }
        }
        categoriaRepository.delete(categoria);
        return !categoriaRepository.existsById(id);
    }

    @Override
    public List<Producto> ultimos5(Long idCategoria) {
        return categoriaRepository.ultimos5(idCategoria);
    }

}
