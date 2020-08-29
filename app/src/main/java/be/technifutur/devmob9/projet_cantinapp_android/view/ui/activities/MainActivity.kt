package be.technifutur.devmob9.projet_cantinapp_android.view.ui.activities

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
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
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.FragmentListener
import be.technifutur.devmob9.projet_cantinapp_android.model.data.Food
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.POSITION_1_PAYMENTS
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.POSITION_2_ALLERGIES
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.POSITION_3_SETTINGS
import be.technifutur.devmob9.projet_cantinapp_android.utils.addFragment
import be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments.*
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.CartBadgeViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.drawer_layout
import kotlinx.android.synthetic.main.fragment_home.*

class MainActivity: AppCompatActivity(), FragmentListener, BaseFragment.OnSettingFragmentTitle {

    companion object {
        fun getInstance() = MainActivity()
    }

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout


    private val cartBadgeViewModel by lazy {
        ViewModelProviders.of(this).get(CartBadgeViewModel::class.java)
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(HomeFragment.getInstance(), R.id.main_fragment_container)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        drawerLayout = findViewById(R.id.drawer_layout)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        toggle.isDrawerIndicatorEnabled = true
        toggle.syncState()
        drawerLayout.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        setupBurgerMenuItems()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        val menuItem = menu?.findItem(R.id.toolbar_cart)
        menuItem?.setActionView(R.layout.custom_action_item)

        val actionView = menuItem?.actionView
        val textCart: TextView? = actionView?.findViewById(R.id.cart_badge)

        textCart?.text = 0.toString()
        actionView?.let { _ ->
            onActionViewClick(actionView, 0)
        }
        cartBadgeViewModel.foodItems.observe(this) {
            textCart?.text = it.size.toString()

            /**
             * Can only Access [OrdersFragment] if there's an item in the cart
             */

            actionView?.let { _ ->
                onActionViewClick(actionView, it.size)
            }
        }
        return super.onCreateOptionsMenu(menu)
    }

    private fun onActionViewClick(view: View, numberOfItems: Int) {
        view.setOnClickListener {
            if(numberOfItems > 0) {
                replaceFragmentWithBackStack(OrdersFragment.getInstance())
            }else {
                Snackbar
                    .make(this.drawer_layout, "You don't have anything in your cart", Snackbar.LENGTH_SHORT)
                    .setAnchorView(bottomBar)
                    .setAction("CLOSE") {

                    }
                    .show()
            }
        }
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        if(fragment is BaseFragment) fragment.setTitleListener(this)
    }

    override fun fragmentTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun openMenuDetail(foodItem: Food) {
        replaceFragmentWithBackStack(DetailsFragment.getInstance(foodItem))
    }


    override fun openCheckoutFragment() {
        replaceFragmentWithBackStack(CheckoutFragment.getInstance())
    }

    override fun onProfileMenuSelection(selectedRowPosition: Int) {
        when(selectedRowPosition){
            POSITION_1_PAYMENTS -> replaceFragmentWithBackStack(PaymentHistory.getInstance())
            POSITION_2_ALLERGIES -> replaceFragmentWithBackStack(AllergiesAccountFragment.getInstance())
            POSITION_3_SETTINGS -> Toast.makeText(this, "Settings Fragment", Toast.LENGTH_SHORT).show()
        }
    }

    private fun replaceFragmentWithBackStack(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .add(R.id.main_fragment_container, fragment)
            .commit()
    }

    private fun setupBurgerMenuItems(){
        navigationView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.activity_main_drawer_profile -> {
                    drawerFragmentImpl(AccountFragment.getInstance())
                }
                R.id.activity_main_drawer_gdpr -> {
                    drawerFragmentImpl(MenuGDPRFragment.getInstance())
                }
                R.id.activity_main_drawer_contact -> {
                    drawerFragmentImpl(ContactFragment.getInstance())
                }
                R.id.activity_main_drawer_allergies -> {
                    drawerFragmentImpl(AllergiesInfoFragment.getInstance())
                }
                R.id.activity_main_drawer_tos -> {
                    drawerFragmentImpl(TermsOfServicesFragment.getInstance())
                }
            }
            true
        }
    }

    private fun drawerFragmentImpl(fragment: Fragment){
//        supportFragmentManager.popBackStack()
        replaceFragmentWithBackStack(fragment)
        drawer_layout.closeDrawer(GravityCompat.START)
    }
}