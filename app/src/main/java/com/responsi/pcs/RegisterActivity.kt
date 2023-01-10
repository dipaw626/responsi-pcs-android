package com.responsi.pcs

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.responsi.pcs.utils.SessionManager

class RegisterActivity : AppCompatActivity() {

    private lateinit var backRegist: Button
    private lateinit var btnRegist: Button

    companion object{
        lateinit var sessionManager: SessionManager
        private lateinit var context: Context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        backRegist = findViewById(R.id.backRegist)
        btnRegist = findViewById(R.id.btnregist)

        backRegist.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        btnRegist.setOnClickListener{
            saveDataRegist()
        }

    }

    private fun saveDataRegist() {
        val editEmail = findViewById<EditText>(R.id.etemailRegist)
        val editPass = findViewById<EditText>(R.id.etpassRegist)
        val email = editEmail.text.toString().trim()
        val pass = editPass.text.toString().trim()

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail.error = "Masukkan Email dengan Benar"
        }
        if(email.isEmpty()) {
            editPass.error = "email masih kosong"
        }
        if(pass.isEmpty()) {
            editPass.error = "Password masih kosong"
        }

        sessionManager = SessionManager(this)
        sessionManager.saveString("email", email)
        sessionManager.saveString("pass", pass)

        Toast.makeText(this, "Akun berhasil dibuat!", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, LoginActivity::class.java))
    }

}