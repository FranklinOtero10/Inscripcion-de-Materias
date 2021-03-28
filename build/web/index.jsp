<%@page import="java.math.BigInteger"%>
<%@page import="java.security.MessageDigest"%>
<%@page import="Modelo.Operaciones"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Iniciar Sesión</title>
        <link rel="icon" href="static/img/icono.ico">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/solid.css">
        <link rel="stylesheet" type="text/css" href="static/css/index.css">
    </head>
    <body>
        <div class="modal-dialog text-center">
            <div class="main-section">
                <div class="modal-content">
                    <div class="col-12 user-img">
                        <img src="static/img/avatarr.png" alt="">
                    </div>
                    <form class="col-12" action="">
                        <div class="form-group" id="user-group">
                            <input name="txtCodigo" type="text" class="form-control" placeholder="Código de usuario" required>
                        </div>
                        <div class="form-group" id="contrasena-group">
                            <input name="txtContrasena" type="password" class="form-control" placeholder="Contraseña" required>
                        </div>
                        <button name="btnIniciar" type="submit" class="btn btn-primary"><i class="fas fa-sign-in-alt"></i>  Ingresar</button>
                    </form>
                </div>
                <div style="color: white;">
                    <%  Operaciones op = new Operaciones();
                        if (request.getParameter("btnIniciar") != null) {
                            String codigo = request.getParameter("txtCodigo");
                            String contrasena = request.getParameter("txtContrasena");

                            HttpSession sesion = request.getSession();

                            switch (op.loguear(codigo, getMD5(contrasena))) {
                                case 1:
                                    sesion.setAttribute("codigo", codigo);
                                    sesion.setAttribute("nivel", "1");
                                    response.sendRedirect("ControladorUsu?accion=PrincipalAdm");
                                    break;
                                case 2:
                                    sesion.setAttribute("codigo", codigo);
                                    sesion.setAttribute("nivel", "2");
                                    response.sendRedirect("ControladorUsu?accion=PrincipalEst");
                                    break;
                                default:
                                    out.write("El código no existe, o clave incorrecta");
                                    break;
                            }
                        }
                        if (request.getParameter("cerrar") != null) {
                            session.invalidate();
                        } else {}   %>
                        <%!
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
                        %>
                </div>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="https://use.fontawesome.com/releases/v5.0.7/js/all.js"></script>
    </body>
</html>