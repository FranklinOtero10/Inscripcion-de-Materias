<%@page import="java.util.Calendar"%>
<%@page session="true"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="Modelo.Inscripciones"%>
<%@page import="ModeloDAO.InscripcionesDAO"%>
<%@page import="Modelo.Materias"%>
<%@page import="ModeloDAO.MateriasDAO"%>
<%@page import="Modelo.Grupos"%>
<%@page import="ModeloDAO.GruposDAO"%>
<%@page import="Modelo.Login"%>
<%@page import="ModeloDAO.LoginDAO"%> 

<%  HttpSession sesion = request.getSession();
    String cod = "";
    if (sesion.getAttribute("codigo") != null) {
        cod = sesion.getAttribute("codigo").toString();
    } else {
        out.print("<script>location.replace('index.jsp');</script>");
    }
    LoginDAO logDAO = new LoginDAO();
    Login log = (Login) logDAO.listCodigo(cod);

    String ciclo = ((String) request.getAttribute("txtciclo"));
    String anio = ((String) request.getAttribute("txtanio"));
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
                    <div style="margin: 0 auto;padding: 0; width: 850px">                    
                        <h2 class="active" style='color: darkcyan; text-align: center'>COMPROBANTE DE INSCRIPCIÓN</h2><hr>
                        <table class="table table-hover" >
                            <thead style="background: darkcyan; color: white">
                                <tr>
                                    <th class="text-center">ID INSCRIPCION</th>
                                    <th class="text-center">MATERIA</th>
                                    <th class="text-center">UV</th>
                                    <th class="text-center">GRUPO</th>
                                    <th class="text-center">HORARIO</th>
                                    <th class="text-center">AULA</th>
                                </tr>
                            </thead>
                            <%
                                int id = Integer.parseInt((String) request.getAttribute("idest"));
                                InscripcionesDAO dao = new InscripcionesDAO();
                                MateriasDAO daoM = new MateriasDAO();
                                GruposDAO daoG = new GruposDAO();
                                List<Inscripciones> list = dao.listar3(anio, ciclo, id);
                                Iterator<Inscripciones> iter = list.iterator();
                                Inscripciones insc = null;
                                Materias mat = null;
                                Grupos grp = null;
                                while (iter.hasNext()) {
                                    insc = iter.next();
                                    mat = daoM.list(insc.getCodigo_materia());
                                    grp = daoG.list(insc.getId_grupo());
                            %> 
                            <tbody>
                                <tr>
                                    <td class="text-center"><%=insc.getId_inscripcion()%></td>
                                    <td class="text-center"><%=mat.getMateria()%></td>
                                    <td class="text-center"><%=mat.getUv()%></td>
                                    <td class="text-center"><%=grp.getGrupo()%></td>                                   
                                    <td class="text-center"><%=grp.getHorario()%></td>
                                    <td class="text-center"><%=grp.getAula()%></td>
                                </tr>
                                <%}%>
                            </tbody>                
                        </table>
                        <a class="btn btn-danger" href="Reporte?txtanio=<%=anio%>&txtciclo=<%=ciclo%>&txtexp=<%=id%>"><span class="fa fa fa-print mr-3"></span>OBTENER REPORTE</a>
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