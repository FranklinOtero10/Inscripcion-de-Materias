<%@page import="java.util.Iterator" %>
<%@page import="java.util.List" %>
<%@page import="Modelo.Grupos" %>
<%@page import="ModeloDAO.GruposDAO" %>
<%@page import="Modelo.Estudiantes" %>
<%@page import="ModeloDAO.EstudiantesDAO" %>
<%@page import="Modelo.Materias" %>
<%@page import="ModeloDAO.MateriasDAO" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%//PAGINACION
    GruposDAO dao = new GruposDAO();
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
    pageContext.setAttribute("items", pag);
%>
<h1 style="color: darkcyan; text-align: center">GRUPOS</h1><hr>
<div class="row">
    <div class="col-md-3">
        <button type="button" class="btn btn-success" data-toggle="modal" data-target="#mAgregar">Agregar Nuevo</button>
    </div>
    <div class="col-md-4">
        <ul>
            <c:forEach var="i" begin="1" end="${items}">
                <li style="display: inline;"><a class="btn btn-info" href="/INSCRIPCION/ControladorGrupos?accion=Resultado&pags=${i}&regis=${item}">${i}</a> </li>
            </c:forEach>
        </ul>
    </div>
    <div class="col-md-5">
        <form action="ControladorGrupos" style="float: right">
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
            <th class="text-center">ID GRUPO</th>
            <th class="text-center">GRUPO</th>
            <th class="text-center">HORARIO</th>
            <th class="text-center">AULA</th>
            <th class="text-center">ACCION</th>
        </tr>
    </thead>
    <%  List<Grupos> list = dao.listar(paginas, registros);
        Iterator<Grupos> iter = list.iterator();
        Grupos gru = null;
        while (iter.hasNext()) {
            gru = iter.next(); 
    %>
    <tbody>
        <tr>
            <td class="text-center"><%=gru.getId_grupo()%></td>
            <td class="text-center"><%=gru.getGrupo()%></td>
            <td class="text-center"><%=gru.getHorario()%></td>
            <td class="text-center"><%=gru.getAula() %></td>
            <td class="text-center">
                <a class="btn btn-warning" href="#" data-toggle="modal" data-target="#mEditar" onclick="editar('<%=gru.getId_grupo()%>','<%=gru.getGrupo()%>','<%=gru.getHorario()%>','<%=gru.getAula()%>')"><span class="fas fa-edit" style="color:white"></span></a>
                <a class="btn btn-danger" href="#" data-toggle="modal" data-target="#mEliminar" onclick="eliminar('<%=gru.getId_grupo()%>')"><span class="fas fa-trash-alt"></span></a>
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
                <form action="ControladorGrupos" method="post">
                    Grupo:<br/>
                    <input type="text" class="form-control" name="txtgrupo" required>
                        <br/>
                    Horario:<br/>
                    <input type="text" class="form-control" name="txthorario" required>
                        <br/>
                    Aula:<br/>
                    <input type="text" class="form-control" name="txtaula" required>
                        <br/>
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
                <form action="ControladorGrupos" method="post">
                    Grupo :<br/>
                    <input type="text" class="form-control txtgrupo" name="txtgrupo" required>
                        <br/>
                    Horario :<br/>
                    <input type="text" class="form-control txtHorario" name="txthorario" required>
                        <br/>
                    Aula :<br/>
                    <input type="text" class="form-control txtAula" name="txtaula" required>
                        <br/>
                    <input class="txtIdG" type="text" name="txtidgru" hidden><br>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                        <input class="btn btn-warning" type="submit" name="accion" value="Actualizar"> 
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
                <form action="ControladorGrupos" method="post">
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
    editar = function (idG,grupo,horario,aula) {
        $('.txtIdG').val(idG);
        $('.txtgrupo').val(grupo);
        $('.txtHorario').val(horario);
        $('.txtAula').val(aula);
    };
    eliminar = function (idG) {
        $('.mId').val(idG);
    };
</script>
