<%@page session="true"%>
<%@page import="Modelo.Login"%>
<%@page import="ModeloDAO.LoginDAO"%> 
<%@page import="Modelo.Ciclos"%>
<%@page import="ModeloDAO.CiclosDAO"%>
<%@page import="Modelo.Grupos"%>
<%@page import="ModeloDAO.GruposDAO"%>
<%@page import="Modelo.Materias"%>
<%@page import="ModeloDAO.MateriasDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Estudiantes"%>
<%@page import="ModeloDAO.EstudiantesDAO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%  HttpSession sesion = request.getSession();
    String cod = "";
    if (sesion.getAttribute("codigo") != null) {
        cod = sesion.getAttribute("codigo").toString();
    } else {
        out.print("<script>location.replace('index.jsp');</script>");
    }
    LoginDAO logDAO = new LoginDAO();
    Login log = (Login) logDAO.listCodigo(cod);
    EstudiantesDAO dao0 = new EstudiantesDAO();
    int id = Integer.parseInt((String) request.getAttribute("idest"));
    String ciclo = ((String) request.getAttribute("txtciclo"));
    String anio = ((String) request.getAttribute("txtanio"));
    Estudiantes est = (Estudiantes) dao0.listUsuario(id);
%>
<html>
    <head>
        <title>Sistema de Inscripción de Materias</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="icon" href="static/img/icono.ico">
        <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">   
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div class="wrapper d-flex align-items-stretch">
            <nav id="sidebar">
                <div class="custom-menu">
                    <button type="button" id="sidebarCollapse" class="btn btn-info">=</button>
                </div>
                <div class="img bg-wrap text-center py-4" style="background-image: url(static/img/QdyNpXT.jpg);">
                    <div class="user-logo">
                        <div class="img">
                            <img src="ControladorImg?Id=<%=log.getId()%>" width="110" height="110" style="border-radius:50%">
                        </div>
                        <h3><%=log.getNombres()%> <%=log.getApellidos()%></h3>
                    </div>
                </div>
                <ul class="list-unstyled components mb-5">
                    <li class="active"><a href="ControladorUsu?accion=PrincipalEst"><span class="fa fa-home mr-3"></span>INICIO</a></li>
                    <li class="active"><a href="index.jsp?cerrar=true"><span class="fa fa-sign-out-alt mr-3"></span> CERRAR SESIÓN</a></li>
                </ul>
            </nav>
            <div id="content" class="p-4 p-md-5 pt-5">
                <div style="display:grid;height:100%;width:100%;place-items:center;">
                    <div style="margin: 0 auto;padding: 0; width: 910px">                    
                        <h2 class="active" style='color: darkcyan; text-align: center'>INSCRIPCIÓN DE MATERIAS</h2><hr>
                        <div class="form-row">
                            <div class="form-group col-md-5">
                                Materias disponibles:
                                <table class="table table-hover" style="font-size: 13px">
                                    <thead style="background: darkcyan; color: white">
                                        <tr>          
                                            <th class="text-center">MATERIAS</th>
                                            <th class="text-center">UV</th>           
                                        </tr>
                                    </thead>
                                    <%  MateriasDAO dao1 = new MateriasDAO();
                                        List<Materias> list1 = dao1.listar3(ciclo);
                                        Iterator<Materias> iter1 = list1.iterator();
                                        Materias mat1 = null;
                                        while (iter1.hasNext()) {
                                            mat1 = iter1.next();%>
                                    <tbody>
                                        <tr>
                                            <td class="text-center"><%=mat1.getMateria()%></td>
                                            <td class="text-center"><%=mat1.getUv()%></td>
                                        </tr>
                                        <%}%>
                                    </tbody>                
                                </table> <br>
                                Horarios de los grupos:
                                <table class="table table-hover" style="font-size: 13px">
                                    <thead style="background: darkcyan; color: white">
                                        <tr>          
                                            <th class="text-center">GRUPO</th>
                                            <th class="text-center">HORARIO</th>
                                            <th class="text-center">AULA</th>
                                        </tr>
                                    </thead>
                                    <%  GruposDAO daog = new GruposDAO();
                                        List<Grupos> listg = daog.listar2();
                                        Iterator<Grupos> iterg = listg.iterator();
                                        Grupos grup = null;
                                        while (iterg.hasNext()) {
                                            grup = iterg.next();%>
                                    <tbody>
                                        <tr>
                                            <td class="text-center"><%=grup.getGrupo()%></td>
                                            <td class="text-center"><%=grup.getHorario()%></td>
                                            <td class="text-center"><%=grup.getAula()%></td>
                                        </tr>
                                        <%}%>
                                    </tbody>
                                </table>
                            </div>
                            <div class="form-group col-md-1"></div>
                            <div class="form-group col-md-6">
                                <form action="ControladorInscripciones" method="post">
                                    Expediente:<br/>
                                    <input type="text" class="form-control" name="txtexp" value="<%=est.getExpediente()%>" required readonly="readonly"><br/>                    
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label>Ciclo: </label>
                                            <input type="text" class="form-control" name="txtciclo" required value="<%=ciclo%>" readonly>
                                        </div>
                                        <div class="form-group col-md-6">
                                            <label>Año:</label>
                                            <input type="text" class="form-control" name="txtanio" required value="<%=anio%>" readonly>
                                        </div>
                                    </div>   
                                    Seleccionar Materia: <br/>
                                    <select class="form-control" name="txtcodigomat" required>
                                        <option></option>
                                        <%  MateriasDAO dao2 = new MateriasDAO();
                                            List<Materias> list2 = dao2.listar3(ciclo);
                                            Iterator<Materias> iter2 = list2.iterator();
                                            Materias mat = null;
                                            while (iter2.hasNext()) {
                                                mat = iter2.next();%>
                                        <option value="<%=mat.getCodigo_materia()%>"><%=mat.getMateria()%></option>
                                        <%}%>
                                    </select><br/>
                                    Seleccionar Grupo: <br/>
                                    <select class="form-control" name="txtidgr" required>
                                        <option></option>
                                        <%  GruposDAO dao6 = new GruposDAO();
                                            List<Grupos> list6 = dao6.listar2();
                                            Iterator<Grupos> iter6 = list6.iterator();
                                            Grupos gru = null;
                                            while (iter6.hasNext()) {
                                                gru = iter6.next();%>
                                        <option value="<%=gru.getId_grupo()%>"><%=gru.getGrupo()%></option>
                                        <%}%>
                                    </select><br/>                                       
                                    <input type="text" class="form-control" name="txtlugar" value="1" hidden="hidden"/>                                                  
                                    <input class="btn btn-success" type="submit" name="accion" value="Agregar">
                                </form>
                            </div>
                        </div>
                    </div>
                </div> 
            </div>   
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="js/popper.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>
        <script src="js/enlaces.js"></script>
    </body>
</html>
