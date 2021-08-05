package com.javareact.javabackend.models.requests;

import java.util.Date;

public class PartidoUpdateRequestModel {

    private long local;
    private long visitante;
    private Date fecha;
    private short golesLocal;
    private short golesVisitante;

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
