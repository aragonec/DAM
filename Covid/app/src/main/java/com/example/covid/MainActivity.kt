package com.example.covid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var txtUsuario: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtUsuario=findViewById(R.id.usuario)
        var txtPassword:EditText = findViewById(R.id.pass)
        Log.e("Prueba", txtUsuario.text.toString())
        print(txtPassword.text)
        login(txtUsuario.text.toString(), txtPassword.text.toString())

    }
    fun login(usuario:String, pass:String){

        var btn1: Button = findViewById(R.id.btnLogin)
        btn1.setOnClickListener {
            Log.e("User", usuario)
            Log.e("Password", pass)
            if (usuario.equals("admin") && pass.equals("123")) {
                var intent = Intent(this, Principal::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(
                    applicationContext,
                    "Error Credenciales Invalidas",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
//**********************************************************************************************
//si corrio el app, le agregue el onclicklistener a el boton pero no jala el nombre y contrasena
//**********************************************************************************************