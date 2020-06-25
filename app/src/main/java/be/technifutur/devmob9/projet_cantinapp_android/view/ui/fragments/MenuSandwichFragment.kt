package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.FragmentListener
import be.technifutur.devmob9.projet_cantinapp_android.model.data.MenuItemModel
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.MenuItemAdapter
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.listeners.ClickEventHook

class MenuSandwichFragment: BaseFragment() {
    companion object {
        fun getInstance() = MenuSandwichFragment()
    }
    override val title: String
        get() = "Sandwich"

    private lateinit var sandwichRecyclerView: RecyclerView
    private val itemAdapter = ItemAdapter<MenuItemAdapter>()
    private val fastAdapter = FastAdapter.with(itemAdapter)
    private var fragmentListener: FragmentListener? = null

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentListener){
            fragmentListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        sandwichRecyclerView.adapter = null
        sandwichRecyclerView.layoutManager = null
        fragmentListener = null
    }

    private fun mockDataItemAdapter(layout: Int = R.layout.recyclerview_sandwich_item, layoutID: Int = R.id.recycler_view_sandwich_item_id) {
        itemAdapter.add(MenuItemAdapter(MenuItemModel("DAGOBERT", getString(R.string.sandwich_desc), R.drawable.sandwich_illustration), layout, layoutID))
        itemAdapter.add(MenuItemAdapter(MenuItemModel("THON", getString(R.string.sandwich_desc), R.drawable.sandwich_illustration), layout, layoutID))
        itemAdapter.add(MenuItemAdapter(MenuItemModel("JAMBOM FROMAGE", getString(R.string.sandwich_desc), R.drawable.sandwich_illustration), layout, layoutID))
        itemAdapter.add(MenuItemAdapter(MenuItemModel("DAGOBERT", getString(R.string.sandwich_desc), R.drawable.sandwich_illustration), layout, layoutID))
        itemAdapter.add(MenuItemAdapter(MenuItemModel("THON", getString(R.string.sandwich_desc), R.drawable.sandwich_illustration), layout, layoutID))
        itemAdapter.add(MenuItemAdapter(MenuItemModel("JAMBOM FROMAGE", getString(R.string.sandwich_desc), R.drawable.sandwich_illustration), layout, layoutID))
        fastAdapter.notifyAdapterDataSetChanged()
    }
}