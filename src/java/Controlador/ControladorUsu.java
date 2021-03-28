package Controlador;
import Modelo.Usuarios;
import ModeloDAO.UsuariosDAO;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
public class ControladorUsu extends HttpServlet {
    String PrincipalAdm = "vistasAdm/PrincipalAdm.jsp?opc=1";
    String PrincipalEst = "vistasEst/PrincipalEst.jsp";
    String Inicio = "vistasAdm/Inicio.jsp";
    String listarUsu = "vistasAdm/Listar/ListarUsu.jsp";
    String Actualizar = "vistasAdm/PrincipalAdm.jsp?opc=2";
    String AcercaDe = "vistasAdm/AcercaDe.jsp";
    Usuarios usu = new Usuarios();
    UsuariosDAO dao = new UsuariosDAO();
    int id=0;    
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acceso="";
        String action=request.getParameter("accion");
        if(action.equalsIgnoreCase("PrincipalAdm")){
            acceso=PrincipalAdm;
        }else if(action.equalsIgnoreCase("PrincipalEst")){
            acceso=PrincipalEst;
        }else if(action.equalsIgnoreCase("Inicio")){
            acceso=Inicio;
        }else if(action.equalsIgnoreCase("listarUsu")){
            String registros = (String)request.getAttribute("reg"); 
            String paginas = (String)request.getAttribute("pag");
            if(registros == null){
                request.setAttribute("reg","5");
            }
            if(paginas == null){
                request.setAttribute("pag","1");
            }
            acceso=listarUsu;
        }else if(action.equalsIgnoreCase("AcercaDe")){
            acceso=AcercaDe;
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
            String cod=request.getParameter("txtCod");
            String con=request.getParameter("txtCon");
            int niv=Integer.parseInt(request.getParameter("txtNiv"));
            String nom=request.getParameter("txtNom");
            String ape=request.getParameter("txtApe");
            Part part=request.getPart("txtFoto");           
            InputStream inputStream=part.getInputStream();
            usu.setCodigo(cod);
            usu.setContrasena(getMD5(con));
            usu.setNivel(niv);
            usu.setNombres(nom);
            usu.setApellidos(ape);
            usu.setFoto(inputStream);
            dao.add(usu);
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
            id=Integer.parseInt(request.getParameter("txtId"));
            String cod=request.getParameter("txtCod");
            String con=request.getParameter("txtCon");
            String con2=request.getParameter("txtCon2");
            String chec=request.getParameter("chec");
            int niv=Integer.parseInt(request.getParameter("txtNiv"));
            String nom=request.getParameter("txtNom");
            String ape=request.getParameter("txtApe");
            Part part=request.getPart("txtFoto");           
            InputStream inputStream=part.getInputStream();
            if(chec != null){
                usu.setContrasena(getMD5(con2));
            }else{
                usu.setContrasena(con);
            }
            usu.setId(id);
            usu.setCodigo(cod);     
            usu.setNivel(niv);
            usu.setNombres(nom);
            usu.setApellidos(ape);
            usu.setFoto(inputStream);
            dao.edit(usu);
            if(registros == null){
                request.setAttribute("reg","5");
            }
            if(paginas == null){
                request.setAttribute("pag","1");
            }
            acceso=Actualizar;
        }else if(action.equalsIgnoreCase("Eliminar")){
            id=Integer.parseInt(request.getParameter("id"));
            usu.setId(id);
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
    
    public String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] encBytes = md.digest(input.getBytes("utf-8"));
            BigInteger numero = new BigInteger(1, encBytes);
            String encString = numero.toString(16);
            while (encString.length() < 32) {
                encString = "0" + encString;
            }
            return encString;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }  
}
