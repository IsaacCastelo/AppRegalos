package mx.edu.itson.proyectofinal_regalos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Perfil : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        val btnInicio = findViewById<ImageButton>(R.id.Inicio)
        val btnCatalogo = findViewById<ImageButton>(R.id.Catalogo)
        val btnOfertas = findViewById<ImageButton>(R.id.Ofertas)
        val btnCarrito = findViewById<ImageButton>(R.id.Carrito)
        val btnActInfo = findViewById<Button>(R.id.btnActualizarInfo)

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

        btnActInfo.setOnClickListener{
            val intent = Intent(this, EditarPerfil::class.java)
            startActivity(intent)
        }

        // Obtener y mostrar la información del usuario
        showUserInfo()
    }

    private fun showUserInfo() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userRef = database.getReference("usuarios").child(currentUser?.uid ?: "")

        userRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val username = dataSnapshot.child("nombre").getValue(String::class.java)
                    val phoneNumber = dataSnapshot.child("telefono").getValue(String::class.java)

                    // Actualizar los campos de texto en tu layout XML
                    val textViewUsername = findViewById<TextView>(R.id.nombreText)
                    val textViewUsername2 = findViewById<TextView>(R.id.usernameText)
                    val textViewPhoneNumber = findViewById<TextView>(R.id.telefonoText)

                    textViewUsername.text = username
                    textViewUsername2.text = username
                    textViewPhoneNumber.text = phoneNumber
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@Perfil, "Error al cargar la información del usuario: ${databaseError.message}", Toast.LENGTH_SHORT).show()
            }
        })
        val userEmail = currentUser?.email
        val textViewEmail = findViewById<TextView>(R.id.correoText)
        textViewEmail.text = userEmail
    }
}
