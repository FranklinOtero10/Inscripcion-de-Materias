package ModeloDAO;
import Config.Conexion;
import Interfaces.CRUDMaterias;
import Modelo.Materias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MateriasDAO implements CRUDMaterias{
    Conexion cn = new Conexion();
    Connection con;     
    Statement st = null;
    PreparedStatement ps;
    ResultSet rs;
    Materias mat = new Materias();

    @Override
    public ArrayList listar(int numPagina, int numRegPagina) {
        ArrayList<Materias> list = new ArrayList<>();
        String sql = "select * from materias order by codigo_materia desc";
        try{
            con = cn.getConexion();
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            st.setFetchSize(numRegPagina);
            rs = st.executeQuery(sql);
            int fila = numRegPagina * (numPagina -1 )+1;
            int cont = 1;
            if(rs.absolute(fila) && (numRegPagina > 0)){
                do{
                    Materias mt = new Materias();
                    mt.setCodigo_materia(rs.getInt("codigo_materia"));
                    mt.setMateria(rs.getString("materia"));
                    mt.setUv(rs.getString("uv"));
                    mt.setId_ciclo(rs.getInt("id_ciclo"));
                    mt.setPrerequisito(rs.getString("prerequisito"));
                    list.add(mt);
                    cont++;
                }while(rs.next() && (cont <= numRegPagina));
            }
        }catch(SQLException e){}
        return list;
    }
    
    @Override
    public List listar2() {
        ArrayList<Materias> list=new ArrayList<>();//generar una lista del tipo Persona, va a ir recibiendo de la base de datos
        String sql="select * from materias order by codigo_materia desc";
        try {
            con=cn.getConexion();//nos conectamos
            ps=con.prepareStatement(sql);//preparamos la sentencia sql
            rs=ps.executeQuery();//la ejecutamos
            while(rs.next()){
                Materias mt=new Materias();
                    mt.setCodigo_materia(rs.getInt("codigo_materia"));
                    mt.setMateria(rs.getString("materia"));
                    mt.setUv(rs.getString("uv"));
                    mt.setId_ciclo(rs.getInt("id_ciclo"));
                    mt.setPrerequisito(rs.getString("prerequisito"));
                list.add(mt);//agregamos una copia de esa 
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    @Override
    public boolean add(Materias mat) {
        String sql = "insert into materias(materia, uv, id_ciclo, prerequisito) values(?,?,?,?)";
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, mat.getMateria());
            ps.setString(2, mat.getUv());
            ps.setInt(3, mat.getId_ciclo());
            ps.setString(4, mat.getPrerequisito());
            ps.executeUpdate();
        }catch(SQLException e){}
        return false;
    }

    @Override
    public boolean edit(Materias mat) {
        String sql = "update materias set materia=?, uv=?, id_ciclo=?, prerequisito=? where codigo_materia=?";
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, mat.getMateria());
            ps.setString(2, mat.getUv());
            ps.setInt(3, mat.getId_ciclo());
            ps.setString(4, mat.getPrerequisito());
            ps.setInt(5, mat.getCodigo_materia());
            ps.executeUpdate();
        }catch(SQLException e){}
        return false;
    }

    @Override
    public boolean eliminar(int codigo) {
        String sql = "delete from materias where codigo_materia="+codigo;
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(SQLException e){}
        return false;
    }

    @Override
    public int getTodos() {
        String query = "select * from materias";
        int total = 0;
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                total++;
            }
        }catch(SQLException e){}
        return total;
    }
    
    @Override
    public List listar3(String ciclos) {
        ArrayList<Materias> list=new ArrayList<>();
        String sql="select m.codigo_materia, m.materia, m.uv, m.id_ciclo, m.prerequisito from materias as m inner join ciclos as c on m.id_ciclo = c.id_ciclo where ciclo=? and m.codigo_materia not in (select i.codigo_materia from inscripciones as i where i.codigo_materia = m.codigo_materia)";
        try {
            con=cn.getConexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, ciclos);
            rs=ps.executeQuery();
            while(rs.next()){
                Materias mt=new Materias();
                    mt.setCodigo_materia(rs.getInt("codigo_materia"));
                    mt.setMateria(rs.getString("materia"));
                    mt.setUv(rs.getString("uv"));
                    mt.setId_ciclo(rs.getInt("id_ciclo"));
                    mt.setPrerequisito(rs.getString("prerequisito"));
                list.add(mt);
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    //OBTENER UNA MATERIA ESPECIFICA
    @Override
    public Materias list(int codigo_materia) {
        String sql = "select * from materias where codigo_materia=?";
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigo_materia);
            rs = ps.executeQuery();
            while(rs.next()){
                mat.setCodigo_materia(rs.getInt("codigo_materia"));
                mat.setUv(rs.getString("uv"));
                mat.setId_ciclo(rs.getInt("id_ciclo"));
                mat.setMateria(rs.getString("materia"));
                mat.setPrerequisito(rs.getString("prerequisito"));
            }
        }catch(SQLException e){}
        return mat;
    } 
}
