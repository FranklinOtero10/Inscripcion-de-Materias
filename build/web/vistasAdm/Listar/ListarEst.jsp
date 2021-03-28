<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Estudiantes"%>
<%@page import="ModeloDAO.EstudiantesDAO"%>
<%@page import="Modelo.Usuarios"%>
<%@page import="ModeloDAO.UsuariosDAO"%>
<%@page import="Modelo.Carreras"%>
<%@page import="ModeloDAO.CarrerasDAO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%  //PAGINACIÓN//
    EstudiantesDAO dao = new EstudiantesDAO();
    int totalRegistros = dao.getTodos();
    int registros = Integer.parseInt((String) request.getAttribute("reg"));
    int paginas = Integer.parseInt((String) request.getAttribute("pag"));
    int pag = 0;
    if (totalRegistros % registros != 0) {
        pag = (totalRegistros / registros) + 1;
    } else {
        pag = totalRegistros / registros;
    }
    pageContext.setAttribute("item", registros);
    pageContext.setAttribute("items", pag); %>

<h1 style="color: darkcyan; text-align: center">ESTUDIANTES</h1><hr>
<div class="row">
    <div class="col-md-3">
        <button type="button" class="btn btn-success" data-toggle="modal" data-target="#mAgregar">Agregar Nuevo</button>
    </div>
    <div class="col-md-4">
        <ul>
            <c:forEach var="i" begin="1" end="${items}">
                <li style="display: inline;"><a class="btn btn-info" href="/INSCRIPCION/ControladorEst?accion=Resultado&pags=${i}&regis=${item}">${i}</a> </li>
            </c:forEach>
        </ul>
    </div>
    <div class="col-md-5">
        <form action="ControladorEst" style="float: right">
            Mostrar por página:
            <input type="text" name="register" maxlength="2" style="max-width: 50px" required/>
            <input type="hidden" name="pages" value="1"/>
            <input class="btn btn-primary" type="submit" name="accion" value="Resultado"/>
        </form>
    </div>
</div>

<table class="table table-hover" >
    <thead style="background: darkcyan; color: white">
        <tr>
            <th class="text-center">EXP</th>
            <th class="text-center">FECHA NAC</th>
            <th class="text-center">DUI</th>
            <th class="text-center">ACCIONES</th>
        </tr>
    </thead>
    <%  List<Estudiantes> list = dao.listar(paginas, registros);
        Iterator<Estudiantes> iter = list.iterator();
        Estudiantes est = null;
        while (iter.hasNext()) {
            est = iter.next(); %>
    <tbody>
        <tr>
            <td class="text-center"><%=est.getExpediente()%></td>
            <td class="text-center"><%=est.getFecha_nacimiento()%></td>
            <td class="text-center"><%=est.getDui()%></td>
            <td class="text-center">
                <a class="btn btn-info" href="#" data-toggle="modal" data-target="#mVerMas" onclick="editar('<%=est.getExpediente()%>','<%=est.getFecha_nacimiento()%>','<%=est.getDireccion()%>','<%=est.getSexo()%>','<%=est.getDui()%>','<%=est.getId_carrera()%>','<%=est.getIdusuario()%>')"><span class="fas fa-eye"></span></a>
                <a class="btn btn-warning" href="#" data-toggle="modal" data-target="#mEditar" onclick="editar('<%=est.getExpediente()%>','<%=est.getFecha_nacimiento()%>','<%=est.getDireccion()%>','<%=est.getSexo()%>','<%=est.getDui()%>','<%=est.getId_carrera()%>','<%=est.getIdusuario()%>')"><span class="fas fa-edit" style="color:white"></span></a>
                <a class="btn btn-danger" href="#" data-toggle="modal" data-target="#mEliminar" onclick="eliminar('<%=est.getExpediente()%>')"><span class="fas fa-trash-alt"></span></a>
            </td>
        </tr>
        <%}%>
    </tbody>                
</table> 

