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

class Productos : AppCompatActivity() {
    var productosAdapter: ProductoAdapter? = null
    var detalles = ArrayList<Prod>()
    var globos = ArrayList<Prod>()
    var regalos = ArrayList<Prod>()
    var tazas = ArrayList<Prod>()
    var peluches = ArrayList<Prod>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_productos)

        val categoria = intent.getStringExtra("categoria")
        obtenerProductosPorCategoria(categoria)
    }

    private fun obtenerProductosPorCategoria(categoria: String?) : Unit {
        return when (categoria) {
            "detalles" -> {
                cargarDetalles()
                var gridProductos =
                    findViewById<GridView>(R.id.gridProductos)
                productosAdapter =
                    ProductoAdapter(this, detalles)
                gridProductos.adapter = productosAdapter
                val titulo = findViewById<TextView>(R.id.titulo)
                titulo.setText("Detalles")
            }
            "globos" -> {
                cargarGlobos()
                var gridProductos =
                    findViewById<GridView>(R.id.gridProductos)
                productosAdapter =
                    ProductoAdapter(this, globos)
                gridProductos.adapter = productosAdapter
                val titulo = findViewById<TextView>(R.id.titulo)
                titulo.setText("Globos")
            }
            "peluches" -> {
                cargarPeluches()
                var gridProductos =
                    findViewById<GridView>(R.id.gridProductos)
                productosAdapter =
                    ProductoAdapter(this, peluches)
                gridProductos.adapter = productosAdapter
                val titulo = findViewById<TextView>(R.id.titulo)
                titulo.setText("Peluches")
            }
            "tazas" -> {
                cargarTazas()
                var gridProductos =
                    findViewById<GridView>(R.id.gridProductos)
                productosAdapter =
                    ProductoAdapter(this, tazas)
                gridProductos.adapter = productosAdapter
                val titulo = findViewById<TextView>(R.id.titulo)
                titulo.setText("Tazas")
            }
            "regalos" -> {
                cargarRegalos()
                var gridProductos =
                    findViewById<GridView>(R.id.gridProductos)
                productosAdapter =
                    ProductoAdapter(this, regalos)
                gridProductos.adapter = productosAdapter
                val titulo = findViewById<TextView>(R.id.titulo)
                titulo.setText("Regalos")
            }
            else -> {}
        }
    }

    private fun cargarDetalles() {
        detalles.add(Prod(R.drawable.cumplebotanas, "$180"))
        detalles.add(Prod(R.drawable.cumplecheve, "$200"))
        detalles.add(Prod(R.drawable.cumpleescolar, "$250"))
        detalles.add(Prod(R.drawable.cumplepaletas, "$135"))
        detalles.add(Prod(R.drawable.cumplesnack, "$80"))
        detalles.add(Prod(R.drawable.cumplevinos, "$285"))
    }

    private fun cargarGlobos() {
        globos.add(Prod(R.drawable.globoamor, "$150"))
        globos.add(Prod(R.drawable.globocumple, "$140"))
        globos.add(Prod(R.drawable.globofestejo, "$210"))
        globos.add(Prod(R.drawable.globonum, "$200"))
        globos.add(Prod(R.drawable.globoregalo, "$75"))
    }

    private fun cargarPeluches() {
        peluches.add(Prod(R.drawable.peluchemario, "$145"))
        peluches.add(Prod(R.drawable.pelucheminecraft, "$180"))
        peluches.add(Prod(R.drawable.peluchepeppa, "$130"))
        peluches.add(Prod(R.drawable.peluchesony, "$200"))
        peluches.add(Prod(R.drawable.peluchestich, "$190"))
        peluches.add(Prod(R.drawable.peluchevarios, "$255"))
    }

    private fun cargarTazas() {
        tazas.add(Prod(R.drawable.tazaabuela, "$150"))
        tazas.add(Prod(R.drawable.tazalove, "$175"))
        tazas.add(Prod(R.drawable.tazaquiero, "$150"))
    }

    private fun cargarRegalos() {
        regalos.add(Prod(R.drawable.regaloazul, "$200"))
        regalos.add(Prod(R.drawable.regalobebe, "$225"))
        regalos.add(Prod(R.drawable.regalocajas, "$190"))
        regalos.add(Prod(R.drawable.regalocolores, "$175"))
        regalos.add(Prod(R.drawable.regalovarios, "$260"))
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