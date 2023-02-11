package com.chollapi.ad.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "Oferta")
public class Oferta implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOferta")
    @Expose
    private Long idOferta;

    @Column(name="url")
    @Expose
    private String url;

    @Temporal(TemporalType.DATE)
    @Column(name="fechaHoraPublicacion")
    @Expose
    private Date fechaPublicacion;

    @Column(name="precio")
    @Expose
    private Float precio;

    @Column(name="disponible")
    @Expose
    private Boolean disponible;

    @ManyToMany
    @JoinTable(
            name = "oferta_producto",
            joinColumns = {@JoinColumn(name = "idOferta")},
            inverseJoinColumns = {@JoinColumn(name = "idProducto")}
    )
    private List<Producto> productos = new ArrayList<>();

    public Oferta(){}

    public Oferta(String url, Date fechaPublicacion, Float precio, Boolean disponible) {
        this.url = url;
        this.fechaPublicacion = fechaPublicacion;
        this.precio = precio;
        this.disponible = disponible;
    }

    public void addProducto(Producto p) {
        productos.add( p );
        p.getOfertas().add( this );
    }

    public void removeProducto(Producto p) {
        productos.remove( p );
        p.getOfertas().remove( this );
    }

}
