package mx.edu.itson.proyectofinal_regalos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val btnIngresar: Button = findViewById(R.id.btn_ingresar)
        val btnRegistrar: Button = findViewById(R.id.btn_registrar)

        btnIngresar.setOnClickListener {
            // Abre la pantalla de InicioTienda al hacer clic en el botón
            val intent = Intent(this@Login, InicioTienda::class.java)
            startActivity(intent)
        }

        btnRegistrar.setOnClickListener {
            val intent = Intent(this@Login, Registro::class.java)
            startActivity(intent)
        }
    }
}