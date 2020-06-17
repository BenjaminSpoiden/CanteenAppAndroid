package be.technifutur.devmob9.projet_cantinapp_android.view.ui.activities

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.FragmentListener
import be.technifutur.devmob9.projet_cantinapp_android.utils.addFragment
import be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments.*


class MainActivity: AppCompatActivity(), FragmentListener {

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onStart() {
        super.onStart()
        addFragment(HomeFragment.getInstance(), R.id.fragment_container)
    }

    override fun onFragmentSelectedFromMenu(position: Int) {
        when(position) {
            0 -> replaceFragmentMenu(MenuRepasFragment.getInstance())
            1 -> replaceFragmentMenu(MenuSandwichFragment.getInstance())
            2 -> replaceFragmentMenu(MenuCroissantFragment.getInstance())
            3 -> replaceFragmentMenu(MenuMoreFragment.getInstance())
        }
    }

    private fun replaceFragmentMenu(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_mainMenu, fragment)
            .commit()
    }
}