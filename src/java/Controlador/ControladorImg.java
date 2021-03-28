package Controlador;
import ModeloDAO.UsuariosDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorImg extends HttpServlet { 
    UsuariosDAO dao = new UsuariosDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        int Id = Integer.parseInt(request.getParameter("Id"));
        dao.listarImg(Id, response);
    }
}

