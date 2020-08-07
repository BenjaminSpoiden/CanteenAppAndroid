package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.ItemSelectedListener
import be.technifutur.devmob9.projet_cantinapp_android.model.data.Food
import be.technifutur.devmob9.projet_cantinapp_android.model.data.MenuItemModel
import be.technifutur.devmob9.projet_cantinapp_android.view.MenuHeaderBinder
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.MenuItemAdapter
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.MenuItemBinder
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericAdapter
import com.mikepenz.fastadapter.GenericItem
import mva2.adapter.ItemSection
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter

class MenuRepasFragment: BaseFragment(){

    companion object {
        fun getInstance() = MenuRepasFragment()
    }

    override val title: String
        get() = "Menu"

    private lateinit var menuRecyclerView: RecyclerView
    private lateinit var menuItemAdapter: MenuItemAdapter

    private lateinit var menuAdapter: MultiViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_menu_repas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuRecyclerView = view.findViewById(R.id.menu_recycler_view)
    }
    @SuppressLint("RestrictedApi")
    override fun onStart() {
        super.onStart()
        menuItemAdapter = MenuItemAdapter(mockDataItemAdapter(), requireContext(), resources)
        menuItemAdapter.notifyDataSetChanged()

        menuAdapter = MultiViewAdapter()
        val itemSection = ItemSection<Food>()
        val listSection = ListSection<MenuItemModel>()


        menuAdapter.registerItemBinders(MenuHeaderBinder(), MenuItemBinder())

        menuAdapter.addSection(itemSection)
        menuAdapter.addSection(listSection)

        itemSection.setItem(Food("Entrée"))
        listSection.add(MenuItemModel("", "", R.drawable.menu_illustration, false, ""))
        listSection.add(MenuItemModel("", "", R.drawable.menu_illustration, false, ""))
        listSection.add(MenuItemModel("", "", R.drawable.menu_illustration, false, ""))
        listSection.add(MenuItemModel("", "", R.drawable.menu_illustration, false, ""))
        listSection.add(MenuItemModel("", "", R.drawable.menu_illustration, false, ""))
        listSection.add(MenuItemModel("", "", R.drawable.menu_illustration, false, ""))
        listSection.add(MenuItemModel("", "", R.drawable.menu_illustration, false, ""))


        menuRecyclerView.apply {
            this.adapter = menuItemAdapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }


    private fun mockDataItemAdapter() = listOf(
        MenuItemModel("Jus de Cyprine 1", getString(R.string.mock_desc), R.drawable.menu_illustration, false, ""),
        MenuItemModel("Jus de Cyprine 2", getString(R.string.mock_desc), R.drawable.menu_illustration, false, ""),
        MenuItemModel("Jus de Cyprine 3", getString(R.string.mock_desc), R.drawable.menu_illustration, false, ""),
        MenuItemModel("Jus de Cyprine 4", getString(R.string.mock_desc), R.drawable.menu_illustration, false, ""),
        MenuItemModel("Jus de Cyprine 5", getString(R.string.mock_desc), R.drawable.menu_illustration, false, "")
    )

    override fun onDestroy() {
        super.onDestroy()
        menuRecyclerView.apply {
            this.adapter = null
            this.layoutManager = null
        }
    }
}