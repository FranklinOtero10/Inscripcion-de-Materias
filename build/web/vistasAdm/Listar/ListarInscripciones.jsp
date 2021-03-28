<%@page import="ModeloDAO.CiclosDAO"%>
<%@page import="Modelo.Ciclos"%>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.List" %>
<%@page import="Modelo.Inscripciones" %>
<%@page import="ModeloDAO.InscripcionesDAO" %>
<%@page import="Modelo.Materias" %>
<%@page import="ModeloDAO.MateriasDAO" %>
<%@page import="Modelo.Estudiantes" %>
<%@page import="ModeloDAO.EstudiantesDAO" %>
<%@page import="Modelo.Grupos" %>
<%@page import="ModeloDAO.GruposDAO" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%//PAGINACION
    InscripcionesDAO dao = new InscripcionesDAO();
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
<h1 style="color: darkcyan; text-align: center">INSCRIPCIONES</h1><hr>
<div class="row">
    <div class="col-md-3">
        <button type="button" class="btn btn-success" data-toggle="modal" data-target="#mAgregar">Agregar Nuevo</button>
    </div>
    <div class="col-md-4">
        <ul>
            <c:forEach var="i" begin="1" end="${items}">
                <li style="display: inline;"><a class="btn btn-info" href="/INSCRIPCION/ControladorInscripciones?accion=Resultado&pags=${i}&regis=${item}">${i}</a> </li>
            </c:forEach>
        </ul>
    </div>
    <div class="col-md-5">
        <form action="ControladorInscripciones" style="float: right">
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
            <th class="text-center">ID INSCRIPCION</th>
            <th class="text-center">ID GRUPO</th>
            <th class="text-center">ID CICLO</th>
            <th class="text-center">CODIGO MATERIA</th>
            <th class="text-center">EXPEDIENTE</th>
            <th class="text-center">AÑO</th>
            <th class="text-center">ACCIONES</th>
        </tr>
    </thead>
    <%  List<Inscripciones> list = dao.listar(paginas, registros);
        Iterator<Inscripciones> iter = list.iterator();
        Inscripciones insc = null;
        while (iter.hasNext()) {
            insc = iter.next(); %> 
    <tbody>
        <tr>
            <td class="text-center"><%=insc.getId_inscripcion()%></td>
            <td class="text-center"><%=insc.getId_grupo()%></td>
            <td class="text-center"><%=insc.getId_ciclo()%></td>
            <td class="text-center"><%=insc.getCodigo_materia()%></td>
            <td class="text-center"><%=insc.getExpediente()%></td>
            <td class="text-center"><%=insc.getAnio()%></td>
            <td class="text-center">
                <a class="btn btn-warning" href="#" data-toggle="modal" data-target="#mEditar" onclick="editar('<%=insc.getId_inscripcion()%>','<%=insc.getId_grupo()%>','<%=insc.getId_ciclo()%>','<%=insc.getCodigo_materia()%>','<%=insc.getExpediente()%>','<%=insc.getAnio()%>')"><span class="fas fa-edit" style="color:white"></span></a>
                <a class="btn btn-danger" href="#" data-toggle="modal" data-target="#mEliminar" onclick="eliminar('<%=insc.getId_inscripcion()%>')"><span class="fas fa-trash-alt"></span></a>
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
                <form action="ControladorInscripciones" method="post">
                    Id Grupo : <br/>
                    <select class="form-control" name="txtidgr" required>
                        <option></option>
                        <%  GruposDAO dao6 = new GruposDAO();
                            List<Grupos> list6 = dao6.listar2();
                            Iterator<Grupos> iter6 = list6.iterator();
                            Grupos gru = null;
                            while (iter6.hasNext()) {
                              gru = iter6.next(); %>
                        <option value="<%=gru.getId_grupo()%>"><%=gru.getGrupo()%></option>
                        <%}%>
                    </select><br/>
                    Id Ciclo :<br/>
                    <select class="form-control" name="txtciclo" required>
                        <option></option>
                        <%  CiclosDAO dao4 = new CiclosDAO();
                            List<Ciclos> list4 = dao4.listar2();
                            Iterator<Ciclos> iter4 = list4.iterator();
                            Ciclos cic = null;
                            while (iter4.hasNext()) {
                                cic = iter4.next(); %>
                        <option value="<%=cic.getId_ciclo()%>"><%=cic.getCiclo()%></option>
                        <%}%>
                    </select><br/>
                    Código Materia : <br/>
                    <select class="form-control" name="txtcodigomat" required>
                        <option></option>
                        <%  MateriasDAO dao2 = new MateriasDAO();
                            List<Materias> list2 = dao2.listar2();
                            Iterator<Materias> iter2 = list2.iterator();
                            Materias mat = null;
                            while (iter2.hasNext()) {
                                mat = iter2.next(); %>
                        <option value="<%=mat.getCodigo_materia()%>"><%=mat.getMateria()%></option>
                        <%}%>
                    </select><br/>
                    Expediente :<br/>
                    <select class="form-control" name="txtexp" required>
                        <option></option>
                        <%  EstudiantesDAO dao3 = new EstudiantesDAO();
                            List<Estudiantes> list3 = dao3.listar2();
                            Iterator<Estudiantes> iter3 = list3.iterator();
                            Estudiantes est = null;
                            while (iter3.hasNext()) {
                                est = iter3.next(); %>
                        <option value="<%=est.getExpediente()%>"><%=est.getExpediente()%></option>
                        <%}%>
                    </select><br/>                   
                    Año : <br/>
                    <input type="text" class="form-control" name="txtanio" required><br/>  
                    <input type="text" class="form-control" name="txtlugar" value="0" hidden="hidden"/>    
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
                <form action="ControladorInscripciones" method="post">
                    Id Grupo : <br/>
                    <select class="form-control txtIdG" name="txtidgr" required>
                        <%  GruposDAO dao1 = new GruposDAO();
                            List<Grupos> list1 = dao1.listar2();
                            Iterator<Grupos> iter1 = list1.iterator();
                            Grupos gru1 = null;
                            while (iter1.hasNext()) {
                                gru1 = iter1.next();%>
                        <option value="<%=gru1.getId_grupo()%>"><%=gru1.getId_grupo()%></option>
                        <%}%>
                    </select><br/>
                    Código Materia : <br/>
                    <select class="form-control txtCod" name="txtcodigomat" required>
                        <%  MateriasDAO dao7 = new MateriasDAO();
                            List<Materias> list7 = dao7.listar2();
                            Iterator<Materias> iter7 = list7.iterator();
                            Materias mat2 = null;
                            while (iter7.hasNext()) {
                                mat2 = iter7.next();%>
                        <option value="<%=mat2.getCodigo_materia()%>"><%=mat2.getMateria()%></option>
                        <%}%>
                    </select><br/>
                    Expediente :<br/>
                    <select class="form-control txtExp" name="txtexp" required>
                        <%  EstudiantesDAO dao8 = new EstudiantesDAO();
                            List<Estudiantes> list8 = dao8.listar2();
                            Iterator<Estudiantes> iter8 = list8.iterator();
                            Estudiantes est2 = null;
                            while (iter8.hasNext()) {
                                est2 = iter8.next();%>
                        <option value="<%=est2.getExpediente()%>"><%=est2.getExpediente()%></option>
                        <%}%>
                    </select><br/>
                    Id Ciclo :<br/>
                    <select class="form-control txtIdC" name="txtidcic" required>
                        <%  CiclosDAO dao9 = new CiclosDAO();
                            List<Ciclos> list9 = dao9.listar2();
                            Iterator<Ciclos> iter9 = list9.iterator();
                            Ciclos hor2 = null;
                            while (iter9.hasNext()) {
                                hor2 = iter9.next();%>
                        <option value="<%=hor2.getId_ciclo()%>"><%=hor2.getCiclo()%></option>
                        <%}%>
                    </select><br/>
                    Anio : <br/>
                    <input type="text" class="form-control txtanio" name="txtanio" required><br/>                  
                    <input class="txtIdI" type="text" name="txtidin" hidden><br>
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
                <form action="ControladorInscripciones" method="post">
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
    editar = function (idI,idG,idC,cod,exp,ani) {
        $('.txtIdI').val(idI);
        $('.txtIdG').val(idG);
        $('.txtIdC').val(idC);
        $('.txtCod').val(cod);
        $('.txtExp').val(exp);
        $('.txtanio').val(ani);
    };
    eliminar = function (idI) {
        $('.mId').val(idI);
    };
</script>