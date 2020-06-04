package be.technifutur.devmob9.projet_cantinapp_android.view.ui

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.utils.addFragment
import be.technifutur.devmob9.projet_cantinapp_android.view.ui.auth.LoginFragment


class MainActivity: AppCompatActivity() {

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


    }

//    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
//        if (currentFocus != null) {
//            //Permet de cacher le clavier quand on clique autre part que sur quelque chose qui fait apparaître le clavier
//            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
//        }
//        return super.dispatchTouchEvent(ev)
//    }

    override fun onStart() {
        super.onStart()
        addFragment(MainMenuFragment(), R.id.fragment_container)
    }

}