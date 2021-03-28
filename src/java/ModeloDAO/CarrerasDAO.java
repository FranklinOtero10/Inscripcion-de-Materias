package ModeloDAO;
import Config.Conexion;
import Modelo.Carreras;
import Interfaces.CRUDCarreras;
import Modelo.Materias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarrerasDAO implements CRUDCarreras{
    Conexion cn= new Conexion();
    Connection con;
    PreparedStatement ps;
    Statement st = null;
    ResultSet rs;
    Carreras car = new Carreras();

    @Override
    public ArrayList listar(int numPagina, int numRegPagina) {
        ArrayList<Carreras>list = new ArrayList<>();
        String sql="select * from carreras order by id_carrera desc";
        try {
            con=cn.getConexion();
            st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            st.setFetchSize(numRegPagina);
            rs=st.executeQuery(sql);
            int fila = numRegPagina * (numPagina -1) + 1;
            int cont =1;
            if(rs.absolute(fila) && numRegPagina > 0){
                do{
                    Carreras carear = new Carreras();
                    carear.setId_carrera(rs.getInt("id_carrera"));
                    carear.setCarrera(rs.getString("carrera"));
                    carear.setCodigo_carrera(rs.getString("codigo_carrera"));
                    list.add(carear);
                    cont++;
                }while(rs.next() && (cont <= numRegPagina));
            }
        } catch (Exception e) {
        }
        return list;
    } 
    
    public List listar2() {
        ArrayList<Carreras> list=new ArrayList<>();//generar una lista del tipo Persona, va a ir recibiendo de la base de datos
        String sql="select * from carreras order by id_carrera desc";
        try {
            con=cn.getConexion();//nos conectamos
            ps=con.prepareStatement(sql);//preparamos la sentencia sql
            rs=ps.executeQuery();//la ejecutamos
            while(rs.next()){
                    Carreras carear = new Carreras();
                    carear.setId_carrera(rs.getInt("id_carrera"));
                    carear.setCarrera(rs.getString("carrera"));
                    carear.setCodigo_carrera(rs.getString("codigo_carrera"));
                list.add(carear);//agregamos una copia de esa 
            }
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public boolean add(Carreras carear) {
        String sql = "insert into carreras (codigo_carrera, carrera) values(?,?)";
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, carear.getCodigo_carrera());
            ps.setString(2, carear.getCarrera());
            ps.executeUpdate();
        }catch(SQLException e){}
        return false;
    }

    @Override
    public boolean edit(Carreras carear) {
        String sql = "update carreras set codigo_carrera=? , carrera=? where id_carrera=?";
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, carear.getCodigo_carrera());
            ps.setString(2, carear.getCarrera());
            ps.setInt(3, carear.getId_carrera());
            ps.executeUpdate();
        }catch(SQLException e){}
        return false;
    }

    @Override
    public boolean eliminar(int idcarrera) {
        String sql = "delete from carreras where id_carrera="+idcarrera;
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(SQLException e){}
        return false;
    }

    @Override
    public int getTodos() {
        String query = "select * from carreras";
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
}
