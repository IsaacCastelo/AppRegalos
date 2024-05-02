package mx.edu.itson.proyectofinal_regalos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class Registro : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        val btnRegistro: Button = findViewById(R.id.btn_registrar)
        val etNombre: EditText = findViewById(R.id.et_nombre)
        val etEmail: EditText = findViewById(R.id.et_email)
        val etPassword: EditText = findViewById(R.id.et_pass)
        val etTelefono: EditText = findViewById(R.id.et_telefono)

        btnRegistro.setOnClickListener {
            val nombre = etNombre.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val telefono = etTelefono.text.toString()

            if (nombre.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && telefono.isNotEmpty()) {
                registerUser(nombre, email, password, telefono)
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerUser(nombre: String, email: String, password: String, telefono: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Registro exitoso
                    val user = auth.currentUser
                    val userId = user?.uid
                    // Guardar el nombre y el teléfono del usuario en Realtime Database
                    userId?.let {
                        val userData = UserData(nombre, telefono)
                        database.reference.child("usuarios").child(userId).setValue(userData)
                            .addOnSuccessListener {
                                // Datos guardados exitosamente en Realtime Database
                                val intent = Intent(this, Login::class.java)
                                startActivity(intent)
                            }
                            .addOnFailureListener { e ->
                                // Fallo al guardar datos en Realtime Database
                                Toast.makeText(this, "Error al guardar datos en Realtime Database: $e", Toast.LENGTH_SHORT).show()
                            }
                    }
                } else {
                    // Fallo en el registro
                    Toast.makeText(this, "Error en el registro!!", Toast.LENGTH_SHORT).show()
                }
            }
    }

data class UserData(
    val nombre: String = "",
    val telefono: String = ""
)
}
