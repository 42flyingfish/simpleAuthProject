package com.example.simpleauthproject

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mAuth = FirebaseAuth.getInstance()

        val registerButton = findViewById<Button>(R.id.registerButton)

        registerButton.setOnClickListener {
            val emailEditText = findViewById<EditText>(R.id.emailEditText)
            val email = emailEditText.text.toString()

            val paswordEditText = findViewById<EditText>(R.id.passwordEditText)
            val password = paswordEditText.text.toString()

            //Log.d("Whooo", "onCreate: $email $password")

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {task ->
                if (task.isSuccessful) {
                    Toast.makeText(this,
                        "Registration Successful",
                        Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this,
                        "Registration Failed: " + task.exception?.message.toString(),
                        Toast.LENGTH_SHORT).show()
                }
            }
        }

        val loginButton = findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener {
            val emailEditText = findViewById<EditText>(R.id.emailEditText)
            val email = emailEditText.text.toString()

            val paswordEditText = findViewById<EditText>(R.id.passwordEditText)
            val password = paswordEditText.text.toString()

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this,
                        "Login Successful",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}