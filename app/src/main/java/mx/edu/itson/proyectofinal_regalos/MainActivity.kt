package mx.edu.itson.proyectofinal_regalos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnInicioEntrar: Button = findViewById(R.id.btn_inicioEntrar)
        btnInicioEntrar.setOnClickListener {
            // Abre la pantalla de login al hacer clic en el botón
            val intent = Intent(this@MainActivity, Login::class.java)
            startActivity(intent)
        }
    }
}