package Interfaces;
import Modelo.Administradores;
import java.util.*;

public interface CRUDAdm {
    public ArrayList listar(int numPagina, int numRegPagina);
    public boolean add(Administradores adm);
    public boolean edit(Administradores adm);
    public boolean eliminar(int id);
    public int getTodos();
}
