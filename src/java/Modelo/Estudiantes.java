package Modelo;

public class Estudiantes {
    int expediente;
    String fecha_nacimiento;
    String direccion;
    String sexo;
    String dui;
    int id_carrera;
    int idusuario;
    
    public Estudiantes() {
    }

    public Estudiantes(int expediente, String fecha_nacimiento, String direccion, String sexo, String dui, int id_carrera, int idusuario) {
        this.expediente = expediente;
        this.fecha_nacimiento = fecha_nacimiento;
        this.direccion = direccion;
        this.sexo = sexo;
        this.dui = dui;
        this.id_carrera = id_carrera;
        this.idusuario = idusuario;
    }
     
    public int getExpediente() {
        return expediente;
    }

    public void setExpediente(int expediente) {
        this.expediente = expediente;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
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

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }
    
    public int getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(int id_carrera) {
        this.id_carrera = id_carrera;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }
}
