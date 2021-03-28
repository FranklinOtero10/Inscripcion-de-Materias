package Interfaces;
import Modelo.Estudiantes;
import java.util.*;

public interface CRUDEst {
    public ArrayList listar(int numPagina, int numRegPagina);
    public List listar2();
    public Estudiantes  list(int expediente);
    public boolean add(Estudiantes est);
    public boolean edit(Estudiantes est);
    public boolean eliminar(int expediente);
    public int getTodos();
    public Estudiantes listUsuario(int idusuario);
}
