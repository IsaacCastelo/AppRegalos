package mx.edu.itson.proyectofinal_regalos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class EditarPerfil : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var currentUser: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_perfil)

        val btnInicio = findViewById<ImageButton>(R.id.Inicio)
        val btnCatalogo = findViewById<ImageButton>(R.id.Catalogo)
        val btnOfertas = findViewById<ImageButton>(R.id.Ofertas)
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

        btnPerfil.setOnClickListener{
            val intent = Intent(this, Perfil::class.java)
            startActivity(intent)
        }

        auth = FirebaseAuth.getInstance()
        currentUser = auth.currentUser!!
        database = FirebaseDatabase.getInstance()

        val editNameButton = findViewById<TextView>(R.id.edit_name_button)
        val nameEditText = findViewById<EditText>(R.id.name_edittext)

        val editPhoneButton = findViewById<TextView>(R.id.edit_phone_button)
        val phoneEditText = findViewById<EditText>(R.id.phone_edittext)

        val editPasswordButton = findViewById<TextView>(R.id.edit_password_button)

        editNameButton.setOnClickListener {
            val newName = nameEditText.text.toString()
            updateUserProfile("nombre", newName)
        }


        editPhoneButton.setOnClickListener {
            val newPhone = phoneEditText.text.toString()
            updateUserProfile("telefono", newPhone)
        }

        editPasswordButton.setOnClickListener {
            sendPasswordResetEmail()
        }


    }

    private fun updateUserProfile(field: String, value: String) {
        val userRef = database.getReference("usuarios").child(currentUser.uid)
        userRef.child(field).setValue(value)
            .addOnSuccessListener {
                Toast.makeText(this, "Perfil actualizado exitosamente", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al actualizar el perfil: $e", Toast.LENGTH_SHORT).show()
            }
    }


    private fun sendPasswordResetEmail() {
        auth.sendPasswordResetEmail(currentUser.email!!)
            .addOnSuccessListener {
                Toast.makeText(this, "Se ha enviado un correo electrónico para restablecer la contraseña", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al enviar el correo electrónico para restablecer la contraseña: $e", Toast.LENGTH_SHORT).show()
            }
    }
}
