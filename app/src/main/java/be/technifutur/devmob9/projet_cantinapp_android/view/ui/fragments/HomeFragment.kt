package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.databinding.FragmentHomeBinding
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.CalendarListener
import be.technifutur.devmob9.projet_cantinapp_android.model.data.CalendarModel
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.CalendarDayManager
import be.technifutur.devmob9.projet_cantinapp_android.utils.cardState
import be.technifutur.devmob9.projet_cantinapp_android.utils.colorSelection
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.CalendarItem
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.HomeViewModel
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.factory.HomeViewModelFactory
import com.google.android.material.card.MaterialCardView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.ISelectionListener
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.listeners.ClickEventHook
import com.mikepenz.fastadapter.select.SelectExtension
import com.mikepenz.fastadapter.select.getSelectExtension
import com.mikepenz.fastadapter.select.selectExtension
import kotlinx.android.synthetic.main.calendar_item.*
import kotlinx.android.synthetic.main.calendar_item.view.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import kotlin.properties.Delegates

class HomeFragment: BaseFragment(), KodeinAware, CalendarListener {
    companion object {
        fun getInstance() =
            HomeFragment()
    }

    override val title: String
    get() = ""

    private val manager = CalendarDayManager.getInstance()
    override val kodein by kodein()
    private val homeFactory: HomeViewModelFactory by instance()

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var calendarRecyclerView: RecyclerView
    private val itemAdapter = ItemAdapter<CalendarItem>()
    private val fastAdapter = FastAdapter.with(itemAdapter)
    private var selectedPosition: Int = -1
    private var oldSelectedPosition: Int = -1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home, container, false)
        homeViewModel = ViewModelProvider(this, homeFactory).get(HomeViewModel::class.java)
        binding.homeViewModel = homeViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        manager.calendarListener = this

        calendarRecyclerView = view.findViewById(R.id.calendar_recyclerview)
        calendarRecyclerView.apply {
            this.adapter = fastAdapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        fastAdapter.addEventHook(object : ClickEventHook<CalendarItem>() {
            override fun onBind(viewHolder: RecyclerView.ViewHolder): View? {
                return if(viewHolder is CalendarItem.CalendarViwHolder){
                    viewHolder.calendarCard
                }else {
                    null
                }
            }
            override fun onClick(v: View, position: Int, fastAdapter: FastAdapter<CalendarItem>, item: CalendarItem) {
                v.calendar_card_view.setCardBackgroundColor(colorSelection("#DBF9D8"))
                v.day_tv.setTextColor(colorSelection("#2F4858"))
                v.day_number_tv.setTextColor(colorSelection("#2F4858"))
            }
        })
    }

    override fun onCalendarReceived(calendarModel: CalendarModel) {
        itemAdapter.add(CalendarItem(calendarModel))
        fastAdapter.notifyAdapterDataSetChanged()
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
