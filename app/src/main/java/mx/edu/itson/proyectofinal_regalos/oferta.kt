package mx.edu.itson.proyectofinal_regalos

data class oferta(
    val nombre: String,
    val descripcion: String,
    val precioOriginal: Double,
    val precioOferta: Double,
    val imagen: Int
)
