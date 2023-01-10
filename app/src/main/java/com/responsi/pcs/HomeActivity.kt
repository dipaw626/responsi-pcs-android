package com.responsi.pcs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var btnHome: Button
    private lateinit var btnPayment: Button
    private lateinit var btnHistory: Button
    private lateinit var btnSetting: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.nav_fragment)
        bottomNavigationView.setupWithNavController(navController)

//        sessionManager = SessionManager(this)
//        val loginStatus = sessionManager.getBoolean("LOGIN_STATUS")
//        if(loginStatus){
//            val moveIntent = Intent(this@HomeActivity,HomeActivity::class.java)
//            startActivity(moveIntent)
//            finish()
//        }

//        var prefEmail = intent.getStringExtra("prefEmail")
////        Log.d("pref", "$prefEmail")
//        val sharedPreferences: SharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
//        val getPref = sharedPreferences.getString("email", prefEmail)
//        Log.d("pref", "$getPref")
//
//        if (prefEmail == null) {
//            startActivity(Intent(this, LoginActivity::class.java))
//        }


    }

}