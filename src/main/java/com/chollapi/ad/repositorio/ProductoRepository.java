package com.chollapi.ad.repositorio;

import com.chollapi.ad.modelo.Oferta;
import com.chollapi.ad.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query(value = "SELECT p FROM Producto p WHERE p.nombre LIKE %:texto% AND p.caracteristicas LIKE %:texto% ")
    List<Producto> buscarProducto(@Param("texto") String texto);

    @Query(value = "SELECT o FROM Oferta as o JOIN o.productos as p where p.idProducto = :idProducto ORDER BY o.precio DESC")
    List<Oferta> mejores10(@Param("idProducto") Long idProducto);
}
