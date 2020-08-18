package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.model.data.DishesType
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.FIREBASE_TAG
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.MenuHeaderBinder
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.MenuItemBinder
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.CalendarClickVM
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.CartBadgeViewModel
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.MenusViewModel
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.factory.MenuVMFactory
import kotlinx.android.synthetic.main.fragment_menu_repas.*
import mva2.adapter.ItemSection
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class MenuRepasFragment: BaseFragment(), KodeinAware {

    override val kodein by kodein()
    private val menuVMFactory by instance<MenuVMFactory>()
    private val menusViewModel by lazy {
        ViewModelProvider(viewModelStore, menuVMFactory).get(MenusViewModel::class.java)
    }

    companion object {
        fun getInstance() = MenuRepasFragment()
    }

    override val title: String
        get() = "Menu"

    private lateinit var menuRecyclerView: RecyclerView
    private lateinit var menuAdapter: MultiViewAdapter

    private val startersSection = ItemSection<String>()
    private val mainCoursesSection = ItemSection<String>()
    private val dessertsSection = ItemSection<String>()
    private val startersList = ListSection<DishesType.Starters>()
    private val mainCoursesList = ListSection<DishesType.MainCourses>()
    private val dessertsList = ListSection<DishesType.Desserts>()

    private val cartBadgeViewModel: CartBadgeViewModel by activityViewModels()
    private val calendarClickVM by activityViewModels<CalendarClickVM>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_menu_repas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuRecyclerView = view.findViewById(R.id.menu_recycler_view)
        menuAdapter = MultiViewAdapter()
        menuAdapter.registerItemBinders(MenuHeaderBinder(), MenuItemBinder(requireContext()){ holder ->
            holder.menuCard.isChecked = !holder.menuCard.isChecked
            if(holder.menuCard.isChecked) {
                cartBadgeViewModel.onAddingMenuItem(holder.item)
                holder.menuCard.setCardBackgroundColor(resources.getColor(R.color.tameGreen, resources.newTheme()))
            }else {
                holder.menuCard.setCardBackgroundColor(Color.WHITE)
                cartBadgeViewModel.onDeleteMenuItem(holder.item)
            }
        })

        menuAdapter.addSection(startersSection)
        menuAdapter.addSection(startersList)
        menuAdapter.addSection(mainCoursesSection)
        menuAdapter.addSection(mainCoursesList)
        menuAdapter.addSection(dessertsSection)
        menuAdapter.addSection(dessertsList)

        menuRecyclerView.apply {
            this.adapter = menuAdapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
        placeholder.startShimmer()
        observingClick()
        fetchingDishes()
    }

    private fun observingClick() {
        calendarClickVM.didClickOnCalendar.observe(viewLifecycleOwner){
            Log.d(FIREBASE_TAG, "Looking click")
            placeholder.stopShimmer()
            placeholder.visibility = View.GONE
            onRefreshListsAndSection()
        }
    }

    private fun fetchingDishes(){
        menusViewModel.onRetrievedMenuData().observe(viewLifecycleOwner) {
            Log.d(FIREBASE_TAG, "Looking")
            when(it){
                is DishesType.Starters -> {
                    startersList.add(it)
                    menuAdapter.notifyDataSetChanged()
                    startersSection.setItem("Entrées")
                }
                is DishesType.MainCourses -> {
                    mainCoursesList.add(it)
                    menuAdapter.notifyDataSetChanged()
                    mainCoursesSection.setItem("Plats")
                }
                is DishesType.Desserts -> {
                    dessertsList.add(it)
                    menuAdapter.notifyDataSetChanged()
                    dessertsSection.setItem("Desserts")
                }
            }
        }
    }
    private fun onRefreshListsAndSection(){
        startersList.clear()
        startersSection.removeItem()

        mainCoursesList.clear()
        mainCoursesSection.removeItem()

        dessertsList.clear()
        dessertsSection.removeItem()
    }

    override fun onDestroy() {
        super.onDestroy()
        menuRecyclerView.apply {
            this.adapter = null
            this.layoutManager = null
        }
    }
}