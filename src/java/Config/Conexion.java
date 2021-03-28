package Config;
import java.sql.*;

public class Conexion {
    Connection con;
    public Conexion(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=Inscripcion;user=juan;password=juan123;");   
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error: \n"+e.getClass()+"\n"+e.getMessage());
        }
    }
    public Connection getConexion(){
        return con;
    }
}
