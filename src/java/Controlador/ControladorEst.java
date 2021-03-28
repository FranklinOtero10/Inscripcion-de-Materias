package Controlador;
import Modelo.Estudiantes;
import ModeloDAO.EstudiantesDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorEst extends HttpServlet{
    String listarEst = "vistasAdm/Listar/ListarEst.jsp";
    String Actualizar = "vistasAdm/PrincipalAdm.jsp?opc=4";
    String comprobante = "vistasEst/HorarioEstudiante.jsp";
    String Inscripcion = "vistasEst/Inscripcion.jsp";
    Estudiantes est = new Estudiantes();
    EstudiantesDAO dao = new EstudiantesDAO();
    int expediente=0;
        
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acceso="";
        String action=request.getParameter("accion");
        if(action.equalsIgnoreCase("listarEst")){
            String registros = (String)request.getAttribute("reg"); 
            String paginas = (String)request.getAttribute("pag");
            if(registros == null){
                request.setAttribute("reg","5");
            }
            if(paginas == null){
                request.setAttribute("pag","1");
            }
            acceso=listarEst;
        }else if(action.equalsIgnoreCase("Resultado")){
            String register = request.getParameter("register");
            String pages = request.getParameter("pages");
            String registros= request.getParameter("regis");
            String paginas = request.getParameter("pags");
            if(register != null){
                request.setAttribute("reg",register);
            }else if(registros != null){
                request.setAttribute("reg",request.getParameter("regis"));
                request.getSession().removeAttribute("reg");
            }
            if(pages != null){
                request.setAttribute("pag",pages);
            }else if(paginas != null){
                request.setAttribute("pag",request.getParameter("pags"));
                request.getSession().removeAttribute("pag");
            }
            acceso=Actualizar;
        }
        else if(action.equalsIgnoreCase("inscripcion")){
            request.setAttribute("idest", request.getParameter("id"));
            request.setAttribute("txtciclo", request.getParameter("txtciclo"));
            request.setAttribute("txtanio", request.getParameter("txtanio"));
            acceso = Inscripcion;
        }else if(action.equalsIgnoreCase("comprobante")){
            request.setAttribute("idest", request.getParameter("id"));
            request.setAttribute("txtciclo", request.getParameter("txtciclo"));
            request.setAttribute("txtanio", request.getParameter("txtanio"));
            acceso = comprobante;
        }
        RequestDispatcher vista=request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acceso="";
        String action=request.getParameter("accion");
        if(action.equalsIgnoreCase("Agregar")){
            String registros = (String)request.getAttribute("reg"); 
            String paginas = (String)request.getAttribute("pag");
            String fec=request.getParameter("txtFec");
            String dir=request.getParameter("txtDir");
            String sex=request.getParameter("txtSex");
            String dui=request.getParameter("txtDui");
            int idC=Integer.parseInt(request.getParameter("txtIdC"));
            int idU=Integer.parseInt(request.getParameter("txtIdU"));
            est.setFecha_nacimiento(fec);
            est.setDireccion(dir);
            est.setSexo(sex);
            est.setDui(dui);
            est.setId_carrera(idC);
            est.setIdusuario(idU);
            dao.add(est);
            if(registros == null){
                request.setAttribute("reg","5");
            }
            if(paginas == null){
                request.setAttribute("pag","1");
            }
            acceso=Actualizar;
       }else if(action.equalsIgnoreCase("Actualizar")){
            String registros = (String)request.getAttribute("reg"); 
            String paginas = (String)request.getAttribute("pag");
            expediente=Integer.parseInt(request.getParameter("txtExp"));
            String fec=request.getParameter("txtFec");
            String dir=request.getParameter("txtDir");
            String sex=request.getParameter("txtSex");
            String dui=request.getParameter("txtDui");
            int idC=Integer.parseInt(request.getParameter("txtIdC"));
            int idU=Integer.parseInt(request.getParameter("txtIdU"));
            est.setExpediente(expediente);
            est.setFecha_nacimiento(fec);
            est.setDireccion(dir);
            est.setSexo(sex);
            est.setDui(dui);
            est.setId_carrera(idC);
            est.setIdusuario(idU);
            dao.edit(est);
            if(registros == null){
                request.setAttribute("reg","5");
            }
            if(paginas == null){
                request.setAttribute("pag","1");
            }
            acceso=Actualizar;
        }else if(action.equalsIgnoreCase("Eliminar")){
            expediente=Integer.parseInt(request.getParameter("id"));
            est.setExpediente(expediente);
            dao.eliminar(expediente);
            String registros = (String)request.getAttribute("reg"); 
            String paginas = (String)request.getAttribute("pag");
            if(registros == null){
                request.setAttribute("reg","5");
            }
            if(paginas == null){
                request.setAttribute("pag","1");
            }
            acceso=Actualizar;
        }
       RequestDispatcher vista=request.getRequestDispatcher(acceso);
       vista.forward(request, response);
    }
}
