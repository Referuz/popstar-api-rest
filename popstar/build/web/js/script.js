let gralController;

async function cargarModuloEmpleado(){
    await fetch("moduloEmpleado/viewEmpleado.html")
            .then(r => r.text())
            .then(content =>{
                document.getElementById("mainContent").innerHTML = content;
                import("../moduloEmpleado/controllerEmpleado.js")
                        .then(function(controller){
                            gralController = controller;
                });
    });
    window.location.href = "#seccionEmpleados";
}

function cargarModuloSucursal(){
    fetch("moduloSucursal/viewSucursal.html")
            .then(r => r.text())
            .then(content =>{
                document.getElementById("mainContent").innerHTML = content;
                import("../moduloSucursal/controllerSucursal.js")
                        .then(function(controller){
                            gralController = controller;
                });
    });
    window.location.href = "#seccionSucursales";
}

document.getElementById("menu").querySelectorAll("button").forEach(y=>{
    y.classList.add(
        "btn", 
        "form-control", 
        "rounded-3",
        //"py-4",
        "me-2",
        "text-dark",
        "custom-btn-header"
    );
});
