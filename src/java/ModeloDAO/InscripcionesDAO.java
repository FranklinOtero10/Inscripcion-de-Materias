package ModeloDAO;
import Config.Conexion;
import Modelo.Inscripciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Interfaces.CRUDInscripciones;
import java.util.List;

public class InscripcionesDAO implements CRUDInscripciones{
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    Statement st = null;
    ResultSet rs;
    Inscripciones insc = new Inscripciones();
    
    @Override
    public ArrayList listar(int numPagina, int numRegPagina){
        ArrayList<Inscripciones>list = new ArrayList<>();
        String sql= "select * from inscripciones order by id_inscripcion desc";
        try {
            con=cn.getConexion();
            st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            st.setFetchSize(numRegPagina);
            rs=st.executeQuery(sql);
            int fila = numRegPagina * (numPagina -1) + 1;
            int cont =1;
            if(rs.absolute(fila) && numRegPagina > 0){
                do{
                    Inscripciones insc = new Inscripciones();
                    insc.setId_inscripcion(rs.getInt("id_inscripcion"));
                    insc.setId_grupo(rs.getInt("id_grupo"));
                    insc.setCodigo_materia(rs.getInt("codigo_materia"));
                    insc.setExpediente(rs.getInt("expediente"));
                    insc.setId_ciclo(rs.getInt("id_ciclo"));
                    insc.setAnio(rs.getString("anio"));
                    list.add(insc);
                    cont++;
                }while(rs.next() && (cont <= numRegPagina));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    @Override
    public List listar2() {
        ArrayList<Inscripciones> list=new ArrayList<>();//generar una lista del tipo Persona, va a ir recibiendo de la base de datos
        String sql="select * from inscripciones order by id_inscripcion desc";
        try {
            con=cn.getConexion();//nos conectamos
            ps=con.prepareStatement(sql);//preparamos la sentencia sql
            rs=ps.executeQuery();//la ejecutamos
            while(rs.next()){
                Inscripciones insc=new Inscripciones();
                    insc.setId_inscripcion(rs.getInt("id_inscripcion"));
                    insc.setId_grupo(rs.getInt("id_grupo"));
                    insc.setCodigo_materia(rs.getInt("codigo_materia"));
                    insc.setExpediente(rs.getInt("expediente"));
                    insc.setId_ciclo(rs.getInt("id_ciclo"));
                    insc.setAnio(rs.getString("anio"));
                list.add(insc);//agregamos una copia de esa persona
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    @Override
    public boolean add(Inscripciones in){
        String sql = "insert into inscripciones(id_grupo,id_ciclo,codigo_materia,expediente,anio) values (?,?,?,?,?)";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, in.getId_grupo());
            ps.setInt(2, in.getId_ciclo());
            ps.setInt(3, in.getCodigo_materia());
            ps.setInt(4, in.getExpediente());
            ps.setString(5, in.getAnio());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return false;
    }
    
    @Override
    public boolean edit(Inscripciones in){
        String sql = "update inscripciones set id_grupo = ?, id_ciclo = ?, codigo_materia = ?, expediente =?, anio = ? where id_inscripcion = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, in.getId_grupo());
            ps.setInt(2, in.getId_ciclo());
            ps.setInt(3, in.getCodigo_materia());
            ps.setInt(4, in.getExpediente());
            ps.setString(5, in.getAnio());
            ps.setInt(6, in.getId_inscripcion());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return false;
    }
    @Override
    public boolean eliminar(int id_iscripcion){
        String sql = "delete from inscripciones where id_inscripcion ="+id_iscripcion;
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {}
        return false;   
    }
    
    @Override
    public int getTodos(){
        String query = "select * from inscripciones";
        int total = 0;
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                total++;
            }
        } catch (SQLException e) {
        }
        return total;
    }

    @Override
    public List listar3(String anio, String ciclo, int expediente) {
        ArrayList<Inscripciones> list=new ArrayList<>();//generar una lista del tipo Persona, va a ir recibiendo de la base de datos
        String sql="select * from inscripciones as i, ciclos as c where i.id_ciclo = c.id_ciclo and i.expediente=? and c.ciclo=? and i.anio=?";
        try {
            con=cn.getConexion();//nos conectamos
            ps=con.prepareStatement(sql);//preparamos la sentencia sql
            ps.setInt(1, expediente);
            ps.setString(2, ciclo);
            ps.setString(3, anio);
            rs=ps.executeQuery();//la ejecutamos
            while(rs.next()){
                Inscripciones insc=new Inscripciones();
                    insc.setId_inscripcion(rs.getInt("id_inscripcion"));
                    insc.setId_grupo(rs.getInt("id_grupo"));
                    insc.setCodigo_materia(rs.getInt("codigo_materia"));
                    insc.setExpediente(rs.getInt("expediente"));
                    insc.setId_ciclo(rs.getInt("id_ciclo"));
                    insc.setAnio(rs.getString("anio"));
                list.add(insc);//agregamos una copia de esa persona
            }
        } catch (Exception e) {
        }
        return list;
    }
    public int listar4(String anio, String ciclo, int expediente){
        Conexion cn=new Conexion();
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        int conteo=0;
        String sql="select count(i.codigo_materia)  from inscripciones i, materias m, ciclos c where i.codigo_materia=m.codigo_materia and i.id_ciclo=c.id_ciclo and expediente="+expediente+" and c.ciclo='"+ciclo+"' and i.anio='"+anio + "'";        
        try {
            conn=cn.getConexion();
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                conteo = rs.getInt(1);                                
            }
            conn.close();
        } 
        catch (SQLException e) {
        }        
        return conteo;
    }
    public int consultaciclo(String anio, String ciclo, int expediente){
        String sql = "select * from inscripciones i inner join ciclos c on c.id_ciclo=i.id_ciclo where expediente=? and c.ciclo=? and i.anio=?";
        int contador=0; 
        try {      
            con=cn.getConexion();//nos conectamos
            ps=con.prepareStatement(sql);//preparamos la sentencia sql
            ps.setInt(1, expediente);
            ps.setString(2, ciclo);
            ps.setString(3, anio);
            rs=ps.executeQuery();
            
            while (rs.next()) {
                contador ++;                           
            }
        } catch (Exception e) {
        }
        return contador;
    }
    
//    public int consultaanio(String anio, String ciclo, int expediente){
//        
//        String sql = "select * from inscripciones i inner join ciclos c on c.id_ciclo=i.id_ciclo where expediente=? and c.ciclo=? and i.anio=?";
//        int contador=0; 
//        try {      
//            con=cn.getConexion();//nos conectamos
//            ps=con.prepareStatement(sql);//preparamos la sentencia sql
//            ps.setInt(1, expediente);
//            ps.setString(2, ciclo);
//            ps.setString(3, anio);
//            rs=ps.executeQuery();
//            
//            while (rs.next()) {
//                contador ++;                           
//            }
//        } catch (Exception e) {
//        }
//        return contador;
//    }
}
