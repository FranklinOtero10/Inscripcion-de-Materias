package Modelo;

public class Ciclos {
    int id_ciclo;
    String ciclo;

    public Ciclos() {
    }

    public Ciclos(int id_ciclo, String ciclo) {
        this.id_ciclo = id_ciclo;
        this.ciclo = ciclo;
    }

    public int getId_ciclo() {
        return id_ciclo;
    }

    public void setId_ciclo(int id_ciclo) {
        this.id_ciclo = id_ciclo;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }      
}
