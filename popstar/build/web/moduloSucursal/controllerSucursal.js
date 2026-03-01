



// MAPA
const map = L.map("map").setView([21.12277913209349, -101.68294446310146], 15);


L.tileLayer("https://tile.openstreetmap.org/{z}/{x}/{y}.png",{
    attribbution: "OpenstreetMap"
}).addTo(map);

var fastFoodIcon = L.icon({iconUrl : "icons/fastfood.png"});

let url = "http://localhost:8080/popstar-servicios2/api/sucursal/getall";
let sucursales = [];

export async function mostrarSucursales(){
    await fetch(url)
            .then(r => r.json())
            .then(info =>{
                sucursales = info;
    });
    sucursales.forEach(e =>{
        var iconoSucursal = L.marker([e.latitud, e.longitud], 
            {icon : fastFoodIcon}).addTo(map);
        iconoSucursal.bindPopup(e.plazaComercial).openPopup();
    });
}





