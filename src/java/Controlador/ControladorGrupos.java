package Controlador;
import Modelo.Grupos;
import ModeloDAO.GruposDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorGrupos extends HttpServlet {
    String listarGru = "vistasAdm/Listar/ListarGrupos.jsp";
    String Actualizar = "vistasAdm/PrincipalAdm.jsp?opc=7";
    Grupos gru = new Grupos();
    GruposDAO dao = new GruposDAO();
    int idgrupo=0;   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acceso="";
        String action=request.getParameter("accion");
        if(action.equalsIgnoreCase("listarGru")){
            String registros = (String)request.getAttribute("reg"); 
            String paginas = (String)request.getAttribute("pag");
            if(registros == null){
                request.setAttribute("reg","5");
            }
            if(paginas == null){
                request.setAttribute("pag","1");
            }
            acceso=listarGru;
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
            String grupo=request.getParameter("txtgrupo");
            String horario=request.getParameter("txthorario");
            String aula=request.getParameter("txtaula");
            gru.setGrupo(grupo);
            gru.setHorario(horario);
            gru.setAula(aula);
            dao.add(gru);
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
            
            idgrupo=Integer.parseInt(request.getParameter("txtidgru"));
            String grupo=request.getParameter("txtgrupo");
            String horario=request.getParameter("txthorario");
            String aula=request.getParameter("txtaula");
            gru.setId_grupo(idgrupo);
            gru.setGrupo(grupo);
            gru.setHorario(horario);
            gru.setAula(aula);
            dao.edit(gru);
            if(registros == null){
                request.setAttribute("reg","5");
            }
            if(paginas == null){
                request.setAttribute("pag","1");
            }
            acceso=Actualizar;
        }else if(action.equalsIgnoreCase("Eliminar")){
            idgrupo=Integer.parseInt(request.getParameter("id"));
            gru.setId_grupo(idgrupo);
            dao.eliminar(idgrupo);
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
