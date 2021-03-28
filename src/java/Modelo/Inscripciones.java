package Modelo;

public class Inscripciones {
    int id_inscripcion;
    int id_grupo;
    int id_ciclo;
    int codigo_materia;
    int expediente;
    String anio;

    public Inscripciones(int id_inscripcion, int id_grupo, int id_ciclo, int codigo_materia, int expediente, String anio) {
        this.id_inscripcion = id_inscripcion;
        this.id_grupo = id_grupo;
        this.id_ciclo = id_ciclo;
        this.codigo_materia = codigo_materia;
        this.expediente = expediente;
        this.anio = anio;
    }
    
    public Inscripciones() {
    }
    
    public int getId_inscripcion() {
        return id_inscripcion;
    }

    public void setId_inscripcion(int id_inscripcion) {
        this.id_inscripcion = id_inscripcion;
    }

    public int getCodigo_materia() {
        return codigo_materia;
    }

    public void setCodigo_materia(int codigo_materia) {
        this.codigo_materia = codigo_materia;
    }

    public int getExpediente() {
        return expediente;
    }

    public void setExpediente(int expediente) {
        this.expediente = expediente;
    }

    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    } 

    public int getId_ciclo() {
        return id_ciclo;
    }

    public void setId_ciclo(int id_ciclo) {
        this.id_ciclo = id_ciclo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }  
}