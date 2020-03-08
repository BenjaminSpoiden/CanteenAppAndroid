package be.technifutur.devmob9.projet_cantinapp_android.view.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import be.technifutur.devmob9.projet_cantinapp_android.R

class SplashActivity: AppCompatActivity() {
    companion object{
        const val SPLASH_SCREEN_DURATION: Int = 3000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var isFirstTime: Boolean
        //Remove the title bar of this activity
        window.requestFeature(Window.FEATURE_NO_TITLE)

        //Set window in fullsreen
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)

        val sharedPreferences: SharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        isFirstTime = sharedPreferences.getBoolean("FirstTime", true) //Set isFirstTime to true

        if(isFirstTime){
            //3 seconds splashScreen
            Handler().postDelayed({
                isFirstTime = false //The app is now open, its not the first time anymore
                editor.putBoolean("FirstTime", isFirstTime)
                editor.apply()

                startActivity(Intent(this@SplashActivity, OnboardingActivity::class.java))
                finish()
            }, SPLASH_SCREEN_DURATION.toLong())
        }else{
            Handler().postDelayed({
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }, SPLASH_SCREEN_DURATION.toLong())
        }
    }
}

