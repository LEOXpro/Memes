package ru.lbas.memes

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({ var SecAct: Intent = Intent(getApplicationContext(), LoginActivity::class.java)
            startActivity(SecAct) }, 300)
    }
}