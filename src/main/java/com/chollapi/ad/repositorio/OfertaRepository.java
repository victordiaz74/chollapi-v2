package com.chollapi.ad.repositorio;

import com.chollapi.ad.modelo.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Long> {

    @Query(value = "SELECT o FROM Oferta AS o JOIN o.productos AS p where p.idProducto = :idProducto ORDER BY o.fechaPublicacion DESC")
    List<Oferta> ultimas5(@Param("idProducto") Long idProducto);

    @Query(value = "SELECT o FROM Oferta as o JOIN o.productos as p JOIN p.categoria c WHERE c.idCategoria = :idCategoria ORDER BY o.fechaPublicacion DESC")
    List<Oferta> ultimas5Categoria(@Param("idCategoria") Long idCategoria);

    @Query(value = "SELECT o FROM Oferta as o JOIN o.productos as p where p.idProducto = :idProducto ORDER BY o.precio DESC")
    List<Oferta> mejores10(@Param("idProducto") Long idProducto);

}
