<%@page session="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Modelo.Login"%>
<%@page import="ModeloDAO.LoginDAO"%> 
<%  HttpSession sesion = request.getSession();
    String cod = "";
    if (sesion.getAttribute("codigo") != null) {
        cod = sesion.getAttribute("codigo").toString();   
    } else {
        out.print("<script>location.replace('index.jsp');</script>");
    }   LoginDAO logDAO = new LoginDAO();
        Login log = (Login) logDAO.listCodigo(cod); %>                               
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
                    <li class="active"><a href="/INSCRIPCION/Controlador/ControladorUsu?accion=Inicio" onclick="abrirIni(event)"><span class="fa fa-home mr-3"></span>INICIO</a></li>
                    <li class="active"><a href="#accesoSubmenu" data-toggle="collapse" aria-expanded="false"><span class="fa fa-caret-down mr-3"></span>  ACCESO</a>
                        <ul style="background-color: teal" class="collapse list-unstyled" id="accesoSubmenu">
                            <li class="active"><a href="/INSCRIPCION/Controlador/ControladorAdm?accion=listarAdm" onclick="abrirAdm(event)"><span class="fa fa-address-card mr-3"></span>ADMINISTRADORES</a>
                            <li class="active"><a href="/INSCRIPCION/Controlador/ControladorEst?accion=listarEst" onclick="abrirEst(event)"><span class="fa fa-user-circle mr-3"></span> ESTUDIANTES</a></li>
                            <li class="active"><a href="/INSCRIPCION/Controlador/ControladorUsu?accion=listarUsu" onclick="abrirUsu(event)"><span class="fas fa-user-friends mr-3"></span> USUARIOS</a></li>
                        </ul>
                    </li>
                    <li class="active"><a href="#homeSubmenu" data-toggle="collapse" aria-expanded="false"><span class="fa fa-caret-down mr-3"></span>  PROCESO MATERIAS</a>
                        <ul style="background-color: teal" class="collapse list-unstyled" id="homeSubmenu">
                            <li class="active"><a href="/INSCRIPCION/Controlador/ControladorMaterias?accion=listarMaterias" onclick="abrirMaterias(event)"><span class="fa fa-address-book mr-3"></span> MATERIAS</a></li>                                                      
                            <li class="active"><a href="/INSCRIPCION/Controlador/ControladorGrupos?accion=listarGru" onclick="abrirGrupos(event)"><span class="fa fa-users mr-3"></span>GRUPOS</a></li>                           
                        </ul>
                    </li>
                    <li class="active"><a href="${pageContext.servletContext.contextPath}/Controlador/ControladorCarreras?accion=listarCarreras" onclick="abrirCarreras(event)"><span class="fa fa-list-alt mr-3"></span>CARRERAS</a></li>
                    <li class="active"><a href="/INSCRIPCION/Controlador/ControladorInscripciones?accion=listarInscripciones" onclick="abrirInscripciones(event)"><span class="fa fa-chalkboard-teacher mr-3"></span>INSCRIPCIONES</a></li>
                    
                    <li class="active"><a href="/INSCRIPCION/Controlador/ControladorUsu?accion=AcercaDe" onclick="abrirAcer(event)"><span class="fas fa-bullhorn mr-3"></span> ACERCA DE</a></li>
                    <li class="active"><a  href="index.jsp?cerrar=true"><span class="fa fa-sign-out-alt mr-3"></span> CERRAR SESIÓN</a></li>
                </ul>
            </nav>

            <div id="content" class="p-4 p-md-5 pt-5">
                <%if (request.getParameter("opc").equals("1")) {%>
                    <%@include file="Inicio.jsp"%>
                <%} else if (request.getParameter("opc").equals("2")) {%>
                    <%@include file="Listar/ListarUsu.jsp"%>
                <%} else if (request.getParameter("opc").equals("3")) {%>
                    <%@include file="Listar/ListarAdm.jsp"%>
                <%} else if (request.getParameter("opc").equals("4")) {%>
                    <%@include file="Listar/ListarEst.jsp"%>
                <%}else if (request.getParameter("opc").equals("5")) {%>
                    <%@include file="Listar/ListarCarreras.jsp"%>
                <%}else if (request.getParameter("opc").equals("6")) {%>
                    <%@include file="Listar/ListarMaterias.jsp"%>                                          
                <%}else if (request.getParameter("opc").equals("7")) {%>
                    <%@include file="Listar/ListarGrupos.jsp"%>                        
                <%}else if (request.getParameter("opc").equals("8")) {%>
                    <%@include file="Listar/ListarInscripciones.jsp"%>
                <%}%>
            </div>   
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="js/popper.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>
        <script src="js/enlaces.js"></script>
    </body>
</html>