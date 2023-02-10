package com.chollapi.ad.dto;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
public class OfertaDto implements Serializable {
    @Expose
    public Long idOferta;
    @Expose
    public String url;
    @Expose
    public Date fechaPublicacion;
    @Expose
    public Boolean disponible;

}
