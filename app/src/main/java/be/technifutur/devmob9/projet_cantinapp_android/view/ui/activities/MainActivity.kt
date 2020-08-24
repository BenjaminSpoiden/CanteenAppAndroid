package be.technifutur.devmob9.projet_cantinapp_android.view.ui.activities

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
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
import be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments.*
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.CartBadgeViewModel
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.MainFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.drawer_layout
import kotlinx.android.synthetic.main.fragment_home.*

class MainActivity: AppCompatActivity(), FragmentListener {

    companion object {
        fun getInstance() = MainActivity()
    }

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
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        mainFragmentViewModel = ViewModelProviders.of(this).get(MainFragmentViewModel::class.java)
        mainFragmentViewModel.title.observe(this, Observer {
            supportActionBar?.title = it
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
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

    override fun openDetailFragment() {
        findNavController(R.id.nav_host_fragment).navigate(R.id.NavigationToDetail)
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

    private fun replaceFragmentWithBackStack(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .add(R.id.nav_host_fragment, fragment)
            .commit()
    }
}