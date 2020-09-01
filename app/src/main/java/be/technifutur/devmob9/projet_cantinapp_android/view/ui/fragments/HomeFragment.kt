package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.utils.BottomNavigationScreens
import be.technifutur.devmob9.projet_cantinapp_android.utils.getMainScreen
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.CalendarAdapter
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.MainPagerAdapter
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.SharedDateViewModel
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.HomeViewModel
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.factory.CalendarViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_home.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import org.threeten.bp.LocalDate
import org.threeten.bp.format.TextStyle
import java.lang.StringBuilder
import java.util.*

class HomeFragment: BaseFragment(), KodeinAware, BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var viewPager2: ViewPager2
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var mainPagerAdapter: MainPagerAdapter

    companion object {
        fun getInstance() =
            HomeFragment()
    }


    override val kodein by kodein()
    private val calendarFactory: CalendarViewModelFactory by instance()
    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this, calendarFactory).get(HomeViewModel::class.java)

    }
    private lateinit var calendarRecyclerView: RecyclerView
//    private lateinit var multiViewAdapter: MultiViewAdapter
//    private lateinit var listSection: ListSection<CalendarModel>

    private lateinit var calendarAdapter: CalendarAdapter


    private val sharedDateViewModel by activityViewModels<SharedDateViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager2 = view.findViewById(R.id.fragment_bottom_pager)
        bottomNavigationView = view.findViewById(R.id.bottomBar)
        mainPagerAdapter = MainPagerAdapter(requireActivity())

        mainPagerAdapter.setItems(listOf(
            BottomNavigationScreens.MENU,
            BottomNavigationScreens.SANDWICH,
            BottomNavigationScreens.OTHERS
        ))

        val defaultScreen = BottomNavigationScreens.MENU
        onSelectBottomNavMenu(defaultScreen.menuItemID)
        requireActivity().actionBar?.setTitle(defaultScreen.menuTitle)

        bottomNavigationView.setOnNavigationItemSelectedListener(this)

        viewPager2.adapter = mainPagerAdapter
        viewPager2.isUserInputEnabled = true
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val selectedScreen = mainPagerAdapter.getItems()[position]
                onSelectBottomNavMenu(selectedScreen.menuItemID)
            }
        })

        calendarRecyclerView = view.findViewById(R.id.calendar_recyclerview)
        calendarAdapter = CalendarAdapter()
//        multiViewAdapter = MultiViewAdapter()
//        listSection =  ListSection()
//        multiViewAdapter.registerItemBinders(CalendarBinder())
        observeData()

        textShimmer.startShimmer()

//        multiViewAdapter.setSelectionMode(Mode.SINGLE)
//        multiViewAdapter.addSection(listSection)


        calendarRecyclerView.apply {
            this.adapter = calendarAdapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun observeData() {
        shimmerFrameLayout.startShimmer()
        homeViewModel.getCalendarDaysData()
        homeViewModel.fetchedDates.observe(viewLifecycleOwner) { calendarModelList ->
            shimmerFrameLayout.stopShimmer()
            shimmerFrameLayout.visibility = View.GONE
            calendarAdapter.addAll(calendarModelList)

            dateBuilder(calendarAdapter.get(0).date)
            onFetchingDishesFromDateClick(calendarAdapter.get(0).date.toString())

            CalendarAdapter.onDateListener = { date ->
                dateBuilder(date)
                onFetchingDishesFromDateClick(date.toString())
            }

            homeViewModel.fetchedDates.removeObservers(this)
        }
    }
    private fun dateBuilder(date: LocalDate?){
        textShimmer.stopShimmer()
        textShimmer.visibility = View.GONE
        val stringBuilder = StringBuilder()
        stringBuilder.append(date?.dayOfWeek?.getDisplayName(TextStyle.FULL, Locale.FRENCH))
        stringBuilder.append(" ")
        stringBuilder.append(date?.dayOfMonth)
        stringBuilder.append(" ")
        stringBuilder.append(date?.month?.getDisplayName(TextStyle.FULL_STANDALONE, Locale.FRENCH))
        dayOfWeek.text = stringBuilder
    }

    private fun onFetchingDishesFromDateClick(dateClick: String) = sharedDateViewModel.onFetchingDishesFromDateClick(dateClick)

    override fun onDestroyView() {
        calendarRecyclerView.apply {
            this.adapter = null
            this.layoutManager = null
        }
        super.onDestroyView()
    }

    private fun toScreen(bottomNavigationScreens: BottomNavigationScreens) {
        val bottomScreenPosition = mainPagerAdapter.getItems().indexOf(bottomNavigationScreens)
        if(bottomScreenPosition != viewPager2.currentItem) {
            viewPager2.currentItem = bottomScreenPosition
        }
    }

    private fun onSelectBottomNavMenu(@IdRes menuItemID: Int) {
        bottomNavigationView.setOnNavigationItemSelectedListener(null)
        bottomNavigationView.selectedItemId = menuItemID
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        getMainScreen(item.itemId)?.let {
            toScreen(it)
            return true
        }
        return false
    }
}
