package ModeloDAO;
import Config.Conexion;
import Interfaces.CRUDGrupos;
import Modelo.Grupos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GruposDAO implements CRUDGrupos{
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    Statement st = null;
    ResultSet rs;
    Grupos grp = new Grupos();
    
    @Override
    public ArrayList listar(int numPagina, int numRegPagina) {
        ArrayList<Grupos>list = new ArrayList<>();
        String sql="select * from grupos order by id_grupo desc";
        try {
            con=cn.getConexion();
            st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            st.setFetchSize(numRegPagina);
            rs=st.executeQuery(sql);
            int fila = numRegPagina * (numPagina -1) + 1;
            int cont =1;
            if(rs.absolute(fila) && numRegPagina > 0){
                do{
                    Grupos gru = new Grupos();
                    gru.setId_grupo(rs.getInt("id_grupo"));
                    gru.setGrupo(rs.getString("grupo"));
                    gru.setHorario(rs.getString("horario"));
                    gru.setAula(rs.getString("aula"));
                    list.add(gru);
                    cont++;
                }while(rs.next() && (cont <= numRegPagina));
            }
        } catch (Exception e) {
        }
        return list;
    }   
    
    public List listar2() {
        ArrayList<Grupos> list=new ArrayList<>();//generar una lista del tipo Persona, va a ir recibiendo de la base de datos
        String sql="select * from grupos order by id_grupo desc";
        try {
            con=cn.getConexion();//nos conectamos
            ps=con.prepareStatement(sql);//preparamos la sentencia sql
            rs=ps.executeQuery();//la ejecutamos
            while(rs.next()){
                    Grupos gru = new Grupos();
                    gru.setId_grupo(rs.getInt("id_grupo"));
                    gru.setGrupo(rs.getString("grupo"));
                    gru.setHorario(rs.getString("horario"));
                    gru.setAula(rs.getString("aula"));
                    list.add(gru);
            }
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public boolean add(Grupos gru) {
        String sql="insert into grupos(grupo,horario,aula) values(?,?,?)";
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, gru.getGrupo());
            ps.setString(2, gru.getHorario());
            ps.setString(3, gru.getAula());
            ps.executeUpdate();
        }catch(Exception e){
        }
        return false;
    }

    @Override
    public boolean edit(Grupos gru) {
        String sql = "update grupos set grupo=?, horario=?, aula=? where id_grupo=?";
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, gru.getGrupo());
            ps.setString(2, gru.getHorario());
            ps.setString(3, gru.getAula());
            ps.setInt(4, gru.getId_grupo());
            ps.executeUpdate();
        }catch(Exception e){}
        return false;
    }

    @Override
    public boolean eliminar(int idgrupo) {
        String sql = "delete from grupos where id_grupo="+idgrupo;
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){}
        return false;
    }  
    
    @Override
    public int getTodos(){
        String query = "select * from grupos";
        int total =0;
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                total++;
            }
        }catch(SQLException e){
        }
        return total;
    }
    //OBTENER GRUPO ESPECIFICO
    @Override
    public Grupos list(int id_grupo) {
        String sql = "select * from grupos where id_grupo=?";
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1 , id_grupo);
            rs = ps.executeQuery();
            while(rs.next()){
                grp.setId_grupo(rs.getInt("id_grupo"));
                grp.setGrupo(rs.getString("grupo"));
                grp.setHorario(rs.getString("horario"));
                grp.setAula(rs.getString("aula"));
            }
        }catch(SQLException e){}
        return grp;
    }
}
