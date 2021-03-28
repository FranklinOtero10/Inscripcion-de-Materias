package Controlador;
import java.io.IOException;
import Modelo.Materias;
import ModeloDAO.MateriasDAO;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorMaterias extends HttpServlet {
    String listarMaterias = "vistasAdm/Listar/ListarMaterias.jsp";
    String Actualizar = "vistasAdm/PrincipalAdm.jsp?opc=6";
    Materias mat = new Materias();
    MateriasDAO dao = new MateriasDAO();
    int codigo = 0;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acceso="";
        String action=request.getParameter("accion");
        if(action.equalsIgnoreCase("listarMaterias")){
            String registros = (String)request.getAttribute("reg"); 
            String paginas = (String)request.getAttribute("pag");
            if(registros == null){
                request.setAttribute("reg","5");
            }
            if(paginas == null){
                request.setAttribute("pag","1");
            }
            acceso=listarMaterias;
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
            String materia = request.getParameter("txtmat");
            String uv = request.getParameter("txtuv");
            int idc = Integer.parseInt(request.getParameter("txtidciclo"));
            String prerequisito = request.getParameter("txtpre");
            mat.setMateria(materia);
            mat.setUv(uv);
            mat.setId_ciclo(idc);
            mat.setPrerequisito(prerequisito);
            dao.add(mat);
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
            int codigo_materia = Integer.parseInt(request.getParameter("txtcodigomat"));
            String uv = request.getParameter("txtuv");
            int idc = Integer.parseInt(request.getParameter("txtidciclo"));
            String materia = request.getParameter("txtmat");
            String prerequisito = request.getParameter("txtpre");
            mat.setCodigo_materia(codigo_materia);
            mat.setMateria(materia);
            mat.setUv(uv);
            mat.setId_ciclo(idc);
            mat.setPrerequisito(prerequisito);
            dao.edit(mat);
            if(registros == null){
                request.setAttribute("reg", "5");
            }
            if(paginas == null){
                request.setAttribute("pag", "1");
            }
            acceso = Actualizar;
        }else if(action.equalsIgnoreCase("Eliminar")){
            codigo = Integer.parseInt(request.getParameter("id"));
            mat.setCodigo_materia(codigo);
            dao.eliminar(codigo);
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
