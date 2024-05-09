package mx.edu.itson.proyectofinal_regalos

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.GridView

class Ofertas : AppCompatActivity() {
    private lateinit var ofertas: ArrayList<oferta>
    private lateinit var gridView: GridView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ofertas)

        ofertas = ArrayList()
        ofertas.add(oferta("Botana de cumpleaños", "Caja con globo y algunas botanas", 100.0, 80.0, R.drawable.cumplebotanas))
        ofertas.add(oferta("Caja de cerveza", "Caja especial para regalar en cumpleaños", 200.0, 150.0, R.drawable.cumplecheve))

        // Configurar el GridView
        gridView = findViewById(R.id.gridView)
        gridView.adapter = OfertasAdapter(this, ofertas)

        val btnInicio = findViewById<ImageButton>(R.id.Inicio)
        val btnCatalogo = findViewById<ImageButton>(R.id.Catalogo)
        val btnCarrito = findViewById<ImageButton>(R.id.Carrito)
        val btnPerfil = findViewById<ImageButton>(R.id.Perfil)

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
}