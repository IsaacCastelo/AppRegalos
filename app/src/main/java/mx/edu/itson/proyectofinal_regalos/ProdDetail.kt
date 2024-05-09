package mx.edu.itson.proyectofinal_regalos

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class ProdDetail : AppCompatActivity() {
    private lateinit var producto: Prod

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prod_detail)

        val bundle = intent.extras
        val prod_image_cell : ImageView = findViewById(R.id.prod_image_cell)
        val prod_price_cell : TextView = findViewById(R.id.prod_price_cell)
        val btnAgregarCarrito : Button = findViewById(R.id.btnAgregarCarrito)

        if (bundle != null) {
            val imagen = bundle.getInt("imagen")
            val precio = bundle.getString("precio") ?: ""
            prod_image_cell.setImageResource(bundle.getInt("imagen"))
            prod_price_cell.text = bundle.getString("precio")
            producto = Prod(imagen, precio)
            btnAgregarCarrito.visibility = View.VISIBLE
        }

        btnAgregarCarrito.setOnClickListener {
            agregarAlCarrito()
        }
    }

    private fun agregarAlCarrito() {
        // TODO: AGREGAR EL PRODUCTO AL CARRITO CORRECTAMENTE
        mostrarMensaje("Producto añadido exitosamente al carrito")
    }

    private fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}