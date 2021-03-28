package Modelo;

public class Administradores {
    int id_administrador;
    String telefono;
    String email;
    String direccion;
    String sexo;
    String cargo;
    int idusuario;
    
    public Administradores() {
    }

    public Administradores(int id_administrador, String telefono, String email, String direccion, String sexo, String cargo, int idusuario) {
        this.id_administrador = id_administrador;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.sexo = sexo;
        this.cargo = cargo;
        this.idusuario = idusuario;
    }
     
    public int getId_administrador() {
        return id_administrador;
    }

    public void setId_administrador(int id_administrador) {
        this.id_administrador = id_administrador;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }  
    
    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }
}
