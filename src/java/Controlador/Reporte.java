package Controlador;
import Config.Conexion;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

public class Reporte extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Conexion cn  = new Conexion();
        Connection con = cn.getConexion();
        int idest = Integer.parseInt(request.getParameter("txtexp"));
        String anio = request.getParameter("txtanio");
        String ciclo = request.getParameter("txtciclo");
        ServletContext context = request.getServletContext();
        File reportFile = new File(context.getRealPath("/") + "reporte/ReporteInscripcion.jasper");
        Map parametros = new HashMap();
        parametros.put("expediente", idest);
        parametros.put("anio", anio);
        parametros.put("ciclo", ciclo);
        byte[] bytes = null;
        try{
            bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parametros, con);
        }catch(JRException ex){
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE , null , ex);
        }
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=Comprobante Inscripcion");
        response.setContentLength(bytes.length);
        try(ServletOutputStream outputStream=response.getOutputStream()){
            outputStream.write(bytes, 0, bytes.length);
            outputStream.flush();
        }
    } 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
         processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
         processRequest(request, response);
    }
}
