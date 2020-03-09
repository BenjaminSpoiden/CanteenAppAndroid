package be.technifutur.devmob9.projet_cantinapp_android.view.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import be.technifutur.devmob9.projet_cantinapp_android.R
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import java.util.*

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textTest: TextView = findViewById(R.id.test)

        val bottomNavigation = findViewById<MeowBottomNavigation>(R.id.bottom_navigation)
        bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.ic_repas_menu))
        bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.ic_sandwich_menu))
        bottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.ic_croissant_menu))
        bottomNavigation.add(MeowBottomNavigation.Model(4, R.drawable.ic_more_menu))
        textTest.text = "Menu 1"

    }
}