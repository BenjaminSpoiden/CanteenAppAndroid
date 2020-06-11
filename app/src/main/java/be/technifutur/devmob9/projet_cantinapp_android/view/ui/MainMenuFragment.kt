package be.technifutur.devmob9.projet_cantinapp_android.view.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.databinding.FragmentMainMenuBinding
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.CalendarListener
import be.technifutur.devmob9.projet_cantinapp_android.model.CalendarModel
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.FirebaseSource
import be.technifutur.devmob9.projet_cantinapp_android.utils.CalendarDayManager
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.CalendarItemAdapter
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.MainMenuViewModel
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.factory.MainMenuViewModelFactory
import com.google.firebase.firestore.*
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import kotlinx.android.synthetic.main.fragment_main_menu.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class MainMenuFragment: Fragment(), KodeinAware, CalendarListener {

    override val kodein by kodein()
    private val mainMenuFactory: MainMenuViewModelFactory by instance<MainMenuViewModelFactory>()

    private lateinit var mainMenuViewModel: MainMenuViewModel
    private lateinit var calendarRecyclerView: RecyclerView
    private val itemAdapter = ItemAdapter<CalendarItemAdapter>()
    private val fastAdapter = FastAdapter.with(itemAdapter)
    private val manager = CalendarDayManager.getInstance()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentMainMenuBinding>(inflater, R.layout.fragment_main_menu, container, false)
        mainMenuViewModel = ViewModelProvider(this, mainMenuFactory).get(MainMenuViewModel::class.java)
        binding.mainMenuViewModel = mainMenuViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendarRecyclerView = view.findViewById(R.id.calendar_recyclerview)
        calendarRecyclerView.apply {
            this.adapter = fastAdapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        bottomBar.onItemSelected = {
            //...
        }
        manager.calendarListener = this
    }

    override fun onCalendarReceived(calendarModel: CalendarModel) {
        Log.d(FirebaseSource.TAG, "calendar model: $calendarModel")
        itemAdapter.add(CalendarItemAdapter(calendarModel))
        fastAdapter.notifyAdapterDataSetChanged()
    }
}