<div class="modal fade" id="mAgregar" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title active" style='color: darkcyan'>Agregar Registro</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form action="ControladorEst" method="post">
                    Fecha Nacimiento:<br/>
                    <input class="form-control" type="text" name="txtFec" maxlength="15" required><br/>
                    Direccion:<br/>
                    <textarea class="form-control" name="txtDir" maxlength="100" required></textarea><br/>
                    Sexo:<br/>
                    <select class="form-control" name="txtSex" required>
                        <option>Masculino</option>
                        <option>Femenino</option>
                    </select><br/>
                    DUI:<br/>
                    <input class="form-control" type="text" name="txtDui" maxlength="10" required><br/>
                    Id Carrera:<br/>
                    <select class="form-control" name="txtIdC" required>
                        <option></option>
                        <%  CarrerasDAO dao3 = new CarrerasDAO();
                            List<Carreras> list3 = dao3.listar2();
                            Iterator<Carreras> iter3 = list3.iterator();
                            Carreras car = null;
                            while (iter3.hasNext()) {
                                car = iter3.next(); %>
                        <option value="<%=car.getId_carrera()%>"><%=car.getCarrera()%></option>
                        <%}%>
                    </select><br/>
                    Id Usuario:<br/>
                    <select class="form-control" name="txtIdU" required>
                        <option></option>
                        <%  UsuariosDAO dao2 = new UsuariosDAO();
                            List<Usuarios> list2 = dao2.listar2();
                            Iterator<Usuarios> iter2 = list2.iterator();
                            Usuarios usu = null;
                            while (iter2.hasNext()) {
                                usu = iter2.next(); %>
                        <option value="<%=usu.getId()%>"><%=usu.getCodigo()%></option>
                        <%}%>
                    </select><br/>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                        <input class="btn btn-success" type="submit" name="accion" value="Agregar">
                    </div>
                </form>      
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="mEditar" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title active" style='color: darkcyan'>Editar Registro</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form action="ControladorEst" method="post">
                    Fecha nacimiento: <br/>
                    <input class="form-control txtFec" type="text" name="txtFec" maxlength="15" required><br/>
                    Direccion: <br/>
                    <textarea class="form-control txtDir" name="txtDir" maxlength="100" required></textarea><br/>
                    Sexo: <br/>
                    <select class="form-control txtSex" name="txtSex" required>
                        <option>Masculino</option>
                        <option>Femenino</option>
                    </select><br/>
                    DUI: <br/>
                    <input class="form-control txtDui" type="text" name="txtDui" maxlength="10" required><br/>
                    Id Carrera:<br/>
                    <select class="form-control txtIdC" name="txtIdC" required>
                        <%  CarrerasDAO dao4 = new CarrerasDAO();
                            List<Carreras> list4 = dao4.listar2();
                            Iterator<Carreras> iter4 = list4.iterator();
                            Carreras car2 = null;
                            while (iter4.hasNext()) {
                                car2 = iter4.next();%>
                        <option value="<%=car2.getId_carrera()%>"><%=car2.getCarrera()%></option>
                        <%}%>
                    </select><br/>
                    Id Usuario:<br/>
                    <select class="form-control txtIdU" name="txtIdU" required>
                        <%  UsuariosDAO dao5 = new UsuariosDAO();
                            List<Usuarios> list5 = dao5.listar2();
                            Iterator<Usuarios> iter5 = list5.iterator();
                            Usuarios usu2 = null;
                            while (iter5.hasNext()) {
                                usu2 = iter5.next();%>
                        <option value="<%=usu2.getId()%>"><%=usu2.getCodigo()%></option>
                        <%}%>
                    </select><br/>
                    <input class="txtExp" type="text" name="txtExp" hidden><br>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                        <input class="btn btn-warning" type="submit" name="accion" value="Actualizar"> 
                    </div>
                </form>     
            </div>
        </div>
    </div>
</div> 

<div class="modal fade" id="mVerMas" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title active" style='color: darkcyan'>Información del registro</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form>
                    Fecha nacimiento: <br/>
                    <input class="form-control txtFec" type="text" readonly><br/>
                    Direccion: <br/>
                    <textarea class="form-control txtDir" readonly></textarea><br/>
                    Sexo: <br/>
                    <input class="form-control txtSex" type="text" readonly><br/>
                    DUI: <br/>
                    <input class="form-control txtDui" type="text" readonly><br/>
                    Id Carrera: <br/>
                    <input class="form-control txtIdC" type="text" readonly><br/>
                    Id Usuario: <br/>
                    <input class="form-control txtIdU" type="text" readonly><br/>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Aceptar</button>
                    </div>
                </form>       
            </div>
        </div>
    </div>
</div>                     
                    
<div class="modal fade" id="mEliminar" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle" style='color: darkcyan'>Eliminar Registro</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <h6>¿Realmente desea eliminar el registro?</h6>
                <form action="ControladorEst" method="post">
                    <input type="text" name="id" class="mId" hidden>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                        <input class="btn btn-danger" type="submit" name="accion" value="Eliminar"> 
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>                   
                    
<script>
    editar = function (exp,fec,dir,sex,dui,idC,idU) {
        $('.txtExp').val(exp);
        $('.txtFec').val(fec);
        $('.txtDir').val(dir);
        $('.txtSex').val(sex);
        $('.txtDui').val(dui);
        $('.txtIdC').val(idC);
        $('.txtIdU').val(idU);
    };
    eliminar = function (idE) {
        $('.mId').val(idE);
    };
</script>