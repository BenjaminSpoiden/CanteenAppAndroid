package be.technifutur.devmob9.projet_cantinapp_android.view.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.utils.ActivityUtils
import com.etebarian.meowbottomnavigation.MeowBottomNavigation


class MainActivity: AppCompatActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        changeUI(findViewById(R.id.root_layout))
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


//        val textTest: TextView = findViewById(R.id.test)
//        val bottomNavigation = findViewById<MeowBottomNavigation>(R.id.bottom_navigation)
//        bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.ic_repas_menu))
//        bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.ic_sandwich_menu))
//        bottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.ic_croissant_menu))
//        bottomNavigation.add(MeowBottomNavigation.Model(4, R.drawable.ic_more_menu))
//        textTest.text = "Menu 1"
//
        if (savedInstanceState == null) {
            setFragment(R.id.fragment_container, LoginFragment())
        }
        hideKeyboard(this)
    }

    fun hideKeyboard(activity: Activity){
        val inputMethodManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity.currentFocus?.windowToken, 0)
    }


    fun changeUI(view: View){
        if(view !is EditText){
            view.setOnTouchListener { v, event ->
                hideKeyboard(this)
                false
            }
        }
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                changeUI(innerView)
            }
        }
    }

    fun setFragment(layout: Int, fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .add(layout, fragment)
            .commit()
    }
}