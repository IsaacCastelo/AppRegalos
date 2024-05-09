package mx.edu.itson.proyectofinal_regalos

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Ofertas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ofertas)

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