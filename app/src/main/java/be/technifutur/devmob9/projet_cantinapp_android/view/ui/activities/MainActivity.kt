package be.technifutur.devmob9.projet_cantinapp_android.view.ui.activities

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.FragmentListener
import be.technifutur.devmob9.projet_cantinapp_android.utils.addFragment
import be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity: AppCompatActivity(), FragmentListener {

    companion object {
        fun getInstance() = MainActivity()
    }

    private lateinit var toggle: ActionBarDrawerToggle

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(MainFragment.getInstance(), R.id.fragment_container)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


        toggle = ActionBarDrawerToggle(this, root_layout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        toggle.isDrawerIndicatorEnabled = true
        toggle.syncState()
        root_layout.addDrawerListener(toggle)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        setupBurgerMenuItems()
    }

    override fun onBackPressed() {
        if(root_layout.isDrawerOpen(GravityCompat.START)){
            root_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
            supportFragmentManager.popBackStack()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val mCartItemCount = 1
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val menuItem = menu?.findItem(R.id.toolbar_cart)
        val actionView = menuItem?.actionView
        val textCart: TextView? = actionView?.findViewById(R.id.cart_badge)

        if (textCart != null) {
            if (mCartItemCount == 0) {
                if (textCart.visibility != View.GONE) {
                    textCart.visibility = View.GONE
                }
            } else {
                textCart.setText(mCartItemCount.coerceAtMost(99))
                if (textCart.visibility != View.VISIBLE) {
                    textCart.visibility = View.VISIBLE
                }
            }
        }

        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onFragmentSelectedFromMenu(position: Int) {
        when(position) {
            0 ->  {
                replaceFragmentMenu(MenuRepasFragment.getInstance())
            }
            1 -> {
                replaceFragmentMenu(MenuSandwichFragment.getInstance())
            }
            2 -> {
                replaceFragmentMenu(MenuCroissantFragment.getInstance())
            }
        }
    }

    override fun getFragmentTitle(title: String) {
        supportActionBar?.title = title
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
            .add(R.id.fragment_container, fragment)
            .commit()
    }

    private fun mockFragmentImpl(fragment: Fragment){
        Toast.makeText(applicationContext, "Clicked", Toast.LENGTH_SHORT).show()
        replaceWithMoreMenuFragment(fragment)
        root_layout.closeDrawer(GravityCompat.START)
    }

    private fun setupBurgerMenuItems(){
        navigationView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.activity_main_drawer_profile -> {
                    mockFragmentImpl(MenuMoreFragment.getInstance())
                }
                R.id.activity_main_drawer_payment -> {
                    mockFragmentImpl(PaymentHistory.getInstance())
                }
                R.id.activity_main_drawer_gdpr -> {
                    mockFragmentImpl(MenuMoreFragment.getInstance())
                }
                R.id.activity_main_drawer_contact -> {
                    mockFragmentImpl(MenuMoreFragment.getInstance())
                }
                R.id.activity_main_drawer_allergies -> {
                    mockFragmentImpl(MenuMoreFragment.getInstance())
                }
                R.id.activity_main_drawer_tos -> {
                    mockFragmentImpl(MenuMoreFragment.getInstance())
                }
                R.id.activity_main_drawer_tutorial -> {
                    mockFragmentImpl(MenuMoreFragment.getInstance())
                }
            }
            true
        }
    }
}