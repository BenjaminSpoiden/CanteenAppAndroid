package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.ItemSelectedListener
import be.technifutur.devmob9.projet_cantinapp_android.model.data.MenuItemModel
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.MenuItemAdapter
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericAdapter
import com.mikepenz.fastadapter.GenericItem

class MenuRepasFragment: BaseFragment(){

    companion object {
        fun getInstance() = MenuRepasFragment()
    }

    override val title: String
        get() = "Menu"

    private lateinit var menuRecyclerView: RecyclerView
    private lateinit var menuItemAdapter: MenuItemAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_menu_repas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuRecyclerView = view.findViewById(R.id.menu_recycler_view)
    }
    override fun onStart() {
        super.onStart()
        menuItemAdapter = MenuItemAdapter(mockDataItemAdapter(), requireContext(), resources)
        menuItemAdapter.notifyDataSetChanged()


        menuRecyclerView.apply {
            this.adapter = menuItemAdapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun mockDataItemAdapter() = listOf(
        MenuItemModel(isHeader = true, headerName = "Entree"),
        MenuItemModel("Jus de Cyprine 1", getString(R.string.mock_desc), R.drawable.menu_illustration),
        MenuItemModel("Jus de Cyprine 2", getString(R.string.mock_desc), R.drawable.menu_illustration),
        MenuItemModel("Jus de Cyprine 3", getString(R.string.mock_desc), R.drawable.menu_illustration),
        MenuItemModel(isHeader = true, headerName = "Plat"),
        MenuItemModel("Jus de Cyprine 4", getString(R.string.mock_desc), R.drawable.menu_illustration),
        MenuItemModel("Jus de Cyprine 5", getString(R.string.mock_desc), R.drawable.menu_illustration),
        MenuItemModel("Jus de Cyprine 6", getString(R.string.mock_desc), R.drawable.menu_illustration),
        MenuItemModel(isHeader = true, headerName = "Dessert"),
        MenuItemModel("Jus de Cyprine 7", getString(R.string.mock_desc), R.drawable.menu_illustration),
        MenuItemModel("Jus de Cyprine 8", getString(R.string.mock_desc), R.drawable.menu_illustration),
        MenuItemModel("Jus de Cyprine 9", getString(R.string.mock_desc), R.drawable.menu_illustration)
    )

    override fun onDestroy() {
        super.onDestroy()
        menuRecyclerView.apply {
            this.adapter = null
            this.layoutManager = null
        }
    }
}