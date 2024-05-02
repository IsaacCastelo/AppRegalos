package mx.edu.itson.proyectofinal_regalos

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Productos : AppCompatActivity() {
    val titulo = findViewById<TextView>(R.id.titulo)
    var detalles = ArrayList<Prod>()
    var globos = ArrayList<Prod>()
    var peluches = ArrayList<Prod>()
    var regalos = ArrayList<Prod>()
    var tazas = ArrayList<Prod>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_producto)
        val categoria = intent.getStringExtra("categoria")
        obtenerProductosPorCategoria(categoria)
    }

    private fun obtenerProductosPorCategoria(categoria: String?): Unit {
        return when(categoria) {
            "detalles" -> {
                cargarDetalles()
                titulo.setText("Detalles")
            }
            //"peluches" -> cargarPeluches()
            //"tazas" -> cargarTazas()
            //"regalos" -> cargarRegalos()
            //"globos" -> cargarGlobos()
            else -> {}
        }
    }

    private fun cargarCatalogo() {
        val intent = Intent(this, Catalogo::class.java)
        startActivity(intent)
    }

    private fun cargarDetalles() {
        detalles.add(Prod(R.drawable.cumplebotanas, "$180"))
        detalles.add(Prod(R.drawable.cumplecheve, "$200"))
        detalles.add(Prod(R.drawable.cumpleescolar, "$250"))
        detalles.add(Prod(R.drawable.cumplepaletas, "$130"))
        detalles.add(Prod(R.drawable.cumplesnack, "$80"))
        detalles.add(Prod(R.drawable.cumplevinos, "$280"))
    }
}