package com.example.elementosvisualestarea

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CrudActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    private lateinit var userPasswordEditText: EditText
    private lateinit var userNameEditText: EditText
    private lateinit var userEmailEditText: EditText
    private lateinit var userSearchEditText: EditText

    private lateinit var userListTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        // Initialize Views
        userPasswordEditText = findViewById(R.id.userPasswordEditText)
        userNameEditText = findViewById(R.id.userNameEditText)
        userEmailEditText = findViewById(R.id.userEmailEditText)
        userSearchEditText = findViewById(R.id.userSearchEditText)

        userListTextView = findViewById(R.id.userListTextView)

        val addUserButton: Button = findViewById(R.id.addUserButton)
        val updateUserButton: Button = findViewById(R.id.updateUserButton)
        val deleteUserButton: Button = findViewById(R.id.deleteUserButton)
        val searchUserButton: Button = findViewById(R.id.searchUserButton)
        val fetchAllUsersButton: Button = findViewById(R.id.fetchAllUsersButton)

        // Add User
        addUserButton.setOnClickListener { addUser() }

        // Update User
        updateUserButton.setOnClickListener { updateUser() }

        // Delete User
        deleteUserButton.setOnClickListener { deleteUser() }

        // Search User
        searchUserButton.setOnClickListener { searchUser() }

        // Fetch All Users
        fetchAllUsersButton.setOnClickListener { fetchAllUsers() }
    }

    private fun addUser() {
        val usuario = userNameEditText.text.toString().trim()
        val correo = userEmailEditText.text.toString().trim()
        val contrasena = userPasswordEditText.text.toString().trim()

        if (usuario.isEmpty()) {
            userNameEditText.error = "Por favor escribe el nombre del usuario"
            return
        }

        if (correo.isEmpty()) {
            userEmailEditText.error = "Por favor escribe el correo del usuario"
            return
        }

        if (contrasena.isEmpty() || contrasena.length < 6) {
            userPasswordEditText.error = "La contraseña debe tener al menos 6 caracteres"
            return
        }

        // Primero registra al usuario en Firebase Authentication
        auth.createUserWithEmailAndPassword(correo, contrasena)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Si se registra exitosamente, agrega los datos del usuario a Firestore
                    val newUser = hashMapOf(
                        "usuario" to usuario,
                        "correo" to correo,
                        "contraseña" to contrasena
                    )

                    db.collection("usuarios").document(usuario)
                        .set(newUser)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Usuario agregado correctamente", Toast.LENGTH_SHORT).show()
                            fetchAllUsers() // Actualizar la lista de usuarios en el ListView
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "Error al guardar en la base de datos", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    // Manejar errores de Firebase Authentication
                    Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }


    private fun updateUser() {
        val userPassword = userPasswordEditText.text.toString().trim()
        val userName = userNameEditText.text.toString().trim()
        val userEmail = userEmailEditText.text.toString().trim()

        if (userPassword.isEmpty() || userName.isEmpty() || userEmail.isEmpty()) {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        val user = hashMapOf(
            "correo" to userEmail,
            "contraseña" to userPassword
        )

        db.collection("usuarios").document(userName)
            .update(user as Map<String, Any>)
            .addOnSuccessListener {
                Toast.makeText(this, "Usuario actualizado exitosamente", Toast.LENGTH_SHORT).show()
                clearFields()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error al actualizar el usuario", Toast.LENGTH_SHORT).show()
            }
    }

    private fun deleteUser() {
        val userName = userNameEditText.text.toString().trim()

        if (userName.isEmpty()) {
            Toast.makeText(this, "Por favor ingresa el nombre del usuario", Toast.LENGTH_SHORT).show()
            return
        }

        db.collection("usuarios").document(userName)
            .delete()
            .addOnSuccessListener {
                Toast.makeText(this, "Usuario eliminado exitosamente", Toast.LENGTH_SHORT).show()
                clearFields()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error al eliminar el usuario", Toast.LENGTH_SHORT).show()
            }
    }

    private fun searchUser() {
        val userName = userSearchEditText.text.toString().trim()

        if (userName.isEmpty()) {
            Toast.makeText(this, "Por favor ingresa el nombre del usuario", Toast.LENGTH_SHORT).show()
            return
        }

        db.collection("usuarios").document(userName)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val userName = document.getString("usuario") ?: ""
                    val userPassword = document.getString("contraseña") ?: ""
                    val userEmail = document.getString("correo") ?: ""
                    userNameEditText.setText(userName)
                    userPasswordEditText.setText(userPassword)
                    userEmailEditText.setText(userEmail)
                    Toast.makeText(this, "Usuario encontrado", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error al buscar el usuario", Toast.LENGTH_SHORT).show()
            }
    }

    private fun fetchAllUsers() {
        db.collection("usuarios")
            .get()
            .addOnSuccessListener { documents ->
                val allUsers = StringBuilder()
                for (document in documents) {
                    val userName = document.getString("usuario") ?: ""
                    val userEmail = document.getString("correo") ?: ""
                    val userPassword = document.getString("contraseña") ?: ""
                    allUsers.append(
                        "Usuario: $userName\nCorreo: $userEmail\nContraseña: $userPassword\n\n"
                    )
                }
                userListTextView.text = allUsers.toString()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error al obtener usuarios", Toast.LENGTH_SHORT).show()
            }
    }

    private fun clearFields() {
        userPasswordEditText.text.clear()
        userNameEditText.text.clear()
        userEmailEditText.text.clear()
        userSearchEditText.text.clear()
    }
}
