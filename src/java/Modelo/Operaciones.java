package Modelo;
import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServlet;

public class Operaciones extends HttpServlet {
 
    public int loguear(String codigo_, String contrasena_){
        Conexion cn=new Conexion();
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        int nivel=0;
        String sql="select nivel from usuarios where codigo='" + codigo_ + "' and contrasena='" + contrasena_ + "'";
        try {
            conn=cn.getConexion();
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                nivel = rs.getInt(1);                                
            }
            conn.close();
        } catch (SQLException e) {
        }        
        return nivel;
    }
}