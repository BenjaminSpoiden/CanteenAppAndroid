package be.technifutur.devmob9.projet_cantinapp_android.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import be.technifutur.devmob9.projet_cantinapp_android.R
import com.etebarian.meowbottomnavigation.MeowBottomNavigation

class MainMenuFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_menu, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textTest: TextView = view.findViewById(R.id.test)
        val bottomNavigation = view.findViewById<MeowBottomNavigation>(R.id.bottom_navigation)
        bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.ic_repas_menu))
        bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.ic_sandwich_menu))
        bottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.ic_croissant_menu))
        bottomNavigation.add(MeowBottomNavigation.Model(4, R.drawable.ic_more_menu))
        textTest.text = "Menu 1"
    }
}