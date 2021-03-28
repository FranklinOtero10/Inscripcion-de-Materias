package Interfaces;
import Modelo.Ciclos;
import java.util.ArrayList;
import java.util.List;

public interface CRUDCiclos {
    public ArrayList listar(int numPagina, int numRegPagina);
    public List listar2();
    public boolean add(Ciclos ciclo);
    public boolean edit(Ciclos ciclo);
    public boolean eliminar(int idciclo);
    public int getTodos();   
}
