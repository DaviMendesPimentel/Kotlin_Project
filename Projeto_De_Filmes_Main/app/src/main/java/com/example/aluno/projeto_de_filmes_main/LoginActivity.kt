package com.example.aluno.projeto_de_filmes_main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {


    private lateinit var mAuth : FirebaseAuth
    private lateinit var mAuthListener : FirebaseAuth.AuthStateListener

    private lateinit var etUsuario : EditText
    private lateinit var etSenha : EditText
    private lateinit var etConfirm : Button
    private lateinit var etCreate : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        mAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener { it
            val user = it.currentUser
            if(user != null){
                var intent1 = Intent(this, MainActivity::class.java)
                startActivity(intent1)
                finish()
            }
        }
        etUsuario = findViewById(R.id.login)
        etSenha = findViewById(R.id.password)
        etConfirm = findViewById(R.id.login_Button)
        etCreate = findViewById(R.id.account_Create)

        etCreate.setOnClickListener { createAccount(it, etUsuario.text.toString(), etSenha.text.toString()) }
        etConfirm.setOnClickListener { signIn(it, etUsuario.text.toString(), etSenha.text.toString()) }
    }

    override fun onStart() {
        super.onStart()
        mAuth.addAuthStateListener(mAuthListener)
    }

    override fun onStop() {
        super.onStop()
        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener)
        }
    }

    fun signIn(view: View, email: String, password: String){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this)
        {
            if(!it.isSuccessful){
                showMessage(view, "erro: ${it.exception?.message}");
            }
        }
    }
    fun createAccount(view: View, email: String, password: String){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            if(!it.isSuccessful){
                showMessage(view, "erro: ${it.exception?.message}")
            }
        }
    }
    fun showMessage(view: View, message: String){
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }
}
