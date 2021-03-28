package Interfaces;
import Modelo.Grupos;
import java.util.*;

public interface CRUDGrupos {
    public ArrayList listar(int numPagina, int numRegPagina);
    public List listar2();
    public Grupos list(int id_grupo);
    public boolean add(Grupos gru);
    public boolean edit(Grupos gru);
    public boolean eliminar(int id);      
    public int getTodos();     
}
