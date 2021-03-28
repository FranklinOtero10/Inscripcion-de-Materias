package Modelo;

public class Grupos {
    int id_grupo;
    String grupo;
    String horario;
    String aula;

    public Grupos() {
    }

    public Grupos(int id_grupo, String grupo) {
        this.id_grupo = id_grupo;
        this.grupo = grupo;
    }
    
    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }
    
    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    } 
}
