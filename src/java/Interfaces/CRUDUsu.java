package Interfaces;
import Modelo.Usuarios;
import java.util.*;

public interface CRUDUsu {
    public ArrayList listar(int numPagina, int numRegPagina);
    public List listar2();
    public Usuarios list(int id);
    public boolean add(Usuarios usu);
    public boolean edit(Usuarios usu);
    public boolean eliminar(int id);  
    public int getTodos();
}
