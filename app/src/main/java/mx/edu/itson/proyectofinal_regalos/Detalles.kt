package mx.edu.itson.proyectofinal_regalos

import android.annotation.SuppressLint
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

class Detalles : AppCompatActivity() {
    val titulo = findViewById<TextView>(R.id.titulo)
    var productosAdapter: ProductoAdapter? = null
    var detalles = ArrayList<Prod>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalles)

        cargarDetalles()

        titulo.setText("Detalles")
        productosAdapter = ProductoAdapter(this, detalles)
        var gridProductos = findViewById<GridView>(R.id.gridProductos)
        gridProductos.adapter = productosAdapter
    }

    private fun cargarDetalles() {
        detalles.add(Prod(R.drawable.cumplebotanas, "$180"))
        detalles.add(Prod(R.drawable.cumplecheve, "$200"))
        detalles.add(Prod(R.drawable.cumpleescolar, "$250"))
        detalles.add(Prod(R.drawable.cumplepaletas, "$130"))
        detalles.add(Prod(R.drawable.cumplesnack, "$80"))
        detalles.add(Prod(R.drawable.cumplevinos, "$280"))
    }

    class ProductoAdapter : BaseAdapter {
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

        @SuppressLint("MissingInflatedId")
        override fun getView(
            pos: Int,
            convertView: View?,
            parent: ViewGroup?
        ): View {
            var producto = productos[pos]
            var inflator = context!!.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE
            ) as LayoutInflater
            var vista = inflator.inflate(R.layout.activity_prod_detail, null)
            var image: ImageView = vista.findViewById(R.id.prod_image_cell)
            var title: TextView = vista.findViewById(R.id.prod_price_cell)
            image.setImageResource(producto.imagen)
            title.setText(producto.precio)
            image.setOnClickListener {
                val intento = Intent(context, ProdDetail::class.java)
                intento.putExtra("imagen", producto.imagen)
                intento.putExtra("precio", producto.precio)
                context!!.startActivity(intento)
            }
            return vista
        }
    }
}