package com.chollapi.ad.repositorio;

import com.chollapi.ad.modelo.Categoria;
import com.chollapi.ad.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query(value = "SELECT p FROM Producto p JOIN p.categoria c WHERE c.idCategoria = :idCategoria")
    List<Producto> ultimos5(@Param("idCategoria") Long idCategoria);
}
