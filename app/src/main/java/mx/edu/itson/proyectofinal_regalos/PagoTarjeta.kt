package mx.edu.itson.proyectofinal_regalos

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import mx.edu.itson.proyectofinal_regalos.PagoProcesado
import mx.edu.itson.proyectofinal_regalos.Pedido
import mx.edu.itson.proyectofinal_regalos.R

class PagoTarjeta : AppCompatActivity() {

    private lateinit var carritoRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pago_tarjeta)

        carritoRef = FirebaseDatabase.getInstance().reference.child("carrito")

        val btnConfirmarPago = findViewById<Button>(R.id.btn_confirmar_pago)

        // Agregar el TextWatcher al campo de número de tarjeta
        val etNumeroTarjeta = findViewById<EditText>(R.id.et_numeroTarjeta)
        etNumeroTarjeta.addTextChangedListener(CreditCardNumberFormattingTextWatcher(etNumeroTarjeta))
        // Limitar la longitud máxima del campo de número de tarjeta
        etNumeroTarjeta.filters = arrayOf(android.text.InputFilter.LengthFilter(19))

        btnConfirmarPago.setOnClickListener {
            realizarPedido()
            limpiarCampos()
            val intent = Intent(this, PagoProcesado::class.java)
            startActivity(intent)
        }
    }

    private fun realizarPedido() {
        // Genera un ID simple y legible para el pedido
        val pedidoId = generarIdPedido()

        // Aquí establecemos el estado del pedido como "En Proceso"
        val estado = "En Proceso"

        // Obtener los últimos cuatro dígitos de la tarjeta de pago
        val ultimosCuatroDigitosTarjeta = findViewById<EditText>(R.id.et_numeroTarjeta).text.toString().takeLast(4)

        // Obtener la dirección del usuario
        val direccion = obtenerDireccion()

        // Validar el mes de expiración
        val mesExpiracion = findViewById<EditText>(R.id.et_mes_expiracion)?.text?.toString()?.toIntOrNull()
        if (mesExpiracion == null || mesExpiracion !in 1..12) {
            Toast.makeText(this, "Por favor ingresa un mes de expiración válido (1-12)", Toast.LENGTH_SHORT).show()
            return
        }

        // Guardar el pedido en la base de datos
        val pedido = Pedido(pedidoId, ultimosCuatroDigitosTarjeta, direccion, estado)
        guardarPedidoEnFirebase(pedido)
    }

    private fun obtenerDireccion(): String {
        val calle = findViewById<EditText>(R.id.calleDir)?.text?.toString() ?: ""
        val numero = findViewById<EditText>(R.id.numCasa)?.text?.toString() ?: ""
        val descripcion = findViewById<EditText>(R.id.descripcionCasa)?.text?.toString() ?: ""

        return "$calle, #$numero, $descripcion"
    }

    private fun guardarPedidoEnFirebase(pedido: Pedido) {
        val pedidoId = pedido.id ?: ""
        val pedidoRef = FirebaseDatabase.getInstance().reference.child("pedidos").child(pedidoId)
        pedidoRef.setValue(pedido)
            .addOnSuccessListener {
                // Si se guarda correctamente, muestra un mensaje de confirmación
                Toast.makeText(this, "Pedido realizado con éxito", Toast.LENGTH_SHORT).show()

                // Limpia el carrito después de realizar el pedido
                limpiarCarrito()

            }
            .addOnFailureListener { e ->
                // Si ocurre un error al guardar el pedido, muestra un mensaje de error
                Log.e(TAG, "Error al guardar el pedido en Firebase: ${e.message}")
                Toast.makeText(this, "Error al realizar el pedido. Inténtalo de nuevo más tarde", Toast.LENGTH_SHORT).show()
            }
    }

    private fun limpiarCarrito() {
        // Limpia el carrito eliminando todos los productos
        carritoRef.removeValue()
    }

    private fun generarIdPedido(): String {
        val timestamp = System.currentTimeMillis()
        return "pedido_$timestamp"
    }

    private fun limpiarCampos(){
        val etNombreTitular = findViewById<EditText>(R.id.et_nombreTitular).text.clear()
        val etNumeroTarjeta = findViewById<EditText>(R.id.et_numeroTarjeta).text.clear()
        val etMesExpiracion = findViewById<EditText>(R.id.et_mes_expiracion).text.clear()
        val etAnioExpiracion = findViewById<EditText>(R.id.et_anio_expiracion).text.clear()
        val etCVV = findViewById<EditText>(R.id.et_cvv).text.clear()
        val calleDir = findViewById<EditText>(R.id.calleDir).text.clear()
        val numCasa = findViewById<EditText>(R.id.numCasa).text.clear()
        val descripcionCasa = findViewById<EditText>(R.id.descripcionCasa).text.clear()
    }

    // Clase para formatear automáticamente el número de tarjeta de crédito en grupos de 4 dígitos
    private class CreditCardNumberFormattingTextWatcher(private val editText: EditText) : TextWatcher {

        private var isFormatting = false

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // No se necesita implementar
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // No se necesita implementar
        }

        override fun afterTextChanged(s: Editable?) {
            // Evita ciclos infinitos al formatear el texto
            if (isFormatting) {
                return
            }

            // Indica que estamos formateando el texto
            isFormatting = true

            // Limpia cualquier separador previo
            val originalText = s.toString().replace("\\s".toRegex(), "")

            // Formatea los números de tarjeta de crédito en grupos de 4
            val formattedText = StringBuilder()
            var i = 0
            while (i < originalText.length) {
                formattedText.append(originalText.substring(i, kotlin.math.min(i + 4, originalText.length)))
                if (i + 4 < originalText.length) {
                    formattedText.append(" ")
                }
                i += 4
            }

            // Actualiza el texto del campo de texto con los números formateados
            editText.setText(formattedText.toString())
            editText.setSelection(formattedText.length)

            // Indica que hemos terminado de formatear el texto
            isFormatting = false
        }
    }
}
