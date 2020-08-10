package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.model.data.DishesType
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.Food
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.MenuHeaderBinder
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.MenuItemBinder
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
        ViewModelProvider(this, menuVMFactory).get(MenusViewModel::class.java)
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


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_menu_repas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuRecyclerView = view.findViewById(R.id.menu_recycler_view)

        menuAdapter = MultiViewAdapter()
        menuAdapter.registerItemBinders(MenuHeaderBinder(), MenuItemBinder(resources, requireContext()))
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
        observeData()

    }
    private fun observeData() {
        placeholder.startShimmer()

        menusViewModel.getStartersData().observe(viewLifecycleOwner) {
            placeholder.stopShimmer()
            placeholder.visibility = View.GONE
            it.forEach { starters ->
                startersList.add(starters)
                menuAdapter.notifyDataSetChanged()
            }
            startersSection.setItem("Entrée")
        }
        menusViewModel.getMainCoursesData().observe(viewLifecycleOwner) {
            it.forEach { mainCourses ->
                mainCoursesList.add(mainCourses)
                menuAdapter.notifyDataSetChanged()
            }
            mainCoursesSection.setItem("Plat")
        }
        menusViewModel.getDessertsData().observe(viewLifecycleOwner) {
            it.forEach { dessert ->
                dessertsList.add(dessert)
                menuAdapter.notifyDataSetChanged()
            }
            dessertsSection.setItem("Dessert")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        menuRecyclerView.apply {
            this.adapter = null
            this.layoutManager = null
        }
    }
}