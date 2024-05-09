package mx.edu.itson.proyectofinal_regalos

data class Pedido(
    val id: String? = null,
    val ultimosCuatroDigitosTarjeta: String? = null,
    val direccion: String? = null,
    val Estado: String? = "En Proceso",
    val descripcionCasa: String? = null,
)
