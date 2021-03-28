<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Administradores"%>
<%@page import="ModeloDAO.AdministradoresDAO"%>
<%@page import="Modelo.Usuarios"%>
<%@page import="ModeloDAO.UsuariosDAO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%  //PAGINACIÓN//
    AdministradoresDAO dao = new AdministradoresDAO();
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

<h1 style="color: darkcyan; text-align: center">ADMINISTRADORES</h1><hr>
<div class="row">
    <div class="col-md-3">
        <button type="button" class="btn btn-success" data-toggle="modal" data-target="#mAgregar">Agregar Nuevo</button>
    </div>
    <div class="col-md-4">
        <ul>
            <c:forEach var="i" begin="1" end="${items}">
                <li style="display: inline;"><a class="btn btn-info" href="/INSCRIPCION/ControladorAdm?accion=Resultado&pags=${i}&regis=${item}">${i}</a> </li>
            </c:forEach>
        </ul>
    </div>
    <div class="col-md-5">
        <form action="ControladorAdm" style="float: right">
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
            <th class="text-center">ID</th>
            <th class="text-center">TELÉFONO</th>
            <th class="text-center">EMAIL</th>
            <th class="text-center">CARGO</th>
            <th class="text-center">ACCIONES</th>
        </tr>
    </thead>
    <%  List<Administradores> list = dao.listar(paginas, registros);
        Iterator<Administradores> iter = list.iterator();
        Administradores adm = null;
        while (iter.hasNext()) {
            adm = iter.next();%>
    <tbody>
        <tr>
            <td class="text-center"><%=adm.getId_administrador()%></td>
            <td class="text-center"><%=adm.getTelefono()%></td>
            <td class="text-center"><%=adm.getEmail()%></td>
            <td class="text-center"><%=adm.getCargo()%></td>
            <td class="text-center">
                <a class="btn btn-info" href="#" data-toggle="modal" data-target="#mVerMas" onclick="editar('<%=adm.getId_administrador()%>','<%=adm.getTelefono()%>','<%=adm.getEmail()%>','<%=adm.getDireccion()%>','<%=adm.getSexo()%>','<%=adm.getCargo()%>','<%=adm.getIdusuario()%>')"><span class="fas fa-eye"></span></a>
                <a class="btn btn-warning" href="#" data-toggle="modal" data-target="#mEditar" onclick="editar('<%=adm.getId_administrador()%>','<%=adm.getTelefono()%>','<%=adm.getEmail()%>','<%=adm.getDireccion()%>','<%=adm.getSexo()%>','<%=adm.getCargo()%>','<%=adm.getIdusuario()%>')"><span class="fas fa-edit" style="color:white"></span></a>
                <a class="btn btn-danger" href="#" data-toggle="modal" data-target="#mEliminar" onclick="eliminar('<%=adm.getId_administrador()%>')"><span class="fas fa-trash-alt"></span></a>
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
                <form action="ControladorAdm" method="post">
                    Teléfono:<br/>
                    <input class="form-control" type="text" name="txtTel" maxlength="15" required><br/>
                    Email:<br/>
                    <input class="form-control" type="email" name="txtEma" maxlength="30" required><br/>
                    Direccion:<br/>
                    <textarea class="form-control" name="txtDir" maxlength="100" required></textarea><br/>
                    Sexo:<br/>
                    <select class="form-control" name="txtSex" required>
                        <option>Masculino</option>
                        <option>Femenino</option>
                    </select><br/>
                    Cargo:<br/>
                    <select class="form-control" name="txtCar" required>
                        <option>Contador</option>
                        <option>Administrador</option>
                        <option>Ingeniero</option>
                        <option>Licenciado</option>
                    </select><br/>
                    Id Usuario:<br/>
                    <select class="form-control" name="txtIdU" required>
                        <option></option>
                        <%  UsuariosDAO dao2 = new UsuariosDAO();
                            List<Usuarios> list2 = dao2.listar2();
                            Iterator<Usuarios> iter2 = list2.iterator();
                            Usuarios usu = null;
                            while (iter2.hasNext()) {
                                usu = iter2.next();%>
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
                <form action="ControladorAdm" method="post">
                    Telefono: <br/>
                    <input class="form-control txtTel" type="text" name="txtTel" maxlength="15" required><br/>
                    Email: <br/>
                    <input class="form-control txtEma" type="email" name="txtEma" maxlength="30" required><br/>
                    Direccion: <br/>
                    <textarea class="form-control txtDir" name="txtDir" maxlength="100" required></textarea><br/>
                    Sexo: <br/>
                    <select class="form-control txtSex" name="txtSex" required>
                        <option>Masculino</option>
                        <option>Femenino</option>
                    </select><br/>
                    Cargo: <br/>
                    <select class="form-control txtCar" name="txtCar" required>
                        <option>Contador</option>
                        <option>Administrador</option>
                        <option>Ingeniero</option>
                        <option>Licenciado</option>
                    </select><br/>
                    Id Usuario:<br/>
                    <select class="form-control txtIdU" name="txtIdU" required>
                        <option></option>
                        <%  UsuariosDAO dao3 = new UsuariosDAO();
                            List<Usuarios> list3 = dao3.listar2();
                            Iterator<Usuarios> iter3 = list3.iterator();
                            Usuarios usu2 = null;
                            while (iter3.hasNext()) {
                               usu2 = iter3.next();%>
                        <option value="<%=usu2.getId()%>"><%=usu2.getCodigo()%></option>
                        <%}%>
                    </select><br/>
                    <input type="text" name="txtid" class="txtId" hidden><br>
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
                    Telefono: <br/>
                    <input class="form-control txtTel" type="text" readonly><br/>
                    Email: <br/>
                    <input class="form-control txtEma" type="email" readonly><br/>
                    Direccion: <br/>
                    <textarea class="form-control txtDir" readonly></textarea><br/>
                    Sexo: <br/>
                    <input class="form-control txtSex" type="text" readonly><br/>
                    Cargo: <br/>
                    <input class="form-control txtCar" type="text" readonly><br>
                    Id Usuario: <br/>
                    <input class="form-control txtIdU" type="text" readonly><br>
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
                <form action="ControladorAdm" method="post">
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
    editar = function (idA,tel,ema,dir,sex,car,idU) {
        $('.txtId').val(idA);
        $('.txtTel').val(tel);
        $('.txtEma').val(ema);
        $('.txtDir').val(dir);
        $('.txtSex').val(sex);
        $('.txtCar').val(car);
        $('.txtIdU').val(idU);
    };
    eliminar = function (idA) {
        $('.mId').val(idA);
    };
</script>