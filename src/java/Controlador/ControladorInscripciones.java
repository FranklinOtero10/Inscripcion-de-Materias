package Controlador;
import Modelo.Inscripciones;
import ModeloDAO.InscripcionesDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorInscripciones extends HttpServlet {
    String listarIns = "vistasAdm/Listar/ListarInscripciones.jsp";
    String Actualizar = "vistasAdm/PrincipalAdm.jsp?opc=8";
    String listarEst = "vistasEst/PrincipalEst.jsp?opc=1";
    String Actualizar2 = "vistasEst/Inscripcion.jsp";
    String HorarioEstudiante = "vistasEst/HorarioEstudiante.jsp";
    Inscripciones inscrip = new Inscripciones();
    InscripcionesDAO indao = new InscripcionesDAO();
    int idinscripc=0;
    int contador = 0;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acceso="";
        String action=request.getParameter("accion");
        if(action.equalsIgnoreCase("listarInscripciones")){
            String registros = (String)request.getAttribute("reg"); 
            String paginas = (String)request.getAttribute("pag");
            if(registros == null){
                request.setAttribute("reg","5");
            }
            if(paginas == null){
                request.setAttribute("pag","1");
            }
            acceso=listarIns;
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
        int lugar ;
        String action=request.getParameter("accion");
        if(action.equalsIgnoreCase("Agregar")){
            String registros = (String)request.getAttribute("reg"); 
            String paginas = (String)request.getAttribute("pag");
            lugar = Integer.parseInt(request.getParameter("txtlugar"));
            int Idgr=Integer.parseInt(request.getParameter("txtidgr"));
            int codmat = Integer.parseInt(request.getParameter("txtcodigomat"));
            int Exp=Integer.parseInt(request.getParameter("txtexp"));
            int Idc=Integer.parseInt(request.getParameter("txtciclo"));
            String anio=request.getParameter("txtanio");
            inscrip.setId_grupo(Idgr);
            inscrip.setCodigo_materia(codmat);
            inscrip.setExpediente(Exp);
            inscrip.setId_ciclo(Idc);
            inscrip.setAnio(anio);
            indao.add(inscrip);
            if(registros == null){
                request.setAttribute("reg","5");
            }
            if(paginas == null){
                request.setAttribute("pag","1");
            }
            if(lugar == 1){
                contador++;
                if(contador == 5){
                    acceso = HorarioEstudiante;
                    contador=0;
                }else{
                    request.setAttribute("idest", request.getParameter("txtexp"));
                    request.setAttribute("txtciclo", request.getParameter("txtciclo"));
                    request.setAttribute("txtanio", request.getParameter("txtanio"));
                    acceso = Actualizar2;
                }
                request.setAttribute("txtciclo", request.getParameter("txtciclo"));
                request.setAttribute("txtanio", request.getParameter("txtanio"));
                request.setAttribute("idest", request.getParameter("txtexp"));
            }else{
                acceso = Actualizar;
            }
        }else if (action.equalsIgnoreCase("Actualizar")){
            String registros = (String)request.getAttribute("reg"); 
            String paginas = (String)request.getAttribute("pag"); 
            idinscripc=Integer.parseInt(request.getParameter("txtidin"));
            int Idgr=Integer.parseInt(request.getParameter("txtidgr"));
            int codmat = Integer.parseInt(request.getParameter("txtcodigomat"));
            int Exp=Integer.parseInt(request.getParameter("txtexp"));
            int Idc=Integer.parseInt(request.getParameter("txtidcic"));
            String anio=request.getParameter("txtanio");
            inscrip.setId_inscripcion(idinscripc);
            inscrip.setId_grupo(Idgr);
            inscrip.setCodigo_materia(codmat);
            inscrip.setExpediente(Exp);
            inscrip.setId_ciclo(Idc);
            inscrip.setAnio(anio);
            indao.edit(inscrip);
            if(registros == null){
                request.setAttribute("reg","5");
            }
            if(paginas == null){
                request.setAttribute("pag","1");
            }
            acceso=Actualizar;
        }else if(action.equalsIgnoreCase("Eliminar")){
            idinscripc=Integer.parseInt(request.getParameter("id"));
            inscrip.setId_inscripcion(idinscripc);
            indao.eliminar(idinscripc);
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