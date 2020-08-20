package be.technifutur.devmob9.projet_cantinapp_android.view.ui.activities

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
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
import androidx.lifecycle.*
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.FragmentListener
import be.technifutur.devmob9.projet_cantinapp_android.model.data.MenuItemModel
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.POSITION_1_PAYMENTS
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.POSITION_2_ALLERGIES
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.POSITION_3_SETTINGS
import be.technifutur.devmob9.projet_cantinapp_android.utils.addFragment
import be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments.*
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.CartBadgeViewModel
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.MainFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.drawer_layout
import kotlinx.android.synthetic.main.fragment_main.*

class MainActivity: AppCompatActivity(), FragmentListener {

    companion object {
        fun getInstance() = MainActivity()
    }

//    private lateinit var toggle: ActionBarDrawerToggle
//    private lateinit var drawerLayout: DrawerLayout

    private lateinit var mainFragmentViewModel: MainFragmentViewModel
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val cartBadgeViewModel by lazy {
        ViewModelProviders.of(this).get(CartBadgeViewModel::class.java)
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)
        navigationView.setupWithNavController(navController)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawer_layout)
        setupActionBarWithNavController(navController, appBarConfiguration)
//        addFragment(MainFragment.getInstance(), R.id.nav_host_fragment)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
//        drawerLayout = findViewById(R.id.drawer_layout)

//        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
//        toggle.isDrawerIndicatorEnabled = true
//        toggle.syncState()
//        drawerLayout.addDrawerListener(toggle)
//
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setDisplayShowHomeEnabled(true)

//        setupBurgerMenuItems()

        mainFragmentViewModel = ViewModelProviders.of(this).get(MainFragmentViewModel::class.java)
        mainFragmentViewModel.title.observe(this, Observer {
            supportActionBar?.title = it
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

//    override fun onBackPressed() {
//        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
//            drawerLayout.closeDrawer(GravityCompat.START)
//        } else {
//            super.onBackPressed()
//        }
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        val menuItem = menu?.findItem(R.id.toolbar_cart)
        menuItem?.setActionView(R.layout.custom_action_item)

        val actionView = menuItem?.actionView
        val textCart: TextView? = actionView?.findViewById(R.id.cart_badge)

        textCart?.text = 0.toString()
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

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if(toggle.onOptionsItemSelected(item)){
//            return true
//        }
//        return super.onOptionsItemSelected(item)
//    }

    override fun onFragmentSelectedFromMenu(position: Int) {
        when(position) {
            0 ->  {
                replaceFragmentMenu(MenuRepasFragment.getInstance())
            }
            1 -> {
                replaceFragmentMenu(MenuSandwichFragment.getInstance())
            }
            2 -> {
                replaceFragmentMenu(MenuOthersFragment.getInstance())
            }
        }
    }

    override fun openDetailFragment() {
        replaceFragmentWithBackStack(DetailsFragment.getInstance())
    }

    override fun openDetailFragmentWithDetails(itemClicked: MenuItemModel) {
        Log.d("MenuData", "Clicked on card: $itemClicked")
        replaceFragmentWithBackStack(DetailsFragment.getInstance())
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

    private fun replaceFragmentMenu(fragment: Fragment, layout: Int = R.id.fragment_container_mainMenu) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack("menus_fragment")
            .replace(layout, fragment)
            .commit()
    }

    private fun replaceFragmentWithBackStack(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .add(R.id.nav_host_fragment, fragment)
            .commit()
    }

//    private fun setupBurgerMenuItems(){
//        navigationView.setNavigationItemSelectedListener {
//            when(it.itemId) {
//                R.id.activity_main_drawer_profile -> {
//                    drawerFragmentImpl(AccountFragment.getInstance())
//                }
//                R.id.activity_main_drawer_gdpr -> {
//                    drawerFragmentImpl(MenuGDPRFragment.getInstance())
//                }
//                R.id.activity_main_drawer_contact -> {
//                    drawerFragmentImpl(ContactFragment.getInstance())
//                }
//                R.id.activity_main_drawer_allergies -> {
//                    drawerFragmentImpl(AllergiesInfoFragment.getInstance())
//                }
//                R.id.activity_main_drawer_tos -> {
//                    drawerFragmentImpl(TermsOfServicesFragment.getInstance())
//                }
////                R.id.activity_main_drawer_tutorial -> {
////                    // Onboarding
////                }
//            }
//            true
//        }
//    }

//    private fun drawerFragmentImpl(fragment: Fragment){
//        supportFragmentManager.popBackStack()
//        replaceFragmentWithBackStack(fragment)
//        drawer_layout.closeDrawer(GravityCompat.START)
//    }

}