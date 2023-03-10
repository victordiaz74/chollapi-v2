package com.chollapi.ad.service.impl;

import com.chollapi.ad.dto.ProductoDto;
import com.chollapi.ad.modelo.Categoria;
import com.chollapi.ad.modelo.Oferta;
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
    public Producto crearProducto(ProductoDto productoDto) {
        Categoria categoria = categoriaRepository.findById(productoDto.getIdCategoria()).get();
        Producto producto = new Producto(productoDto.getNombre(), productoDto.getCaracteristicas(), productoDto.getIdFabricante());
        producto.setCategoria(categoria);
        categoria.addProducto(producto);
        return productoRepository.save(producto);
    }

    @Override
    public Producto modificarProducto(ProductoDto productoDto) {

        Categoria categoria = categoriaRepository.findById(productoDto.getIdCategoria()).get();
        Producto producto = new Producto(productoDto.getNombre(), productoDto.getCaracteristicas(), productoDto.getIdFabricante());
        producto.setCategoria(categoria);
        return productoRepository.save(producto);
    }

    @Override
    public Boolean eliminarProducto(Long id) {

        productoRepository.deleteById(id);
        return !productoRepository.existsById(id);
    }

    @Override
    public List<Producto> buscarProducto(String texto) {
        return productoRepository.buscarProducto(texto);
    }

    @Override
    public List<Oferta> mejores10(Long idProducto){
        List<Oferta> ofertas = productoRepository.mejores10(idProducto);
        return ofertas;
    }

    @Override
    public List<ProductoDto> listar5Pag(Pageable pageable) {
        Page<Producto> productos = productoRepository.findAll(pageable);

        List<ProductoDto> productoDtos = new ArrayList<>();

        for(Producto pro: productos) {
            productoDtos.add(new ProductoDto(pro.getIdProducto(), pro.getNombre(), pro.getCaracteristicas(), pro.getIdFabricante()));
        }

        return productoDtos;
    }
}
