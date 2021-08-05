package com.javareact.javabackend.models.responses;

import java.util.Date;

public class PartidoDetailsResponseModel {

    private long id;
    private long local;
    private long visitante;
    private Date fecha;
    private short golesLocal;
    private short golesVisitante;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLocal() {
        return this.local;
    }

    public void setLocal(long local) {
        this.local = local;
    }

    public long getVisitante() {
        return this.visitante;
    }

    public void setVisitante(long visitante) {
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
