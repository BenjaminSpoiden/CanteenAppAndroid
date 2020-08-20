package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.graphics.Color
import android.os.Bundle
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
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.MenuHeaderBinder
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.MenuItemBinder
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.SharedDateViewModel
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.CartBadgeViewModel
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.DishesViewModel
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.factory.DishesViewModelFactory
import kotlinx.android.synthetic.main.fragment_menu_repas.*
import mva2.adapter.ItemSection
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class MenuRepasFragment: BaseFragment(), KodeinAware {

    override val kodein by kodein()
    private val dishesViewModelFactory by instance<DishesViewModelFactory>()
    private val dishesViewModel: DishesViewModel by lazy {
        ViewModelProvider(viewModelStore, dishesViewModelFactory).get(DishesViewModel::class.java)
    }

    companion object {
        fun getInstance() = MenuRepasFragment()
    }

    override val title: String
        get() = "Menu"

    private var menuRecyclerView: RecyclerView? = null
    private lateinit var menuAdapter: MultiViewAdapter

    private val startersSection = ItemSection<String>()
    private val mainCoursesSection = ItemSection<String>()
    private val dessertsSection = ItemSection<String>()
    private val startersList = ListSection<DishesType.Starters>()
    private val mainCoursesList = ListSection<DishesType.MainCourses>()
    private val dessertsList = ListSection<DishesType.Desserts>()

    private val cartBadgeViewModel  by activityViewModels<CartBadgeViewModel>()
    private val sharedDateViewModel by activityViewModels<SharedDateViewModel>()

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

        menuRecyclerView?.apply {
            this.adapter = menuAdapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
        placeholder.startShimmer()
        observingClick()
        fetchingDishes()
    }
    private fun observingClick() {
        sharedDateViewModel.sharedDate.observe(viewLifecycleOwner) {
            placeholder.stopShimmer()
            placeholder.visibility = View.GONE
            onRefreshListsAndSection()
            dishesViewModel.fetchingDishes(it)
            sharedDateViewModel.sharedDate.removeObservers(this)
        }
    }

    private fun fetchingDishes() {
        dishesViewModel.fetchedDishes.observe(viewLifecycleOwner) { dishesList ->
            dishesList.forEach {
                when(it) {
                    is DishesType.Starters -> {
                        startersSection.setItem("Entrées")
                        startersList.add(it)
                        menuAdapter.notifyDataSetChanged()
                    }
                    is DishesType.MainCourses -> {
                        mainCoursesSection.setItem("Plats")
                        mainCoursesList.add(it)
                        menuAdapter.notifyDataSetChanged()
                    }
                    is DishesType.Desserts -> {
                        dessertsSection.setItem("Desserts")
                        dessertsList.add(it)
                        menuAdapter.notifyDataSetChanged()
                    }
                }
            }
            dishesViewModel.fetchedDishes.removeObservers(this)
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

    override fun onDestroyView() {
        menuRecyclerView?.apply {
            this.adapter = null
            this.layoutManager = null
        }
        menuRecyclerView = null
        super.onDestroyView()
    }

}