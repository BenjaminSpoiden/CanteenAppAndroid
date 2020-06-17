package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.MenuItemModel
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.LAYOUT
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.LAYOUT_ID
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.MenuItemAdapter
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class MenuSandwichFragment: Fragment() {

    companion object {
        fun getInstance() = MenuSandwichFragment()
    }

    private lateinit var sandwichRecyclerView: RecyclerView
    private val itemAdapter = ItemAdapter<MenuItemAdapter>()
    private val fastAdapter = FastAdapter.with(itemAdapter)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_menu_sandwich, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sandwichRecyclerView = view.findViewById(R.id.sandwich_recycler_view)
        sandwichRecyclerView.apply {
            this.adapter = fastAdapter
            this.layoutManager = GridLayoutManager(context, 2)
        }
        mockDataItemAdapter()
    }

    private fun mockDataItemAdapter(layout: Int = LAYOUT, layoutID: Int = LAYOUT_ID) {
        itemAdapter.add(MenuItemAdapter(MenuItemModel("DAGOBERT", getString(R.string.mock_desc), R.drawable.menu_illustration), layout, layoutID))
        itemAdapter.add(MenuItemAdapter(MenuItemModel("THON", getString(R.string.mock_desc), R.drawable.menu_illustration), layout, layoutID))
        itemAdapter.add(MenuItemAdapter(MenuItemModel("JAMBOM FROMAGE", getString(R.string.mock_desc), R.drawable.menu_illustration), layout, layoutID))

    }
}