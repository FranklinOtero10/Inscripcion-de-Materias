<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Usuarios"%>
<%@page import="ModeloDAO.UsuariosDAO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%  //PAGINACIÓN//
    UsuariosDAO dao = new UsuariosDAO();
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

<h1 style="color: darkcyan; text-align: center">USUARIOS</h1><hr>
<div class="row">
    <div class="col-md-3">
        <button type="button" class="btn btn-success" data-toggle="modal" data-target="#mAgregar">Agregar Nuevo</button>
    </div>
    <div class="col-md-4">
        <ul>
            <c:forEach var="i" begin="1" end="${items}">
                <li style="display: inline;"><a class="btn btn-info" href="/INSCRIPCION/ControladorUsu?accion=Resultado&pags=${i}&regis=${item}">${i}</a> </li>
            </c:forEach>
        </ul>
    </div>
    <div class="col-md-5">
        <form action="ControladorUsu" style="float: right">
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
            <th class="text-center">FOTO</th>
            <th class="text-center">NOMBRES</th>
            <th class="text-center">APELLIDOS</th>
            <th class="text-center">CÓDIGO</th>
            <th class="text-center">ACCIONES</th>
        </tr>
    </thead>
    <%  List<Usuarios> list = dao.listar(paginas, registros);
        Iterator<Usuarios> iter = list.iterator();
        Usuarios usu = null;
        while (iter.hasNext()) {
            usu = iter.next(); %>
    <tbody>
        <tr>
            <td class="text-center"><%=usu.getId()%></td>
            <td><img src="ControladorImg?Id=<%=usu.getId()%>" width="50" height="50" style="margin: auto; display: block"></td>
            <td class="text-center"><%=usu.getNombres()%></td>
            <td class="text-center"><%=usu.getApellidos()%></td>
            <td class="text-center"><%=usu.getCodigo()%></td>
            <td class="text-center">
                <a class="btn btn-info" href="#" data-toggle="modal" data-target="#mVerMas" onclick="editar('<%=usu.getId()%>','<%=usu.getCodigo()%>','<%=usu.getContrasena()%>','<%=usu.getNivel()%>','<%=usu.getNombres()%>','<%=usu.getApellidos()%>','<%=usu.getFoto()%>')"><span class="fas fa-eye"></span></a>
                <a class="btn btn-warning" href="#" data-toggle="modal" data-target="#mEditar" onclick="editar('<%=usu.getId()%>','<%=usu.getCodigo()%>','<%=usu.getContrasena()%>','<%=usu.getNivel()%>','<%=usu.getNombres()%>','<%=usu.getApellidos()%>','<%=usu.getFoto()%>')"><span class="fas fa-edit" style="color:white"></span></a>
                <a class="btn btn-danger" href="#" data-toggle="modal" data-target="#mEliminar" onclick="eliminar('<%=usu.getId()%>')"><span class="fas fa-trash-alt"></span></a>
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
                <form action="ControladorUsu" method="post" enctype="multipart/form-data">
                    Nombres:<br>
                    <input class="form-control" type="text" name="txtNom" maxlength="50" required><br>
                    Apellidos:<br>
                    <input class="form-control" type="text" name="txtApe" maxlength="50" required><br>
                    Código Usuario:<br/>
                    <input class="form-control" type="text" name="txtCod" maxlength="50" required><br/>
                    Contraseña:<br/>
                    <input class="form-control" type="password" name="txtCon" maxlength="50" required><br/>
                    Nivel:<br/>
                    <select class="form-control" name="txtNiv" required>
                        <option>1</option>
                        <option>2</option>
                    </select><br/>
                    Foto de perfil:
                    <input type="file" class="form-control-file" name="txtFoto" required/><br/>
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
                <form action="ControladorUsu" method="post" enctype="multipart/form-data">
                    Nombres:<br>
                    <input class="form-control txtNom" type="text" name="txtNom" maxlength="50" required><br>
                    Apellidos: <br>
                    <input class="form-control txtApe" type="text" name="txtApe" maxlength="50" required><br>
                    Código Usuario: <br/>
                    <input class="form-control txtCod" type="text" name="txtCod" maxlength="50" required><br/>        
                    <div class="form-check form-check-inline">      
                        Contraseña: &nbsp;<br/>
                        <input class="form-check-input" name="chec" type="checkbox" id="chec" value="1" onChange="comprobar(this);"/>
                    </div><br/>
                    <input class="form-control txtCon" type="password" name="txtCon" maxlength="50" required hidden>
                    <input class="form-control" id="boton" type="password" name="txtCon2" maxlength="50" readonly><br>
                    Nivel: <br/>
                    <select class="form-control txtNiv" name="txtNiv" required>
                        <option>1</option>
                        <option>2</option>
                    </select><br/>
                    Foto de perfil:
                    <input type="file" class="form-control-file txtFot" name="txtFoto" required/><br/>
                    <input type="text" class="txtIdU" name="txtId" hidden><br>
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
                    Nombres:<br>
                    <input class="form-control txtNom" type="text" readonly><br>
                    Apellidos: <br>
                    <input class="form-control txtApe" type="text" readonly><br>
                    Código Usuario: <br/>
                    <input class="form-control txtCod" type="text" readonly><br/>
                    Contraseña: <br/>
                    <input class="form-control txtCon" type="password" readonly><br/>
                    Nivel: <br/>
                    <input class="form-control txtNiv" type="text" readonly><br/>
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
                <form action="ControladorUsu" method="post">
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
    editar = function (idU,cod,con,niv,nom,ape,foto) {
        $('.txtIdU').val(idU);
        $('.txtCod').val(cod);
        $('.txtCon').val(con);
        $('.txtNiv').val(niv);
        $('.txtNom').val(nom);
        $('.txtApe').val(ape);
        $('.txtFot').val(foto);
    };
    eliminar = function (idU) {
        $('.mId').val(idU);
    };
    function comprobar(obj){   
        if (obj.checked){
          document.getElementById('boton').readOnly = false;  
          document.getElementById('boton').required = true; 
        }
        else{
          document.getElementById('boton').readOnly = true;  
          document.getElementById('boton').required = false; 
        }
    }
</script>