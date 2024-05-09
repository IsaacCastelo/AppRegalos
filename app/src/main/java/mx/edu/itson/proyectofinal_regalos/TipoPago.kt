package mx.edu.itson.proyectofinal_regalos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class TipoPago : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tipo_pago)

        val btnPagoTienda = findViewById<Button>(R.id.btn_pagoTienda)
        val btnPagoTarjeta = findViewById<Button>(R.id.btn_pagoTarjeta)

        btnPagoTienda.setOnClickListener {
            // Abre la actividad de tipo pago al hacer clic en el botón
            val intent = Intent(this, PagoLocal::class.java)
            startActivity(intent)
        }

        btnPagoTarjeta.setOnClickListener {
            // Abre la actividad de tipo pago al hacer clic en el botón
            val intent = Intent(this, PagoTarjeta::class.java)
            startActivity(intent)
        }
    }
}