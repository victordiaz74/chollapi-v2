package com.chollapi.ad.service.impl;

import com.chollapi.ad.dto.ProductoDto;
import com.chollapi.ad.modelo.Categoria;
import com.chollapi.ad.repositorio.CategoriaRepository;
import com.chollapi.ad.repositorio.ProductoRepository;
import jakarta.transaction.Transactional;
import com.chollapi.ad.modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import com.chollapi.ad.service.ProductoService;

import java.util.ArrayList;
import java.util.List;
@Transactional
@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Producto obtenerProductoID(Long id) {

        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto crearProducto(Producto producto, Long idCategoria) {
        Categoria categoria = categoriaRepository.findById(idCategoria).get();
        categoria.addProducto(producto);
        return productoRepository.save(producto);
    }

    @Override
    public Producto modificarProducto(Producto producto, Long idCategoria) {

        Categoria categoria = categoriaRepository.findById(idCategoria).get();
        producto.setCategoria(categoria);
        return productoRepository.save(producto);
    }

    @Override
    public Boolean eliminarProducto(Long id) {

        productoRepository.deleteById(id);
        return !productoRepository.existsById(id);
    }

    @Override
    public List<Producto> buscarProducto(String nombre, String caracteristicas) {
        return productoRepository.buscarProducto(nombre, caracteristicas);
    }

    @Override
    public List<Producto> ultimos5(Long idCategoria) {
        return productoRepository.ultimos5(idCategoria);
    }

    @Override
    public List<ProductoDto> listar5Pag(Pageable pageable) {
        Page<Producto> productos = productoRepository.findAll(pageable);

        List<ProductoDto> productoDtos = new ArrayList<>();

        for(Producto pro: productos) {
            productoDtos.add(new ProductoDto(pro.getIdProducto(), pro.getNombre(), pro.getCaracteristicas(), pro.getIdFabricante(), pro.getCategoria().getIdCategoria()));
        }

        return productoDtos;
    }
}
