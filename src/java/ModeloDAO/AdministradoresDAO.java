package ModeloDAO;
import Config.Conexion;
import Modelo.Administradores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Interfaces.CRUDAdm;
import java.sql.Statement;

public class AdministradoresDAO implements CRUDAdm{
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    Statement st = null;
    ResultSet rs;
    Administradores adm = new Administradores();
    
    @Override
    public ArrayList listar(int numPagina, int numRegPagina) {
        ArrayList<Administradores>list = new ArrayList<>();
        String sql="select * from administradores order by id_administrador desc";
        try {
            con=cn.getConexion();
            st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            st.setFetchSize(numRegPagina);
            rs=st.executeQuery(sql);
            int fila = numRegPagina * (numPagina -1) + 1;
            int cont =1;
            if(rs.absolute(fila) && numRegPagina > 0){
                do{
                    Administradores adm = new Administradores();
                    adm.setId_administrador(rs.getInt("id_administrador"));
                    adm.setTelefono(rs.getString("telefono"));
                    adm.setEmail(rs.getString("email"));
                    adm.setDireccion(rs.getString("direccion"));
                    adm.setSexo(rs.getString("sexo"));
                    adm.setCargo(rs.getString("cargo"));
                    adm.setIdusuario(rs.getInt("idusuario"));
                    list.add(adm);
                    cont++;
                }while(rs.next() && (cont <= numRegPagina));
            }
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public boolean add(Administradores adm) {
        String sql="insert into administradores(telefono, email, direccion, sexo, cargo, idusuario)values(?,?,?,?,?,?)";
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, adm.getTelefono());
            ps.setString(2, adm.getEmail());
            ps.setString(3, adm.getDireccion());
            ps.setString(4, adm.getSexo());
            ps.setString(5, adm.getCargo());
            ps.setInt(6, adm.getIdusuario());
            ps.executeUpdate();
        }catch(Exception e){
        }
        return false;
    }

    @Override
    public boolean edit(Administradores adm) {
        String sql = "update administradores set telefono=?, email=?, direccion=?, sexo=?, cargo=?, idusuario=? where id_administrador=?";
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, adm.getTelefono());
            ps.setString(2, adm.getEmail());
            ps.setString(3, adm.getDireccion());
            ps.setString(4, adm.getSexo());
            ps.setString(5, adm.getCargo());
            ps.setInt(6, adm.getIdusuario());
            ps.setInt(7, adm.getId_administrador());
            ps.executeUpdate();
        }catch(Exception e){}
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "delete from administradores where id_administrador="+id;
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){}
        return false;
    }  
    
    @Override
    public int getTodos(){
        String query = "select * from administradores";
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
