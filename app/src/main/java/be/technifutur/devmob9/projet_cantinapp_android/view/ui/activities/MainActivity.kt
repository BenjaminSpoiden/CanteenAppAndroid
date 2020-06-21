package be.technifutur.devmob9.projet_cantinapp_android.view.ui.activities

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
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

    lateinit var toggle: ActionBarDrawerToggle

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toggle = ActionBarDrawerToggle(this, root_layout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        root_layout.addDrawerListener(toggle)
        toggle.syncState()
        addFragment(MainFragment.getInstance(), R.id.fragment_container)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.activity_main_drawer_profile -> Toast.makeText(applicationContext, "Clicked", Toast.LENGTH_SHORT).show()
                R.id.activity_main_drawer_settings -> Toast.makeText(applicationContext, "Clicked", Toast.LENGTH_SHORT).show()
            }
            true
        }
        setNavigationIcon(R.drawable.ic_account_circle_black_24dp)
    }

    override fun onBackPressed() {
        if(root_layout.isDrawerOpen(GravityCompat.START)){
            root_layout.closeDrawer(GravityCompat.START)
        }else {
            super.onBackPressed()
            supportFragmentManager.popBackStack()
            addFragment(MainFragment.getInstance(), R.id.fragment_container)
        }

//        replaceFragmentMenu(MenuRepasFragment.getInstance())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return when(item.itemId){
            R.id.toolbar_back -> {
                onBackPressed()
                return true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onFragmentSelectedFromMenu(position: Int) {
        when(position) {
            0 ->  {
                supportFragmentManager.popBackStack()
                replaceFragmentMenu(MenuRepasFragment.getInstance())
            }
            1 -> {
                supportFragmentManager.popBackStack()
                replaceFragmentMenu(MenuSandwichFragment.getInstance())
            }
            2 -> {
                supportFragmentManager.popBackStack()
                replaceFragmentMenu(MenuCroissantFragment.getInstance())
            }
            3 -> {
                supportFragmentManager.popBackStack()
                replaceWithMoreMenuFragment(MenuMoreFragment.getInstance())
            }
        }
    }

    private fun replaceFragmentMenu(fragment: Fragment, layout: Int = R.id.fragment_container_mainMenu) {
        supportFragmentManager
            .beginTransaction()
            .add(layout, fragment)
            .commit()
    }

    private fun replaceWithMoreMenuFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .add(R.id.fragment_container_main, fragment)
            .commit()
    }

    fun setToolBarTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun setNavigationIcon(icon: Int) {
        toolbar.setNavigationIcon(icon)
    }
}