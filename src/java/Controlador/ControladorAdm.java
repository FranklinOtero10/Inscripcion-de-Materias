package Controlador;
import Modelo.Administradores;
import ModeloDAO.AdministradoresDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorAdm extends HttpServlet{
    String listarAdm = "vistasAdm/Listar/ListarAdm.jsp";
    String Actualizar = "vistasAdm/PrincipalAdm.jsp?opc=3";
    Administradores adm = new Administradores();
    AdministradoresDAO dao = new AdministradoresDAO();
    int id=0;
        
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acceso="";
        String action=request.getParameter("accion");
        if(action.equalsIgnoreCase("listarAdm")){
            String registros = (String)request.getAttribute("reg"); 
            String paginas = (String)request.getAttribute("pag");
            if(registros == null){
                request.setAttribute("reg","5");
            }
            if(paginas == null){
                request.setAttribute("pag","1");
            }
            acceso=listarAdm;
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
            String tel=request.getParameter("txtTel");
            String ema=request.getParameter("txtEma");
            String dir=request.getParameter("txtDir");
            String sex=request.getParameter("txtSex");
            String car=request.getParameter("txtCar");
            int idU=Integer.parseInt(request.getParameter("txtIdU"));
            adm.setTelefono(tel);
            adm.setEmail(ema);
            adm.setDireccion(dir);
            adm.setSexo(sex);
            adm.setCargo(car);
            adm.setIdusuario(idU);
            dao.add(adm);
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
            id=Integer.parseInt(request.getParameter("txtid"));
            String tel=request.getParameter("txtTel");
            String ema=request.getParameter("txtEma");
            String dir=request.getParameter("txtDir");
            String sex=request.getParameter("txtSex");
            String car=request.getParameter("txtCar");
            int idU=Integer.parseInt(request.getParameter("txtIdU"));
            adm.setId_administrador(id);
            adm.setTelefono(tel);
            adm.setEmail(ema);
            adm.setDireccion(dir);
            adm.setSexo(sex);
            adm.setCargo(car);
            adm.setIdusuario(idU);
            dao.edit(adm);
            if(registros == null){
                request.setAttribute("reg","5");
            }
            if(paginas == null){
                request.setAttribute("pag","1");
            }
            acceso=Actualizar;
        }else if(action.equalsIgnoreCase("Eliminar")){
            id=Integer.parseInt(request.getParameter("id"));
            adm.setId_administrador(id);
            dao.eliminar(id);
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
