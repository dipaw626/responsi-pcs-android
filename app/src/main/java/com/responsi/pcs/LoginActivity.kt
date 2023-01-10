package com.responsi.pcs

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.responsi.pcs.utils.SessionManager

class LoginActivity : AppCompatActivity() {

    private lateinit var btnLogin: Button
    private lateinit var forgotpass: TextView
    private lateinit var register: TextView

    companion object{
        lateinit var sessionManager: SessionManager
        private lateinit var context: Context
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin = findViewById(R.id.btnlogin)
        forgotpass = findViewById(R.id.tvrecoverpass)
        register = findViewById(R.id.tvregisteracc)

        sessionManager = SessionManager(this)
        val loginStatus = sessionManager.getBoolean("LOGIN_STATUS")
        if(loginStatus){
            val moveIntent = Intent(this@LoginActivity,HomeActivity::class.java)
            startActivity(moveIntent)
            finish()
        }

        btnLogin.setOnClickListener{
          checkData()
        }
        forgotpass.setOnClickListener{
            val intent = Intent(this, RecoveryActivity::class.java)
            startActivity(intent)
        }
        register.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }

    private fun checkData() {
        val editEmail = findViewById<EditText>(R.id.etemailLogin)
        val editPass = findViewById<EditText>(R.id.etpassLogin)

        val email = editEmail?.text.toString().trim()
        val pass = editPass?.text.toString().trim()

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail.error = "Masukkan Email dengan Benar"
        }
        if(email.isEmpty() && pass.isEmpty()) {
            Toast.makeText(this, "email dan password masih kosong!", Toast.LENGTH_SHORT).show()
        }
        if(email.isEmpty()) {
            editEmail.error = "Email masih kosong"
        }
        if(pass.isEmpty()) {
            editPass.error = "Password masih kosong"
        }
        else {
            sessionManager = SessionManager(this)
            val getEmail = sessionManager.getString("email")
            val getPass = sessionManager.getString("pass")

            if (email == getEmail && pass == getPass) {
                Toast.makeText(this, "Selamat Datang $getEmail", Toast.LENGTH_SHORT).show()
                sessionManager.saveBoolean("LOGIN_STATUS", true)
                startActivity(Intent(this, SplashScreen::class.java))
            }
            else {
                if (email != getEmail && pass != getPass) {
                    Toast.makeText(this, "Email dan Password tidak terdaftar", Toast.LENGTH_SHORT).show()
                }
                else if (email != getEmail) {
                    Toast.makeText(this, "Email tidak sesuai", Toast.LENGTH_SHORT).show()
                }
                else if (pass != getPass) {
                    Toast.makeText(this, "Password tidak sesuai", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

}


//        title ="Menu Login"
//        val email = findViewById<EditText>(R.id.etemail)
//        val pass = findViewById<EditText>(R.id.etpass)
//        val btnlogin = findViewById<Button>(R.id.btnlogin)
//
//        btnlogin.setOnClickListener{
//            val bundle = Bundle()
//            bundle.putString("email" , email.text.toString())
//
//            if(!Patterns.EMAIL_ADDRESS.matcher(email.text,toString()).matches()) {
//                email.error = "Masukkan Email dengan Benar"
//            }
//            else if (pass.length() == 0){
//                pass.error = "Password tidak boleh kosong"
//            }
//            else {
//                val alertDialogBuilder = AlertDialog.Builder(this)
//                alertDialogBuilder.setTitle("Peringatan..!!")
//                alertDialogBuilder.setMessage("Apakah Data Anda Sudah Benar..?")
//                    .setCancelable(false)
//                    .setPositiveButton("Yes") {
//                            dialogInterface, i ->
//                        val intent = Intent(this, HomeActivity::class.java)
//                        intent.putExtras(bundle)
//                        startActivity(intent)
//                        Toast.makeText(this, "Anda Berhasil Login", Toast.LENGTH_SHORT).show()
//                    }
//                    .setNegativeButton("No") {
//                            dialogInterface, i -> dialogInterface.cancel()
//                    }
//                val alertDialog = alertDialogBuilder.create()
//                alertDialog.show()
//            }
//        }
