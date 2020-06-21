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
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.GenericMenuItemAdapter
import be.technifutur.devmob9.projet_cantinapp_android.view.ui.activities.MainActivity
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class MenuCroissantFragment: BaseFragment() {

    companion object {
        fun getInstance() = MenuCroissantFragment()
    }

    private lateinit var croissantRecyclerView: RecyclerView
    private val itemAdapter = ItemAdapter<GenericMenuItemAdapter>()
    private val fastAdapter = FastAdapter.with(itemAdapter)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu_croissant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setToolBarTitle("Croissant")
        croissantRecyclerView = view.findViewById(R.id.croissant_recycler_view)
        croissantRecyclerView.apply {
            this.adapter = fastAdapter
            this.layoutManager = GridLayoutManager(context, 3)
        }
        mockDataItemAdapter()
    }

    override fun onDetach() {
        super.onDetach()
        croissantRecyclerView.adapter = null
        croissantRecyclerView.layoutManager = null
    }

    private fun mockDataItemAdapter(layout: Int = R.layout.recyclerview_croissant_item, layoutID: Int = R.id.recycler_view_croissant_item_id) {
        itemAdapter.add(GenericMenuItemAdapter(MenuItemModel("CROISSANT", getString(R.string.croissant_desc), R.drawable.croissant_illustration), layout, layoutID))
        itemAdapter.add(GenericMenuItemAdapter(MenuItemModel("CROISSANT", getString(R.string.croissant_desc), R.drawable.croissant_illustration), layout, layoutID))
        itemAdapter.add(GenericMenuItemAdapter(MenuItemModel("CROISSANT", getString(R.string.croissant_desc), R.drawable.croissant_illustration), layout, layoutID))
        itemAdapter.add(GenericMenuItemAdapter(MenuItemModel("CROISSANT", getString(R.string.croissant_desc), R.drawable.croissant_illustration), layout, layoutID))
        itemAdapter.add(GenericMenuItemAdapter(MenuItemModel("CROISSANT", getString(R.string.croissant_desc), R.drawable.croissant_illustration), layout, layoutID))
        itemAdapter.add(GenericMenuItemAdapter(MenuItemModel("CROISSANT", getString(R.string.croissant_desc), R.drawable.croissant_illustration), layout, layoutID))
        itemAdapter.add(GenericMenuItemAdapter(MenuItemModel("CROISSANT", getString(R.string.croissant_desc), R.drawable.croissant_illustration), layout, layoutID))
        itemAdapter.add(GenericMenuItemAdapter(MenuItemModel("CROISSANT", getString(R.string.croissant_desc), R.drawable.croissant_illustration), layout, layoutID))
        itemAdapter.add(GenericMenuItemAdapter(MenuItemModel("CROISSANT", getString(R.string.croissant_desc), R.drawable.croissant_illustration), layout, layoutID))
        itemAdapter.add(GenericMenuItemAdapter(MenuItemModel("CROISSANT", getString(R.string.croissant_desc), R.drawable.croissant_illustration), layout, layoutID))
        itemAdapter.add(GenericMenuItemAdapter(MenuItemModel("CROISSANT", getString(R.string.croissant_desc), R.drawable.croissant_illustration), layout, layoutID))
        itemAdapter.add(GenericMenuItemAdapter(MenuItemModel("CROISSANT", getString(R.string.croissant_desc), R.drawable.croissant_illustration), layout, layoutID))
        fastAdapter.notifyAdapterDataSetChanged()
    }
}