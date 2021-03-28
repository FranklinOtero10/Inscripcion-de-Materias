<%@page import="ModeloDAO.MateriasDAO"%>
<%@page import="Modelo.Estudiantes"%>
<%@page import="ModeloDAO.EstudiantesDAO"%>
<%@page import="ModeloDAO.InscripcionesDAO"%>
<%@page import="Modelo.Inscripciones"%>
<%@page session="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Modelo.Login"%>
<%@page import="ModeloDAO.LoginDAO"%> 
<%@page import="java.util.Calendar"%> 
<%  HttpSession sesion = request.getSession();
    String cod = "";
    if (sesion.getAttribute("codigo") != null) {
        cod = sesion.getAttribute("codigo").toString();
    } else {
        out.print("<script>location.replace('index.jsp');</script>");
    }
    LoginDAO logDAO = new LoginDAO();
    Login log = (Login) logDAO.listCodigo(cod);
    EstudiantesDAO daoe = new EstudiantesDAO();
    Estudiantes e = daoe.listUsuario(log.getId());
    Calendar cal = Calendar.getInstance();
    int anio = cal.get(Calendar.YEAR);
    int mes = cal.get(Calendar.MONTH);
    String ciclo = "";
    if (mes > 0 && mes <= 6) {
        ciclo = "01";
    } else {
        ciclo = "01";
    }
    int opc = 1;
    InscripcionesDAO dao1 = new InscripcionesDAO();
    int expediente = e.getExpediente();
    String anio2 = String.valueOf(anio);
    int ins = (int) dao1.listar4(anio2, ciclo, expediente);
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
                <%if (opc == 1) {%>
                <div class="jumbotron">
                    <div class="container">
                        <h1 style="color:teal">Bienvenido, <%=log.getNombres()%>  a la Inscripción de Materias del Ciclo <%=ciclo%>/<%=anio%> </h1> 
                        <p>Antes de empezar, puedes leer los pasos para realizar la inscripción.</p>
                        <%      if (ins < 5) {%>
                        <p><a class="btn btn-primary btn-lg" href="ControladorEst?accion=inscripcion&id=<%=log.getId()%>&txtciclo=<%=ciclo%>&txtanio=<%=anio2%>" role="button">EMPEZAR AHORA &raquo;</a></p>
                        <%} else {%>
                        <p><a class="btn btn-primary btn-lg" href="ControladorEst?accion=comprobante&id=<%=log.getId()%>&txtciclo=<%=ciclo%>&txtanio=<%=anio2%>" role="button">VER COMPROBANTE DE INSCRIPCION &raquo;</a></p>
                        <%}%>
                    </div>   
                </div>
                <div class="container">
                    <div class="row">
                        <div class="col-md-4">
                            <h2 style="color:teal">Paso 1</h2>
                            <p>Seleccionarás las materias que deberás llevar este ciclo.</p>
                        </div>
                        <div class="col-md-4">
                            <h2 style="color:teal">Paso 2</h2>
                            <p>Seleccionarás los grupos de las materias que elegiste. Después harás click en inscribir materia, y eligirás la siguiente materia hasta completar las cinco.</p>
                        </div>
                        <div class="col-md-4">
                            <h2 style="color:teal">Paso 3</h2>
                            <p>Se mostrará un resumen de las materias inscritas con el grupo escogido, y podrás imprimirlo.</p>
                        </div>
                    </div>
                    <hr>
                </div>
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