package com.javareact.javabackend.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "partidos")
public class PartidoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "usuario")
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "local")
    private EquipoEntity local;

    @ManyToOne
    @JoinColumn(name = "visitante")
    private EquipoEntity visitante;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private short golesLocal;

    @Column(nullable = false)
    private short golesVisitante;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UsuarioEntity getUsuario() {
        return this.usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public EquipoEntity getLocal() {
        return this.local;
    }

    public void setLocal(EquipoEntity local) {
        this.local = local;
    }

    public EquipoEntity getVisitante() {
        return this.visitante;
    }

    public void setVisitante(EquipoEntity visitante) {
        this.visitante = visitante;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public short getGolesLocal() {
        return this.golesLocal;
    }

    public void setGolesLocal(short golesLocal) {
        this.golesLocal = golesLocal;
    }

    public short getGolesVisitante() {
        return this.golesVisitante;
    }

    public void setGolesVisitante(short golesVisitante) {
        this.golesVisitante = golesVisitante;
    }
    
}
