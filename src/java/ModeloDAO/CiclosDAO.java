package ModeloDAO;
import Config.Conexion;
import Modelo.Ciclos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Interfaces.CRUDCiclos;
import Modelo.Materias;
import java.sql.Statement;
import java.util.List;

public class CiclosDAO implements CRUDCiclos{
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    Statement st = null;
    ResultSet rs;
    Ciclos cic = new Ciclos();
     
    @Override
    public ArrayList listar(int numPagina, int numRegPagina) {
        ArrayList<Ciclos>list = new ArrayList<>();
        String sql="select * from ciclos order by id_ciclo desc";
        try {
            con=cn.getConexion();
            st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            st.setFetchSize(numRegPagina);
            rs=st.executeQuery(sql);
            int fila = numRegPagina * (numPagina -1) + 1;
            int cont =1;
            if(rs.absolute(fila) && numRegPagina > 0){
                do{
                    Ciclos cic = new Ciclos();
                    cic.setId_ciclo(rs.getInt("id_ciclo"));
                    cic.setCiclo(rs.getString("ciclo"));
                    list.add(cic);
                    cont++;
                }while(rs.next() && (cont <= numRegPagina));
            }
        } catch (Exception e) {
        }
        return list;
    }    
    
    public List listar2() {
        ArrayList<Ciclos> list=new ArrayList<>();//generar una lista del tipo Persona, va a ir recibiendo de la base de datos
        String sql="select * from ciclos order by id_ciclo desc";
        try {
            con=cn.getConexion();//nos conectamos
            ps=con.prepareStatement(sql);//preparamos la sentencia sql
            rs=ps.executeQuery();//la ejecutamos
            while(rs.next()){
                    Ciclos cic = new Ciclos();
                    cic.setId_ciclo(rs.getInt("id_ciclo"));
                    cic.setCiclo(rs.getString("ciclo"));
                    list.add(cic);
            }
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public boolean add(Ciclos cic) {
        String sql="insert into ciclos(ciclo) values(?)";
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cic.getCiclo());
            ps.executeUpdate();
        }catch(Exception e){
        }
        return false;
    }

    @Override
    public boolean edit(Ciclos cic) {
        String sql = "update ciclos set ciclo=? where id_ciclo=?";
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cic.getCiclo());
            ps.setInt(2, cic.getId_ciclo());
            ps.executeUpdate();
        }catch(Exception e){}
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "delete from ciclos where id_ciclo="+id;
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){}
        return false;
    }  
    
    @Override
    public int getTodos(){
        String query = "select * from ciclos";
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
}
