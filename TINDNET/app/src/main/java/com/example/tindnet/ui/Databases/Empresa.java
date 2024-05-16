package com.example.tindnet.ui.Databases;

import java.util.List;

public class Empresa {
    private String nombre;
    private String numero;
    private String email;
    private String password;
    private String descripcion;
    private String telefono;
    private String web;
    private String sector;
    private String razonSocial;
    private String horarios;
    private String documentoUri;
    private List<String> imagenUris;
    private String logoUri;

    public Empresa(String nombre, String numero, String email, String password, String descripcion, String telefono, String web, String sector, String razonSocial, String horarios, String documentoUri, List<String> imagenUris, String logoUri) {
        this.nombre = nombre;
        this.numero = numero;
        this.email = email;
        this.password = password;
        this.descripcion = descripcion;
        this.telefono = telefono;
        this.web = web;
        this.sector = sector;
        this.razonSocial = razonSocial;
        this.horarios = horarios;
        this.documentoUri = documentoUri;
        this.imagenUris = imagenUris;
        this.logoUri = logoUri;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumero() {
        return numero;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getWeb() {
        return web;
    }

    public String getSector() {
        return sector;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getHorarios() {
        return horarios;
    }

    public String getDocumentoUri() {
        return documentoUri;
    }

    public List<String> getImagenUris() {
        return imagenUris;
    }

    public String getLogoUri() {
        return logoUri;
    }
}
