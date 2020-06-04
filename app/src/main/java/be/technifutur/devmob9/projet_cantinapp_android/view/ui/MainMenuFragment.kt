package be.technifutur.devmob9.projet_cantinapp_android.view.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.databinding.FragmentMainMenuBinding
import be.technifutur.devmob9.projet_cantinapp_android.model.CalendarModel
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.FirebaseSource
import be.technifutur.devmob9.projet_cantinapp_android.utils.CalendarDayManager
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.CalendarAdapter
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.AuthViewModel
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.MainMenuViewModel
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.factory.AuthViewModelFactory
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.factory.MainMenuViewModelFactory
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_main_menu.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import java.util.*


class MainMenuFragment: Fragment(), KodeinAware {

    override val kodein by kodein()
    private val mainMenuFactory: MainMenuViewModelFactory by instance<MainMenuViewModelFactory>()
    private lateinit var mainMenuViewModel: MainMenuViewModel

    private lateinit var calendarRecyclerView: RecyclerView
    private lateinit var calendarAdapter: CalendarAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentMainMenuBinding>(inflater, R.layout.fragment_main_menu, container, false)
        mainMenuViewModel = ViewModelProvider(this, mainMenuFactory).get(MainMenuViewModel::class.java)
        binding.mainMenuViewModel = mainMenuViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomBar.onItemSelected = {
            //...
        }
    }
    override fun onStart() {
        super.onStart()
        view?.let {
            calendarRecyclerView = it.findViewById(R.id.calendar_recyclerview)
        }
//        calendarAdapter = CalendarAdapter()
        calendarRecyclerView.adapter = calendarAdapter
        calendarAdapter.notifyDataSetChanged()
        calendarRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
}