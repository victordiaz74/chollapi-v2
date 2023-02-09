package com.chollapi.ad.repositorio;

import com.chollapi.ad.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query(value = "SELECT p FROM Producto p WHERE p.nombre LIKE %:nombre% AND p.caracteristicas LIKE %:caracteristicas% ", nativeQuery = true)
    List<Producto> buscarProducto(@Param("nombre") String nombre,@Param("caracteristicas") String caracteristicas);

    @Query(value = "SELECT p FROM Producto p JOIN p.categoria c WHERE c.idCategoria = :idCategoria")
    List<Producto> ultimos5(@Param("idCategoria") Long idCategoria);
}
