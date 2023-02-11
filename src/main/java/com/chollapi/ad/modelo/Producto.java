package com.chollapi.ad.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "Producto")
public class Producto implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idProducto")
    @Expose
    private Long idProducto;

    @Column(name="nombre")
    @Expose
    private String nombre;

    @Column(name = "caracteristicas")
    @Expose
    private String caracteristicas;

    @Nullable
    @Column(name="idFabricante")
    @Expose
    private Long idFabricante;

    @ManyToMany(mappedBy = "productos", cascade = CascadeType.ALL)
    private List<Oferta> ofertas = new ArrayList<>();
    @JsonIgnore

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;

    public Producto(String nombre, String caracteristicas, Long idFabricante) {
        this.nombre = nombre;
        this.caracteristicas = caracteristicas;
        this.idFabricante = idFabricante;
    }

    public void addOferta(Oferta o) {
        ofertas.add(o);
        o.getProductos().add(this);
    }

    public void removeOferta(Oferta o) {
        ofertas.remove(o);
        o.getProductos().remove(this);
    }



}
