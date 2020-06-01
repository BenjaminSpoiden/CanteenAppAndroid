package be.technifutur.devmob9.projet_cantinapp_android.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.databinding.FragmentMainMenuBinding
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.AuthViewModel
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.AuthViewModelFactory
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class MainMenuFragment: Fragment(), KodeinAware {

    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance<AuthViewModelFactory>()
    private lateinit var viewModel: AuthViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentMainMenuBinding>(inflater, R.layout.fragment_main_menu, container, false)
        viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomNavigation = view.findViewById<MeowBottomNavigation>(R.id.bottom_navigation)
        bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.ic_repas_menu))
        bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.ic_sandwich_menu))
        bottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.ic_croissant_menu))
        bottomNavigation.add(MeowBottomNavigation.Model(4, R.drawable.ic_more_menu))
    }
}