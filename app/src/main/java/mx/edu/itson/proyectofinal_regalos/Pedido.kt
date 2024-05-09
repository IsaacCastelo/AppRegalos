package mx.edu.itson.proyectofinal_regalos
import java.util.*
class Pedido {


    val id: String,
    val productos: List<ProductoEnPedido>,
    var estadoEntrega: EstadoEntrega = EstadoEntrega.PENDIENTE,
    val fechaCreacion: Date = Date()
    )
    { // Función para calcular el total del pedido
        fun calcularTotal(): Double {
            return productos.sumByDouble { it.precioTotal() }
        }

        // Función para agregar un producto al pedido
        fun agregarProducto(producto: Producto, cantidad: Int) {
            val productoEnPedido = ProductoEnPedido(producto, cantidad)
            productos.add(productoEnPedido)
        }

        // Función para actualizar el estado de entrega del pedido
        fun actualizarEstadoEntrega(nuevoEstado: EstadoEntrega) {
            estadoEntrega = nuevoEstado
        }
    }

    data class ProductoEnPedido(
        val producto: Producto,
        var cantidad: Int
    ) {
        fun precioTotal(): Double {
            return producto.precio * cantidad
        }
    }

    data class Producto(
        val nombre: String,
        val precio: Double,
        val imagenResId: Int // ID de recurso de imagen (R.drawable.xxx)
    )

    enum class EstadoEntrega {
        PENDIENTE,
        EN_CAMINO,
        ENTREGADO,
        CANCELADO
    }
