package Modelo;

public class Materias {
    private int codigo_materia;
    private String materia;
    private String uv;
    private String prerequisito;
    private int id_ciclo;
    
    public Materias(){}

    public Materias(int codigo_materia, String materia, String uv, String prerequisito, int id_ciclo) {
        this.codigo_materia = codigo_materia;
        this.materia = materia;
        this.uv = uv;
        this.prerequisito = prerequisito;
        this.id_ciclo = id_ciclo;
    }
    
    public int getCodigo_materia() {
        return codigo_materia;
    }

    public void setCodigo_materia(int codigo_materia) {
        this.codigo_materia = codigo_materia;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getUv() {
        return uv;
    }

    public void setUv(String uv) {
        this.uv = uv;
    }

    public String getPrerequisito() {
        return prerequisito;
    }

    public void setPrerequisito(String prerequisito) {
        this.prerequisito = prerequisito;
    }

    public int getId_ciclo() {
        return id_ciclo;
    }

    public void setId_ciclo(int id_ciclo) {
        this.id_ciclo = id_ciclo;
    }
}
