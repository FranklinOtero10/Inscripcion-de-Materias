package ModeloDAO;
import Config.Conexion;
import Modelo.Estudiantes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Interfaces.CRUDEst;
import Modelo.Materias;
import java.sql.Statement;
import java.util.List;

public class EstudiantesDAO implements CRUDEst{
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    Statement st = null;
    ResultSet rs;
    Estudiantes est = new Estudiantes();
    
    @Override
    public ArrayList listar(int numPagina, int numRegPagina) {
        ArrayList<Estudiantes>list = new ArrayList<>();
        String sql="select * from estudiantes order by expediente desc";
        try {
            con=cn.getConexion();
            st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            st.setFetchSize(numRegPagina);
            rs=st.executeQuery(sql);
            int fila = numRegPagina * (numPagina -1) + 1;
            int cont =1;
            if(rs.absolute(fila) && numRegPagina > 0){
                do{
                    Estudiantes est = new Estudiantes();
                    est.setExpediente(rs.getInt("expediente"));
                    est.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
                    est.setDireccion(rs.getString("direccion"));
                    est.setSexo(rs.getString("sexo"));
                    est.setDui(rs.getString("dui"));
                    est.setId_carrera(rs.getInt("id_carrera"));
                    est.setIdusuario(rs.getInt("idusuario"));
                    list.add(est);
                    cont++;
                }while(rs.next() && (cont <= numRegPagina));
            }
        } catch (Exception e) {
        }
        return list;
    }   
    
    public List listar2() {
        ArrayList<Estudiantes> list=new ArrayList<>();//generar una lista del tipo Persona, va a ir recibiendo de la base de datos
        String sql="select * from estudiantes order by expediente desc";
        try {
            con=cn.getConexion();//nos conectamos
            ps=con.prepareStatement(sql);//preparamos la sentencia sql
            rs=ps.executeQuery();//la ejecutamos
            while(rs.next()){
                    Estudiantes est = new Estudiantes();
                    est.setExpediente(rs.getInt("expediente"));
                    est.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
                    est.setDireccion(rs.getString("direccion"));
                    est.setSexo(rs.getString("sexo"));
                    est.setDui(rs.getString("dui"));
                    est.setId_carrera(rs.getInt("id_carrera"));
                    est.setIdusuario(rs.getInt("idusuario"));
                    list.add(est);
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    @Override
    public Estudiantes list(int expediente) {
        String sql = "select * from estudiantes where expediente="+expediente;
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                est.setExpediente(rs.getInt("expediente"));
                est.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
                est.setDireccion(rs.getString("direccion"));
                est.setSexo(rs.getString("sexo"));
                est.setDui(rs.getString("dui"));
                est.setId_carrera(rs.getInt("id_carrera"));
                est.setIdusuario(rs.getInt("idusuario"));
            }
        } catch (SQLException ex) {
        }
        return est;
    }

    @Override
    public boolean add(Estudiantes est) {
        String sql="insert into estudiantes(fecha_nacimiento, direccion, sexo, dui, id_carrera, idusuario)values(?,?,?,?,?,?)";
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, est.getFecha_nacimiento());
            ps.setString(2, est.getDireccion());
            ps.setString(3, est.getSexo());
            ps.setString(4, est.getDui());
            ps.setInt(5, est.getId_carrera());
            ps.setInt(6, est.getIdusuario());
            ps.executeUpdate();
        }catch(Exception e){
        }
        return false;
    }

    @Override
    public boolean edit(Estudiantes est) {
        String sql = "update estudiantes set fecha_nacimiento=?, direccion=?, sexo=?, dui=?, id_carrera=?, idusuario=? where expediente=?";
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, est.getFecha_nacimiento());
            ps.setString(2, est.getDireccion());
            ps.setString(3, est.getSexo());
            ps.setString(4, est.getDui());
            ps.setInt(5, est.getId_carrera());
            ps.setInt(6, est.getIdusuario());
            ps.setInt(7, est.getExpediente());
            ps.executeUpdate();
        }catch(Exception e){}
        return false;
    }

    @Override
    public boolean eliminar(int expediente) {
        String sql = "delete from estudiantes where expediente="+expediente;
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){}
        return false;
    }  
    
    @Override
    public int getTodos(){
        String query = "select * from estudiantes";
        Connection conBBDD = null;
        PreparedStatement pt = null;
        ResultSet rs = null;
        int total =0;
        try{
            conBBDD = cn.getConexion();
            pt = conBBDD.prepareStatement(query);
            rs = pt.executeQuery();
            while(rs.next()){
                total++;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return 0;
        }
        return total;
    }

    @Override
    public Estudiantes listUsuario(int idusuario) {
        String sql = "select * from estudiantes where idusuario="+idusuario;
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                est.setExpediente(rs.getInt("expediente"));
                est.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
                est.setDireccion(rs.getString("direccion"));
                est.setSexo(rs.getString("sexo"));
                est.setDui(rs.getString("dui"));
                est.setId_carrera(rs.getInt("id_carrera"));
                est.setIdusuario(rs.getInt("idusuario"));
            }
        }catch (SQLException e){
            
        }
        return est;
    }
}
