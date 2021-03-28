package Modelo;

public class Carreras {
    private int id_carrera;
    private String codigo_carrera;
    private String carrera;
    
    public Carreras(){
    }
    
    public Carreras(int id_carrera, String codigo_carrera, String carrera){
        this.id_carrera = id_carrera;
        this.codigo_carrera = codigo_carrera;
        this.carrera = carrera;
    }

    public int getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(int id_carrera) {
        this.id_carrera = id_carrera;
    }

    public String getCodigo_carrera() {
        return codigo_carrera;
    }

    public void setCodigo_carrera(String codigo_carrera) {
        this.codigo_carrera = codigo_carrera;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }      
}
