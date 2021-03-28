function abrirIni(event) {
    event.preventDefault();
    $.get("/INSCRIPCION/ControladorUsu?accion=Inicio", function (data) {
        $("#content").html(data);
    });
}
function abrirAdm(event) {
    event.preventDefault();
    $.get("/INSCRIPCION/ControladorAdm?accion=listarAdm", function (data) {
        $("#content").html(data);
    });
}
function abrirEst(event) {
    event.preventDefault();
    $.get("/INSCRIPCION/ControladorEst?accion=listarEst", function (data) {
        $("#content").html(data);
    });
}
function abrirUsu(event) {
    event.preventDefault();
    $.get("/INSCRIPCION/ControladorUsu?accion=listarUsu", function (data) {
        $("#content").html(data);
    });
}
function abrirAcer(event) {
    event.preventDefault();
    $.get("/INSCRIPCION/ControladorUsu?accion=AcercaDe", function (data) {
        $("#content").html(data);
    });
}
function abrirCarreras(event){
    event.preventDefault();
    $.get("/INSCRIPCION/ControladorCarreras?accion=listarCarreras", function (data){
       $("#content").html(data); 
    });
}
function abrirMaterias(event){
    event.preventDefault();
    $.get("/INSCRIPCION/ControladorMaterias?accion=listarMaterias", function (data){
        $("#content").html(data);
    });
}
function abrirHorarios(event){
    event.preventDefault();
    $.get("/INSCRIPCION/ControladorHorarios?accion=listarHorarios", function (data){
        $("#content").html(data);
    });
}
function abrirGrupos(event){
    event.preventDefault();
    $.get("/INSCRIPCION/ControladorGrupos?accion=listarGru", function (data){
        $("#content").html(data);
    });
}
function abrirInscripciones(event){
    event.preventDefault();
    $.get("/INSCRIPCION/ControladorInscripciones?accion=listarInscripciones", function (data){
        $("#content").html(data);
    });
}

