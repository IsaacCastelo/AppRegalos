package mx.edu.itson.proyectofinal_regalos

import android.content.Intent
import android.os.Bundle
import android.widget.GridView
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class Ofertas : AppCompatActivity() {
    private lateinit var ofertas: ArrayList<oferta>
    private lateinit var gridView: GridView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ofertas)

        // Inicializar la lista de ofertas
        ofertas = ArrayList()
        ofertas.add(oferta("Botana de cumpleaños", "Caja con globo y algunas botanas", 100.0, 80.0, R.drawable.cumplebotanas))
        ofertas.add(oferta("Caja de cerveza", "Caja especial para regalar en cumpleaños", 200.0, 150.0, R.drawable.cumplecheve))

        // Configurar el GridView
        gridView = findViewById(R.id.gridView)
        gridView.adapter = OfertasAdapter(this, ofertas)

        // Configuración de los botones de navegación
        val btnInicio = findViewById<ImageButton>(R.id.Inicio)
        val btnCatalogo = findViewById<ImageButton>(R.id.Catalogo)
        val btnCarrito = findViewById<ImageButton>(R.id.Carrito)
        val btnPerfil = findViewById<ImageButton>(R.id.Perfil)
        btnInicio.setOnClickListener {
            val intent = Intent(this, InicioTienda::class.java)
            startActivity(intent)
        }
        btnCatalogo.setOnClickListener {
            val intent = Intent(this, Catalogo::class.java)
            startActivity(intent)
        }
        btnCarrito.setOnClickListener {
            val intent = Intent(this, Carrito::class.java)
            startActivity(intent)
        }
        btnPerfil.setOnClickListener {
            val intent = Intent(this, Perfil::class.java)
            startActivity(intent)
        }
    }
}
