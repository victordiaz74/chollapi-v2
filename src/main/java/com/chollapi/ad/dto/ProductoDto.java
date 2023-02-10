package com.chollapi.ad.dto;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
public class ProductoDto implements Serializable {

    @Expose
    public Long idProducto;
    @Expose
    public String nombre;
    @Expose
    public String caracteristicas;
    @Expose
    public Long idFabricante;
    @Expose
    public Long idCategoria;

}
