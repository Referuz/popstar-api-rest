
let url = "http://localhost:8080/popstar-servicios2/api/empleado/getall";
let urlSucursales = "http://localhost:8080/popstar-servicios2/api/sucursal/getall";
let empleados = [];
let sucursales = [];


async function getEmpleados(){
    const respuesta = await fetch(url);
    empleados = await respuesta.json();
}

export async function mostrarEmpleados(){
    await getSucursales();
    await getEmpleados();
    let informacion = document.getElementById("info");
    let administrar = document.getElementById("administrar");
    if(informacion.classList.contains("d-none")){ // si está oculta
        informacion.classList.remove("d-none"); // muestrala
        administrar.classList.add("d-none"); // y oculta eadministrar
    }else{ // si informacion esta visible
        informacion.classList.add("d-none"); // Escondela
    }
    informacion.innerHTML = "";
    empleados.forEach(e=>{
        informacion.appendChild(crearCarta(e));
    });
    
}

function crearCarta(persona){
    //let cardContainer = document.createElement("div");
    let column = document.createElement("div");
    let carta = document.createElement("div");
    let content = "";
    carta.classList = "card my-4 mx-1";
    carta.style.width = "35rem";
    column.classList = "col-md-4 d-flex justify-content-center";
    content = `
        <div class="card-title text-center p-2"><h3>Empleado</h3></div>
        <div class="card-header"><h4>${persona.nombre}</h4></div>
        <div class="card-body">
            <div class="card-text text-center"><h4><b>Descripcion</b></h4></div>
            <br>
            <div class="card-text"><h5><b>Nombre:</b></h5></div>
            <p>${persona.nombre}</p>
            <div class="card-text"><h5><b>Apellidos:</b></h5></div>
            <p>${persona.apellidos}</p>
            <div class="card-text"><h5><b>Telefono:</b></h5></div>
            <p>${persona.telefono}</p>
            <div class="card-text"><h5><b>No. empleado:</b></h5></div>
            <p>${persona.numeroEmpleado}</p>
        </div>
    `;
    carta.innerHTML = content;
    carta.addEventListener("click", function(){
       document.getElementById("txtAdminNombre").value = persona.nombre; 
       document.getElementById("txtAdminApellidos").value = persona.apellidos; 
       document.getElementById("txtAdminTelefono").value = persona.telefono; 
       document.getElementById("selectAdminSucursal").value = persona.sucursal.idSucursal; 
       mostrarAdministracion();
    });
    column.appendChild(carta);
    return column;
}


export function mostrarAdministracion(){
    let administrar = document.getElementById("administrar");
    let informacion = document.getElementById("info");
    if(administrar.classList.contains("d-none")){ // si administrar está oculta
        administrar.classList.remove("d-none"); // muestrala
        informacion.classList.add("d-none"); // oculta la parte "información"
    }else{ // si esta visible
        administrar.classList.add("d-none"); // escondela
    }
}

async function getSucursales(){
    let selector = document.getElementById("selectAdminSucursal");
    selector.innerHTML = `<option value="" selected disable>Selecciona una sucursal...</option>`;
    await fetch(urlSucursales).then(r => r.json()).then(data =>{
        sucursales = data;
        sucursales.forEach(e =>{
            let opcionSelector = document.createElement("option");
            opcionSelector.value = e.idSucursal;
            opcionSelector.innerHTML = e.plazaComercial;
            selector.appendChild(opcionSelector);
        });
    });
}




