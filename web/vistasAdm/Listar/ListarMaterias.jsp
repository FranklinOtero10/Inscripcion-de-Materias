<%@page import="Modelo.Ciclos"%>
<%@page import="ModeloDAO.CiclosDAO"%>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.List" %>
<%@page import="Modelo.Materias" %>
<%@page import="ModeloDAO.MateriasDAO" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%//PAGINACION
    MateriasDAO dao = new MateriasDAO();
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
<h1 style="color: darkcyan; text-align: center">MATERIAS</h1><hr>
<div class="row">
    <div class="col-md-3">
        <button type="button" class="btn btn-success" data-toggle="modal" data-target="#mAgregar">Agregar Nuevo</button>
    </div>
    <div class="col-md-4">
        <ul>
            <c:forEach var="i" begin="1" end="${items}">
                <li style="display: inline;"><a class="btn btn-info" href="/INSCRIPCION/ControladorMaterias?accion=Resultado&pags=${i}&regis=${item}">${i}</a> </li>
            </c:forEach>
        </ul>
    </div>
    <div class="col-md-5">
        <form action="ControladorMaterias" style="float: right">
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
            <th class="text-center">IDMATERIA</th>
            <th class="text-center">MATERIAS</th>
            <th class="text-center">UV</th>
            <th class="text-center">ID CICLO</th>
            <th class="text-center">PREREQUISITOS</th>
            <th class="text-center">ACCION</th>
        </tr>
    </thead>
    <%  List<Materias> list = dao.listar(paginas, registros);
        Iterator<Materias> iter = list.iterator();
        Materias mat = null;
        while (iter.hasNext()) {
            mat = iter.next(); %>
    <tbody>
        <tr>
            <td class="text-center"><%=mat.getCodigo_materia()%></td>
            <td class="text-center"><%=mat.getMateria()%></td>
            <td class="text-center"><%=mat.getUv()%></td>
            <td class="text-center"><%=mat.getId_ciclo()%></td>
            <td class="text-center"><%=mat.getPrerequisito()%></td>
            <td class="text-center">
                <a class="btn btn-warning" href="#" data-toggle="modal" data-target="#mEditar" onclick="editar('<%=mat.getCodigo_materia()%>','<%=mat.getMateria()%>','<%=mat.getUv()%>','<%=mat.getId_ciclo()%>','<%=mat.getPrerequisito()%>')"><span class="fas fa-edit" style="color:white"></span></a>
                <a class="btn btn-danger" href="#" data-toggle="modal" data-target="#mEliminar" onclick="eliminar('<%=mat.getCodigo_materia()%>')"><span class="fas fa-trash-alt"></span></a>
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
                <form action="ControladorMaterias" method="post">
                    Materia:<br/>
                    <input class="form-control" type="text" name="txtmat" maxlength="100" required><br/>
                    Uv: <br/>
                    <input class="form-control" type="text" name="txtuv" maxlength="2" required/><br/>
                    Id Ciclo :<br/>
                    <select class="form-control" name="txtidciclo" required>
                        <option></option>
                        <%  CiclosDAO dao4 = new CiclosDAO();
                            List<Ciclos> list4 = dao4.listar(paginas, registros);
                            Iterator<Ciclos> iter4 = list4.iterator();
                            Ciclos cic = null;
                            while (iter4.hasNext()) {
                                cic = iter4.next(); %>
                        <option value="<%=cic.getId_ciclo()%>"><%=cic.getCiclo()%></option>
                        <%}%>
                    </select><br/>
                    Prerequisito: <br/>
                    <input class="form-control" type="text" name="txtpre" maxlength="30" required/><br/>
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
                <form action="ControladorMaterias" method="post">
                    Materia: <br/>
                    <input class="form-control txtMat" type="text" name="txtmat" maxlength="100" required/><br/>
                    UV: <br/>
                    <input class="form-control txtUv" type="text" name="txtuv" maxlength="2" required><br/>
                    Id Ciclo : <br/>
                    <select class="form-control txtIdC" name="txtidciclo" required>
                        <%  CiclosDAO dao1 = new CiclosDAO();
                            List<Ciclos> list1 = dao1.listar(paginas, registros);
                            Iterator<Ciclos> iter1 = list1.iterator();
                            Ciclos gru1 = null;
                            while (iter1.hasNext()) {
                                gru1 = iter1.next();%>
                        <option value="<%=gru1.getId_ciclo()%>"><%=gru1.getCiclo()%></option>
                        <%}%>
                    </select><br/>
                    
                    Prerequisito: <br/>
                    <input class="form-control txtPre" type="text" name="txtpre" maxlength="30" required><br/>
                    <input class="txtIdM" type="text" name="txtcodigomat" hidden><br>
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
                <form action="ControladorMaterias" method="post">
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
    editar = function (idM,mat,uv,idC,pre) {
        $('.txtIdM').val(idM);
        $('.txtMat').val(mat);
        $('.txtUv').val(uv);
        $('.txtIdC').val(idC);
        $('.txtPre').val(pre);
    };
    eliminar = function (idM) {
        $('.mId').val(idM);
    };
</script>