package Interfaces;
import Modelo.Inscripciones;
import java.util.*;

public interface CRUDInscripciones {
    public ArrayList listar(int numPagina, int numRegPagina);
    public List listar2();
    public List listar3(String anio, String ciclo, int expediente);
    public int listar4(String anio, String ciclo, int expediente);
    public boolean add(Inscripciones in);
    public boolean edit(Inscripciones in);
    public boolean eliminar(int id_iscripcion);
    public int getTodos();
    public int consultaciclo(String anio, String ciclo, int expediente);
//    public int consultaanio(String anio, String ciclo, int expediente);
}
