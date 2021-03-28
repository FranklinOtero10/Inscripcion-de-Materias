package Interfaces;
import Modelo.Carreras;
import java.util.ArrayList;
import java.util.List;

public interface CRUDCarreras {
    public ArrayList listar(int numPagina, int numRegPagina);
    public List listar2();
    public boolean add(Carreras carear);
    public boolean edit(Carreras carear);
    public boolean eliminar(int idcarrera);
    public int getTodos();
}
