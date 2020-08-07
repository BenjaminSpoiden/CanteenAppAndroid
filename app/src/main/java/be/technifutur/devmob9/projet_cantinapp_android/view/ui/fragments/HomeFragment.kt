package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.databinding.FragmentHomeBinding
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.CalendarListener
import be.technifutur.devmob9.projet_cantinapp_android.model.data.CalendarModel
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.CalendarDayManager
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.CalendarBinder
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.HomeViewModel
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.factory.HomeViewModelFactory
import mva2.adapter.HeaderSection
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter
import mva2.adapter.util.Mode
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class HomeFragment: BaseFragment(), KodeinAware, CalendarListener {
    companion object {
        fun getInstance() =
            HomeFragment()
    }

    override val title: String
    get() = ""

    private val manager = CalendarDayManager.INSTANCE
    override val kodein by kodein()
    private val homeFactory: HomeViewModelFactory by instance()

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var calendarRecyclerView: RecyclerView

    private lateinit var multiViewAdapter: MultiViewAdapter
    private lateinit var listSection: ListSection<CalendarModel>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home, container, false)
        homeViewModel = ViewModelProvider(this, homeFactory).get(HomeViewModel::class.java)
        binding.homeViewModel = homeViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendarRecyclerView = view.findViewById(R.id.calendar_recyclerview)
    }

    override fun onStart() {
        super.onStart()
        manager.calendarListener = this

        multiViewAdapter = MultiViewAdapter()
        multiViewAdapter.registerItemBinders(CalendarBinder())
        multiViewAdapter.setSelectionMode(Mode.SINGLE)

        listSection =  ListSection()
        multiViewAdapter.addSection(listSection)
        multiViewAdapter.addSection(HeaderSection<CalendarModel>())

        calendarRecyclerView.apply {
            this.adapter = multiViewAdapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    override fun onCalendarReceived(calendarModel: CalendarModel) {
        listSection.add(calendarModel)
        multiViewAdapter.notifyDataSetChanged()
    }

    override fun onDetach() {
        super.onDetach()
        manager.calendarListener = null
    }

    override fun onDestroy() {
        calendarRecyclerView.apply {
            this.adapter = null
            this.layoutManager = null
        }
        super.onDestroy()
    }
}
