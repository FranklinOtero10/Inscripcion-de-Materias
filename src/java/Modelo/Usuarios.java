package Modelo;
import java.io.InputStream;

public class Usuarios {
    int id;
    String codigo;
    String contrasena;
    int nivel;
    String nombres;
    String apellidos;
    InputStream foto;

    public Usuarios() {
    }

    public Usuarios(int id, String codigo, String contrasena, int nivel, String nombres, String apellidos, InputStream foto) {
        this.id = id;
        this.codigo = codigo;
        this.contrasena = contrasena;
        this.nivel = nivel;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public InputStream getFoto() {
        return foto;
    }

    public void setFoto(InputStream foto) {
        this.foto = foto;
    }
}
