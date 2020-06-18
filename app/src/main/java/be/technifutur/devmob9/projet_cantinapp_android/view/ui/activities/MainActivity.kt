package be.technifutur.devmob9.projet_cantinapp_android.view.ui.activities

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuBuilder
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.FragmentListener
import be.technifutur.devmob9.projet_cantinapp_android.utils.addFragment
import be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity: AppCompatActivity(), FragmentListener{

    companion object {
        fun getInstance() = MainActivity()
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onStart() {
        super.onStart()
        addFragment(MainFragment.getInstance(), R.id.fragment_container)
    }

    override fun onFragmentSelectedFromMenu(position: Int) {
        when(position) {
            0 -> replaceFragmentMenu(MenuRepasFragment.getInstance())
            1 -> replaceFragmentMenu(MenuSandwichFragment.getInstance())
            2 -> replaceFragmentMenu(MenuCroissantFragment.getInstance())
            3 -> replaceWithMoreMenuFragment(MenuMoreFragment.getInstance())
        }
    }
    private fun replaceFragmentMenu(fragment: Fragment, layout: Int = R.id.fragment_container_mainMenu) {
        supportFragmentManager
            .beginTransaction()
            .replace(layout, fragment)
            .commit()
    }

    private fun replaceWithMoreMenuFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack("")
            .replace(R.id.fragment_container_main, fragment)
            .commit()
    }

    fun setToolBarTitle(title: String) {
        supportActionBar?.title = title
    }
    fun setNavigationIcon(icon: Int) {
        toolbar.setNavigationIcon(icon)

    }
}