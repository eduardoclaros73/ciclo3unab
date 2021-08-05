package com.javareact.javabackend.models.requests;

public class UsuarioLoginRequestModel {
    
    private String correo;
    private String password;

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
