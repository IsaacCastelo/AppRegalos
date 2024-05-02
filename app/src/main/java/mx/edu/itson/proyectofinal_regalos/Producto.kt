package mx.edu.itson.proyectofinal_regalos

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Producto : AppCompatActivity() {
    val titulo = findViewById<TextView>(R.id.titulo)
    var detalles = ArrayList<Prod>()
    var globos = ArrayList<Prod>()
    var peluches = ArrayList<Prod>()
    var regalos = ArrayList<Prod>()
    var tazas = ArrayList<Prod>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_producto2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var gridProductos = findViewById<GridView>(R.id.gridProductos)
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

    private fun cargarDetalles() {
        detalles.add(Prod(R.drawable.cumplebotanas, "$180"))
        detalles.add(Prod(R.drawable.cumplecheve, "$200"))
        detalles.add(Prod(R.drawable.cumpleescolar, "$250"))
        detalles.add(Prod(R.drawable.cumplepaletas, "$130"))
        detalles.add(Prod(R.drawable.cumplesnack, "$80"))
        detalles.add(Prod(R.drawable.cumplevinos, "$280"))
    }

    class ProductoAdapter: BaseAdapter {
        var productos = ArrayList<Prod>()
        var context: Context? = null

        constructor(
            context: Context,
            productos: ArrayList<Prod>
        ) : super() {
            this.productos = productos
            this.context = context
        }

        override fun getCount(): Int {
            return productos.size
        }

        override fun getItem(pos: Int): Any {
            return productos[pos]
        }

        override fun getItemId(pos: Int): Long {
            return pos.toLong()
        }

        override fun getView(pos: Int,
                             convertView: View?,
                             parent: ViewGroup?): View {
            var producto = productos[pos]
            var inflator = context!!.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var vista = inflator.inflate(R.layout.activity_detalles_productos, null)
            var image: ImageView = vista.findViewById(R.id.image_detalles_cell)
            var title: TextView = vista.findViewById(R.id.detalles_price_cell)

            image.setImageResource(producto.imagen)
            title.setText(producto.precio)

            image.setOnClickListener {
                val intento = Intent(context, DetallesProductos::class.java)
                intento.putExtra("imagen", producto.imagen)
                intento.putExtra("precio", producto.precio)
                context!!.startActivity(intento)
            }

            return vista
        }
    }
}