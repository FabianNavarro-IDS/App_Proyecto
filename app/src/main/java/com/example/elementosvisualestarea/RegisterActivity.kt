package com.example.elementosvisualestarea

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {

    val nombres = arrayOf("Anakin", "ObiWan", "Ashoka", "Luke")

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inicializa Firebase
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val registerBtn = findViewById<Button>(R.id.registerBtn)
        registerBtn.setOnClickListener { registerUser() }

        val actv = findViewById<AutoCompleteTextView>(R.id.registerUsuario)
        val adapter = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1, nombres)

        actv.setAdapter(adapter)
        actv.setOnClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            Toast.makeText(
                applicationContext,
                "Nombre seleccionado = " + nombres[position],
                Toast.LENGTH_SHORT
            ).show()
        })
    }

    private fun registerUser() {
        val usuario = findViewById<AutoCompleteTextView>(R.id.registerUsuario)
        val correo = findViewById<EditText>(R.id.registerCorreo)
        val contra = findViewById<EditText>(R.id.registerContra)

        val usuTexto = usuario.text.toString().trim()
        val correoTexto = correo.text.toString().trim()
        val contraTexto = contra.text.toString().trim()

        if (usuTexto.isEmpty()) {
            usuario.error = "Por favor escribe tu usuario"
            return
        }

        if (correoTexto.isEmpty()) {
            correo.error = "Por favor escribe tu correo"
            return
        }

        if (contraTexto.isEmpty() || contraTexto.length < 6) {
            contra.error = "La contraseña debe tener al menos 6 caracteres"
            return
        }

        // Crear Usuario
        auth.createUserWithEmailAndPassword(correoTexto, contraTexto)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Guardar en Firestore
                    val user = hashMapOf(
                        "usuario" to usuTexto,
                        "correo" to correoTexto,
                        "contraseña" to contraTexto
                    )

                    db.collection("usuarios").document(usuTexto)
                        .set(user)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "Error al guardar en la base de datos", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}