package be.technifutur.devmob9.projet_cantinapp_android.view.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import be.technifutur.devmob9.projet_cantinapp_android.R
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.luseen.spacenavigation.SpaceItem
import com.luseen.spacenavigation.SpaceNavigationView
import com.luseen.spacenavigation.SpaceOnClickListener
import java.util.*

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textTest: TextView = findViewById(R.id.test)

        val spaceNavigationView: SpaceNavigationView = findViewById(R.id.bottom_navigation)
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState)
        spaceNavigationView.showIconOnly()
        spaceNavigationView.addSpaceItem(SpaceItem("REPAS", R.drawable.ic_repas_menu))
        spaceNavigationView.addSpaceItem(SpaceItem("SANDWICH", R.drawable.ic_sandwich_menu))
        spaceNavigationView.addSpaceItem(SpaceItem("CROISSANT", R.drawable.ic_croissant_menu))
        spaceNavigationView.addSpaceItem(SpaceItem("MORE", R.drawable.ic_more_menu))
        spaceNavigationView.changeCenterButtonIcon(R.drawable.ic_buy)
        spaceNavigationView.setActiveSpaceItemColor(resources.getColor(R.color.darkBlue, theme))
        spaceNavigationView.setInActiveSpaceItemColor(resources.getColor(R.color.defaultIcon, theme))
        spaceNavigationView.setSpaceItemIconSizeInOnlyIconMode(resources.getDimension(R.dimen.icon_size).toInt())
        textTest.text = "Menu 1"
        val spaceClickListener = object: SpaceOnClickListener{
            override fun onCentreButtonClick() {
                textTest.text = "BUY"
            }
            override fun onItemReselected(itemIndex: Int, itemName: String?) {}
            override fun onItemClick(itemIndex: Int, itemName: String?) {
                when(itemIndex){
                    0 -> {
                    textTest.text = "Menu 1"
                    }

                    1 -> {
                    textTest.text = "Menu 2"
                    }

                    2 -> {
                    textTest.text = "Menu 3"
                    }

                    3 -> {
                    textTest.text = "Menu 4"
                    }
                }
            }
        }
        spaceNavigationView.setSpaceOnClickListener(spaceClickListener)
    }
}