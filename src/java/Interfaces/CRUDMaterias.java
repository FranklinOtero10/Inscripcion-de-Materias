package Interfaces;
import Modelo.Materias;
import java.util.ArrayList;
import java.util.List;

public interface CRUDMaterias {
    public ArrayList listar(int numPagina, int numRegPagina);
    public List listar2();
    public List listar3(String ciclos);
    public Materias list(int codigo_materia);
    public boolean add(Materias mat);
    public boolean edit(Materias mat);
    public boolean eliminar(int codigo);
    public int getTodos();
}
