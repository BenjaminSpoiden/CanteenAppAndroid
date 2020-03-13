package be.technifutur.devmob9.projet_cantinapp_android.view.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.utils.addFragment


class MainActivity: AppCompatActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

//        if (savedInstanceState == null) {
//            setFragment(R.id.fragment_container, LoginFragment())
//        }
        hideKeyboard(this)
        addFragment(LoginFragment(), R.id.fragment_container)
    }

    fun hideKeyboard(activity: Activity){
        val inputMethodManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity.currentFocus?.windowToken, 0)
    }

    fun setFragment(layout: Int, fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .add(layout, fragment)
            .commit()
    }
}