package ModeloDAO;
import Modelo.Login;
import Interfaces.CRUDLogin;
import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDAO implements CRUDLogin{
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    Statement st = null;
    ResultSet rs;
    Login log = new Login();
    
    @Override
    public Login listCodigo(String codigo) {
        String sql = "select * from usuarios where codigo='" +codigo+ "'";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                log.setId(rs.getInt("id"));
                log.setCodigo(rs.getString("codigo"));
                log.setContrasena(rs.getString("contrasena"));
                log.setNivel(rs.getInt("nivel"));
                log.setNombres(rs.getString("nombres"));
                log.setApellidos(rs.getString("apellidos"));
                log.setFoto(rs.getBinaryStream("foto"));
            }
        } catch (SQLException ex) {
        }
        return log;
    }
}
