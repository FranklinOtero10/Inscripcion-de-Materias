package ModeloDAO;
import Config.Conexion;
import Modelo.Usuarios;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import Interfaces.CRUDUsu;
import Modelo.Materias;
import java.sql.Statement;
import java.util.List;

public class UsuariosDAO implements CRUDUsu{
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    Statement st = null;
    ResultSet rs;
    Usuarios usu = new Usuarios();
    
    @Override
    public ArrayList listar(int numPagina, int numRegPagina) {
        ArrayList<Usuarios>list = new ArrayList<>();
        String sql="select * from usuarios order by id desc";
        try {
            con=cn.getConexion();
            st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            st.setFetchSize(numRegPagina);
            rs=st.executeQuery(sql);
            int fila = numRegPagina * (numPagina -1) + 1;
            int cont =1; 
            if(rs.absolute(fila) && numRegPagina > 0){
                do{
                    Usuarios usu = new Usuarios();
                    usu.setId(rs.getInt("id"));
                    usu.setCodigo(rs.getString("codigo"));
                    usu.setContrasena(rs.getString("contrasena"));
                    usu.setNivel(rs.getInt("nivel"));
                    usu.setNombres(rs.getString("nombres"));
                    usu.setApellidos(rs.getString("apellidos"));
                    usu.setFoto(rs.getBinaryStream("foto"));
                    list.add(usu);
                    cont++;
                }while(rs.next() && (cont <= numRegPagina));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    
    public List listar2() {
        ArrayList<Usuarios> list=new ArrayList<>();
        String sql="select * from usuarios order by id desc";
        try {
            con=cn.getConexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                    Usuarios usu = new Usuarios();
                    usu.setId(rs.getInt("id"));
                    usu.setCodigo(rs.getString("codigo"));
                    usu.setContrasena(rs.getString("contrasena"));
                    usu.setNivel(rs.getInt("nivel"));
                    usu.setNombres(rs.getString("nombres"));
                    usu.setApellidos(rs.getString("apellidos"));
                    usu.setFoto(rs.getBinaryStream("foto"));
                    list.add(usu);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void listarImg(int Id, HttpServletResponse response){
        String sql="select * from usuarios where id="+Id;
        InputStream inputStream=null;
        OutputStream outputStream=null;
        BufferedInputStream bufferedInputStream=null;
        BufferedOutputStream bufferedOutputStream = null;
        response.setContentType("image/*");
        try{
            outputStream=response.getOutputStream();
            con=cn.getConexion();
            ps = con.prepareStatement(sql);
            rs=ps.executeQuery();
            if(rs.next()){
                inputStream=rs.getBinaryStream("foto");
            }
            bufferedInputStream=new BufferedInputStream(inputStream);
            bufferedOutputStream=new BufferedOutputStream(outputStream);
            int i=0;
            while((i=bufferedInputStream.read())!=-1){
                bufferedOutputStream.write(i);
            }
        }catch (Exception e){ 
        }
    }
    
    @Override
    public Usuarios list(int id) {
        String sql = "select * from usuarios where id="+id;
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                usu.setId(rs.getInt("id"));
                usu.setCodigo(rs.getString("codigo"));
                usu.setContrasena(rs.getString("contrasena"));
                usu.setNivel(rs.getInt("nivel"));
                usu.setNombres(rs.getString("nombres"));
                usu.setApellidos(rs.getString("apellidos"));
                usu.setFoto(rs.getBinaryStream("foto"));
            }
        } catch (SQLException ex) {
        }
        return usu;
    }

    @Override
    public boolean add(Usuarios usu) {
        String sql="insert into usuarios(codigo,contrasena,nivel,nombres,apellidos,foto)values(?,?,?,?,?,?)";
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, usu.getCodigo());
            ps.setString(2, usu.getContrasena());
            ps.setInt(3, usu.getNivel());
            ps.setString(4, usu.getNombres());
            ps.setString(5, usu.getApellidos());
            ps.setBinaryStream(6, usu.getFoto());
            ps.executeUpdate();
        }catch(Exception e){
        }
        return false;
    }

    @Override
    public boolean edit(Usuarios usu) {
        String sql = "update usuarios set codigo=?, contrasena=?, nivel=?, nombres=?, apellidos=?, foto=?  where id=?";
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, usu.getCodigo());
            ps.setString(2, usu.getContrasena());
            ps.setInt(3, usu.getNivel());
            ps.setString(4, usu.getNombres());
            ps.setString(5, usu.getApellidos());
            ps.setBinaryStream(6, usu.getFoto());
            ps.setInt(7, usu.getId());
            ps.executeUpdate();
        }catch(Exception e){}
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "delete from usuarios where id="+id;
        try{
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){}
        return false;
    } 
    
    @Override
    public int getTodos(){
        String query = "select * from usuarios";
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
