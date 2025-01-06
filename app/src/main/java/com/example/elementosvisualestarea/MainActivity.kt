package com.example.elementosvisualestarea

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    val nombres = arrayOf("Anakin", "ObiWan", "Ashoka", "Luke")

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializa Firebase
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val sesionBtn = findViewById<Button>(R.id.sesion)
        sesionBtn.setOnClickListener { buscarCorreo() }

        val registerLinkBtn = findViewById<Button>(R.id.registerLinkBtn)
        registerLinkBtn.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        val actv = findViewById<AutoCompleteTextView>(R.id.actUsuario)
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

    private fun buscarCorreo() {
        val usuario = findViewById<AutoCompleteTextView>(R.id.actUsuario)
        val contra = findViewById<EditText>(R.id.contra)

        val usuTexto = usuario.text.toString().trim()
        val contraTexto = contra.text.toString().trim()

        if (usuTexto.isEmpty()) {
            usuario.error = "Por favor escribe tu usuario"
            return
        }

        if (contraTexto.isEmpty()) {
            contra.error = "Por favor escribe tu contraseña"
            return
        }

        db.collection("usuarios")
            .whereEqualTo("usuario", usuTexto)
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
                } else {
                    for (doc in documents) {
                        val correoTexto = doc.getString("correo") ?: ""
                        iniciarSesion(correoTexto, contraTexto)
                        return@addOnSuccessListener
                    }
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error al buscar el usuario", Toast.LENGTH_SHORT).show()
            }
    }

    private fun iniciarSesion(correo: String, contra: String) {
        val Lbt = findViewById<RadioButton>(R.id.RbuttonLuz)
        val Obt = findViewById<RadioButton>(R.id.RbuttonOsc)

        auth.signInWithEmailAndPassword(correo, contra)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                    if (Lbt.isChecked) {
                        startActivity(Intent(this, MainActivity2::class.java))
                        finish()
                    } else if (Obt.isChecked) {
                        startActivity(Intent(this, MainActivity4::class.java))
                        finish()
                    }
                } else {
                    val exception = task.exception
                    when (exception) {
                        is FirebaseAuthInvalidCredentialsException ->
                            Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
                        is FirebaseAuthInvalidUserException ->
                            Toast.makeText(this, "Usuario no registrado", Toast.LENGTH_SHORT).show()
                        else ->
                            Toast.makeText(this, "Error: ${exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }
}

fun AutoCompleteTextView.setOnClickListener(onItemClickListener: AdapterView.OnItemClickListener) {

}
