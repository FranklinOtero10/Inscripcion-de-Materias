package Controlador;
import Modelo.Carreras;
import ModeloDAO.CarrerasDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorCarreras extends HttpServlet {
    String listarCarreras = "vistasAdm/Listar/ListarCarreras.jsp";
    String Actualizar = "vistasAdm/PrincipalAdm.jsp?opc=5";
    Carreras car = new Carreras();
    CarrerasDAO dao = new CarrerasDAO();
    int idcarrera =0;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acceso="";
        String action=request.getParameter("accion");
        if(action.equalsIgnoreCase("listarCarreras")){
            String registros = (String)request.getAttribute("reg"); 
            String paginas = (String)request.getAttribute("pag");
            if(registros == null){
                request.setAttribute("reg","5");
            }
            if(paginas == null){
                request.setAttribute("pag","1");
            }
            acceso=listarCarreras;
        }
        else if(action.equalsIgnoreCase("Resultado")){
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
        String acceso = "";
        String action = request.getParameter("accion");
        if(action.equalsIgnoreCase("Agregar")){
            String registros = (String)request.getAttribute("reg");
            String paginas = (String)request.getAttribute("pag");
            String codigo_carrera = request.getParameter("txtcodigo");
            String carrera = request.getParameter("txtcarrera");
            car.setCodigo_carrera(codigo_carrera);
            car.setCarrera(carrera);
            dao.add(car);
            if(registros == null){
                request.setAttribute("reg","5");
            }
            if(paginas == null){
                request.setAttribute("pag", "1");
            }
            acceso = Actualizar;
        }
        else if(action.equalsIgnoreCase("Actualizar")){
            String registros = (String) request.getAttribute("reg");
            String paginas = (String) request.getAttribute("pag");
            String codigo_carrera = request.getParameter("txtcod");
            String carrera = request.getParameter("txtcar");
            int id_carrera = Integer.parseInt(request.getParameter("txtidcar"));
            car.setId_carrera(id_carrera);
            car.setCodigo_carrera(codigo_carrera);
            car.setCarrera(carrera);
            dao.edit(car);
            if(registros == null){
                request.setAttribute("reg", "5");
            }
            if(paginas == null){
                request.setAttribute("pag", "1");
            }
            acceso = Actualizar;
        }else if(action.equalsIgnoreCase("Eliminar")){
            idcarrera = Integer.parseInt(request.getParameter("id"));
            car.setId_carrera(idcarrera);
            dao.eliminar(idcarrera);
            String registros = (String) request.getAttribute("reg");
            String paginas = (String) request.getAttribute("pag");
            if(registros == null){
                request.setAttribute("reg", "5");
            }
            if(paginas == null){
                request.setAttribute("pag", "1");
            }
            acceso = Actualizar;
        }
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request,response);
    }
}