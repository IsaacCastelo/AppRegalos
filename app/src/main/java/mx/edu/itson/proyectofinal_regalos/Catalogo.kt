package mx.edu.itson.proyectofinal_regalos

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Catalogo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_catalogo)

        // MOSTRAR PRODUCTOS DEL CATÁLOGO
        var btnDetalles = findViewById<ImageButton>(R.id.detallesBtn)
        var btnGlobos = findViewById<ImageButton>(R.id.globosBtn)
        var btnRegalos = findViewById<ImageButton>(R.id.regalosBtn)
        var btnPeluches = findViewById<ImageButton>(R.id.peluchesBtn)
        var btnTazas = findViewById<ImageButton>(R.id.tazasBtn)

        btnDetalles.setOnClickListener {
            mostrarProductos(btnDetalles.tag.toString())
        }

        btnGlobos.setOnClickListener {
            mostrarProductos(btnGlobos.tag.toString())
        }

        btnRegalos.setOnClickListener {
            mostrarProductos(btnRegalos.tag.toString())
        }

        btnPeluches.setOnClickListener {
            mostrarProductos(btnPeluches.tag.toString())
        }

        btnTazas.setOnClickListener {
            mostrarProductos(btnTazas.tag.toString())
        }

        // MOSTRAR PESTAÑAS ADICIONALES
        val btnInicio = findViewById<ImageButton>(R.id.Inicio)
        val btnOfertas = findViewById<ImageButton>(R.id.Ofertas)
        val btnCarrito = findViewById<ImageButton>(R.id.Carrito)
        val btnPerfil = findViewById<ImageButton>(R.id.Perfil)

        btnInicio.setOnClickListener {
            // Abre la pantalla de inicio al hacer clic en el botón
            val intent = Intent(this, InicioTienda::class.java)
            startActivity(intent)
        }

        btnOfertas.setOnClickListener {
            // Abre la pantalla de ofertas al hacer clic en el botón
            val intent = Intent(this, Ofertas::class.java)
            startActivity(intent)
        }

        btnCarrito.setOnClickListener {
            // Abre la pantalla de carrito al hacer clic en el botón
            val intent = Intent(this, Carrito::class.java)
            startActivity(intent)
        }

        btnPerfil.setOnClickListener {
            // Abre la pantalla de perfil al hacer clic en el botón
            val intent = Intent(this, Perfil::class.java)
            startActivity(intent)
        }
    }

    private fun mostrarProductos(categoria: String) {
        val intent = Intent(this, Productos::class.java)
        intent.putExtra("categoria", categoria)
        startActivity(intent)
    }
}