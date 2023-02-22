package com.chollapi.ad.dto;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OfertaDto implements Serializable {
    @Expose
    public Long idOferta;
    @Expose
    public String url;
    @Expose
    public Date fechaPublicacion;
    @Expose
    public Float precio;
    @Expose
    public Boolean disponible;

    @Expose
    public Long idProducto;

    public OfertaDto() {
        // constructor vacío
    }
    public OfertaDto(Long idOferta, String url, Date fechaPublicacion, Float precio, Boolean disponible) {
        this.idOferta = idOferta;
        this.url = url;
        this.fechaPublicacion = fechaPublicacion;
        this.precio = precio;
        this.disponible = disponible;
    }
}
