package com.example.aluno.myapplication

import android.widget.TextView

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById<Button>(R.id.textview1)
        editText = findViewById(R.id.editText1)
    }

    fun testeClick(view: View){
        var Variavel_Texto = editText.text.toString();

        Toast.makeText(this, "Hello World", Toast.LENGTH_SHORT).show();

        textView.text = editText.text.toString();
    }
}
