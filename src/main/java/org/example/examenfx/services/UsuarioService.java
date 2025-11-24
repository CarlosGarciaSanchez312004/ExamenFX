package org.example.examenfx.services;

import org.example.examenfx.models.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class UsuarioService {
    private ArrayList<Usuario> usuarios;
    private int contadorId = 1;

    public UsuarioService() {
        usuarios = new ArrayList<>();
        usuarios.add(new Usuario(contadorId++, "iker@gmail.com", "nintendo", "no", "2.0", LocalDateTime.now().toString()));
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void añadirUsuario(String correo, String plataforma, boolean esAdmin, String version) {
        String rol = esAdmin ? "si" : "no";
        String fecha = LocalDateTime.now().toString();

        Usuario usuario = new Usuario(contadorId++, correo, plataforma, rol, version, fecha);
        usuarios.add(usuario);
    }

    public void vaciarTabla() {
        usuarios.clear();
        contadorId = 1;
    }
}