package org.example.examenfx.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private Integer id;
    private String correo;
    private String plataforma;
    private String rol;
    private String version;
    private String fecha;

    public Usuario(String correo, String plataforma, String rol, String version, String fecha) {
        this.correo = correo;
        this.plataforma = plataforma;
        this.rol = rol;
        this.version = version;
        this.fecha = fecha;
    }
}