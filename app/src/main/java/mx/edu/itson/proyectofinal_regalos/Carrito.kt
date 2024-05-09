package mx.edu.itson.proyectofinal_regalos

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Carrito : AppCompatActivity() {
    private lateinit var carritoRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        carritoRef = FirebaseDatabase.getInstance().reference.
        child("carrito")

        obtenerProductosDelCarrito()

        val btnInicio = findViewById<ImageButton>(R.id.Inicio)
        val btnCatalogo = findViewById<ImageButton>(R.id.Catalogo)
        val btnOfertas = findViewById<ImageButton>(R.id.Ofertas)
        val btnPerfil = findViewById<ImageButton>(R.id.Perfil)
        val btnTipoPago = findViewById<Button>(R.id.btn_inicioEntrar)

        btnInicio.setOnClickListener {
            // Abre la pantalla de inicio al hacer clic en el botón
            val intent = Intent(this, InicioTienda::class.java)
            startActivity(intent)
        }

        btnCatalogo.setOnClickListener {
            // Abre la pantalla de catálogo al hacer clic en el botón
            val intent = Intent(this, Catalogo::class.java)
            startActivity(intent)
        }

        btnOfertas.setOnClickListener {
            // Abre la pantalla de ofertas al hacer clic en el botón
            val intent = Intent(this, Ofertas::class.java)
            startActivity(intent)
        }

        btnPerfil.setOnClickListener {
            // Abre la pantalla de perfil al hacer clic en el botón
            val intent = Intent(this, Perfil::class.java)
            startActivity(intent)
        }

        btnTipoPago.setOnClickListener {
            // Abre la actividad de tipo pago al hacer clic en el botón
            val intent = Intent(this, TipoPago::class.java)
            startActivity(intent)
        }
    }

    private fun obtenerProductosDelCarrito() {
        var contenedorProductos: LinearLayout = findViewById(R.id.contenedorProductos)
        var totalPrecio = 0.0
        carritoRef.addValueEventListener(object : ValueEventListener {
            @SuppressLint("StringFormatInvalid")
            override fun onDataChange(snapshot: DataSnapshot) {
                contenedorProductos.removeAllViews()
                for (productoSnapshot in snapshot.children) {
                    val imagen = productoSnapshot.child("imagen").getValue(Int::class.java) ?: 0
                    val precioS = productoSnapshot.child("precio").getValue(String::class.java) ?: ""

                    val precio = precioS.toDoubleOrNull() ?: 0.0
                    totalPrecio += precio

                    val productoView = crearProductoView(imagen, precioS)
                    contenedorProductos.addView(productoView)
                }
                val precioTotal: TextView = findViewById(R.id.precioTotal)
                precioTotal.text = getString(R.string.total, totalPrecio)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, "Error al leer datos desde Firebase: ${error.message}")
            }
        })
    }

    private fun crearProductoView(imagen: Int,
                                  precio: String): View {
        val productoView = layoutInflater.inflate(R.layout.item_producto_carrito, null)
        val prodImage: ImageView = productoView.findViewById(R.id.prodImage)
        val prodPrecio: TextView = productoView.findViewById(R.id.prodPrecio)

        prodImage.setImageResource(imagen)
        prodPrecio.text = precio

        return productoView
    }
}