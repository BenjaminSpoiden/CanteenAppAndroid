package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.MenuItemModel
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.LAYOUT
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.LAYOUT_ID
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.GenericMenuItemAdapter
import be.technifutur.devmob9.projet_cantinapp_android.view.ui.activities.MainActivity
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class MenuRepasFragment: BaseFragment() {

    companion object {
        fun getInstance() = MenuRepasFragment()
    }

    private lateinit var menuRecyclerView: RecyclerView
    private val itemAdapter = ItemAdapter<GenericMenuItemAdapter>()
    private val fastAdapter = FastAdapter.with(itemAdapter)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_menu_repas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setToolBarTitle("Menu")
        menuRecyclerView = view.findViewById(R.id.menu_recycler_view)
        menuRecyclerView.apply {
            this.adapter = fastAdapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
        mockDataItemAdapter()
    }

    override fun onResume() {
        super.onResume()
        mockDataItemAdapter()
    }

    override fun onDetach() {
        super.onDetach()
        menuRecyclerView.adapter = null
        menuRecyclerView.layoutManager = null
    }

    private fun mockDataItemAdapter(layout: Int = LAYOUT, layoutID: Int = LAYOUT_ID) {
        itemAdapter.add(GenericMenuItemAdapter(MenuItemModel("ENTREE", getString(R.string.mock_desc), R.drawable.menu_illustration), layout, layoutID))
        itemAdapter.add(GenericMenuItemAdapter(MenuItemModel("PLAT", getString(R.string.mock_desc), R.drawable.menu_illustration), layout, layoutID))
        itemAdapter.add(GenericMenuItemAdapter(MenuItemModel("DESSERT", getString(R.string.mock_desc), R.drawable.menu_illustration), layout, layoutID))
    }
}