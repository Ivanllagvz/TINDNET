package com.example.tindnet.ui.Databases;

public class Empresa {
    private String nombre;
    private String descripcion;
    private String web;
    private String sector;
    private String razonSocial;
    private String horarios;
    private String imagenUri;

    public Empresa(String nombre, String descripcion, String web, String sector, String razonSocial, String horarios, String imagenUri) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.web = web;
        this.sector = sector;
        this.razonSocial = razonSocial;
        this.horarios = horarios;
        this.imagenUri = imagenUri;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
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

    public String getImagenUri() {
        return imagenUri;
    }
}