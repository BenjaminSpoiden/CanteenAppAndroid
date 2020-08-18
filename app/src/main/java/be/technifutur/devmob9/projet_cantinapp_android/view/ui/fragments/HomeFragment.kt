package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.CalendarModel
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.CalendarBinder
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.CalendarClickVM
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.HomeViewModel
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.factory.HomeViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter
import mva2.adapter.util.Mode
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import org.threeten.bp.LocalDate
import org.threeten.bp.format.TextStyle
import java.lang.StringBuilder
import java.util.*

class HomeFragment: BaseFragment(), KodeinAware {
    companion object {
        fun getInstance() =
            HomeFragment()
    }

    override val title: String
    get() = ""


    override val kodein by kodein()
    private val homeFactory: HomeViewModelFactory by instance()

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this, homeFactory).get(HomeViewModel::class.java)

    }
    private lateinit var calendarRecyclerView: RecyclerView
    private lateinit var multiViewAdapter: MultiViewAdapter
    private lateinit var listSection: ListSection<CalendarModel>

    private val calendarClickVM by activityViewModels<CalendarClickVM>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendarRecyclerView = view.findViewById(R.id.calendar_recyclerview)
        multiViewAdapter = MultiViewAdapter()
        listSection =  ListSection()


        multiViewAdapter.registerItemBinders(CalendarBinder {
            Toast.makeText(requireContext(), "${it.adapterPosition}", Toast.LENGTH_SHORT).show()
            dateBuilder(it.item.date)
            onRefreshClick(true)
            onGettingDataFromDate(it.item.date.toString())
            it.toggleItemSelection()
        })

        multiViewAdapter.setSelectionMode(Mode.SINGLE)
        multiViewAdapter.addSection(listSection)

        calendarRecyclerView.apply {
            this.adapter = multiViewAdapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        observeData()
    }

    private fun observeData() {
        shimmerFrameLayout.startShimmer()
        homeViewModel.getCalendarDaysData().observe(viewLifecycleOwner) {
            shimmerFrameLayout.stopShimmer()
            shimmerFrameLayout.visibility = View.GONE
            it.forEach { calendarModel ->
                listSection.add(calendarModel)
                multiViewAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun dateBuilder(date: LocalDate?){
        val stringBuilder = StringBuilder()
        stringBuilder.append(date?.dayOfWeek?.getDisplayName(TextStyle.FULL, Locale.FRENCH))
        stringBuilder.append(" ")
        stringBuilder.append(date?.dayOfMonth)
        stringBuilder.append(" ")
        stringBuilder.append(date?.month?.getDisplayName(TextStyle.FULL_STANDALONE, Locale.FRENCH))
        dayOfWeek.text = stringBuilder
    }

    private fun onGettingDataFromDate(date: String){
        homeViewModel.onRetrievedMenuFromDate(date)
    }


    private fun onRefreshClick(didClick: Boolean) = calendarClickVM.didClick(didClick)

    override fun onDestroy() {
        calendarRecyclerView.apply {
            this.adapter = null
            this.layoutManager = null
        }
        super.onDestroy()
    }
}
