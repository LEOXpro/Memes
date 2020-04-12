package ru.lbas.memes

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import java.lang.Thread.sleep
import java.util.*
import kotlin.concurrent.thread

class SplashActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        sleep(300)
        setContentView(R.layout.activity_login)
    }


}