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

class MenuCroissantFragment: BaseFragment() {
    companion object {
        fun getInstance() = MenuCroissantFragment()
    }

    override val title: String
        get() = "Croissant"

    private lateinit var croissantRecyclerView: RecyclerView
    private val itemAdapter = ItemAdapter<MenuItemAdapter>()
    private val fastAdapter = FastAdapter.with(itemAdapter)
    private var fragmentListener: FragmentListener? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu_croissant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        croissantRecyclerView = view.findViewById(R.id.croissant_recycler_view)
        croissantRecyclerView.apply {
            this.adapter = fastAdapter
            this.layoutManager = GridLayoutManager(context, 3)
        }
        mockDataItemAdapter()

        fastAdapter.addEventHook(object: ClickEventHook<MenuItemAdapter>() {
            override fun onBind(viewHolder: RecyclerView.ViewHolder): View? {
                return if(viewHolder is MenuItemAdapter.MenuItemViewHolder) {
                    viewHolder.detail
                } else {
                    null
                }
            }
            override fun onClick(v: View, position: Int, fastAdapter: FastAdapter<MenuItemAdapter>, item: MenuItemAdapter) {
                Toast.makeText(context, "Clicked on detail: $position", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentListener) {
            fragmentListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        croissantRecyclerView.adapter = null
        croissantRecyclerView.layoutManager = null
        fragmentListener = null
    }

    private fun mockDataItemAdapter(layout: Int = R.layout.recyclerview_croissant_item, layoutID: Int = R.id.recycler_view_croissant_item_id) {
        itemAdapter.add(MenuItemAdapter(MenuItemModel("CROISSANT", getString(R.string.croissant_desc), R.drawable.croissant_illustration), layout, layoutID))
        itemAdapter.add(MenuItemAdapter(MenuItemModel("CROISSANT", getString(R.string.croissant_desc), R.drawable.croissant_illustration), layout, layoutID))
        itemAdapter.add(MenuItemAdapter(MenuItemModel("CROISSANT", getString(R.string.croissant_desc), R.drawable.croissant_illustration), layout, layoutID))
        itemAdapter.add(MenuItemAdapter(MenuItemModel("CROISSANT", getString(R.string.croissant_desc), R.drawable.croissant_illustration), layout, layoutID))
        itemAdapter.add(MenuItemAdapter(MenuItemModel("CROISSANT", getString(R.string.croissant_desc), R.drawable.croissant_illustration), layout, layoutID))
        itemAdapter.add(MenuItemAdapter(MenuItemModel("CROISSANT", getString(R.string.croissant_desc), R.drawable.croissant_illustration), layout, layoutID))
        itemAdapter.add(MenuItemAdapter(MenuItemModel("CROISSANT", getString(R.string.croissant_desc), R.drawable.croissant_illustration), layout, layoutID))
        itemAdapter.add(MenuItemAdapter(MenuItemModel("CROISSANT", getString(R.string.croissant_desc), R.drawable.croissant_illustration), layout, layoutID))
        itemAdapter.add(MenuItemAdapter(MenuItemModel("CROISSANT", getString(R.string.croissant_desc), R.drawable.croissant_illustration), layout, layoutID))
        itemAdapter.add(MenuItemAdapter(MenuItemModel("CROISSANT", getString(R.string.croissant_desc), R.drawable.croissant_illustration), layout, layoutID))
        itemAdapter.add(MenuItemAdapter(MenuItemModel("CROISSANT", getString(R.string.croissant_desc), R.drawable.croissant_illustration), layout, layoutID))
        itemAdapter.add(MenuItemAdapter(MenuItemModel("CROISSANT", getString(R.string.croissant_desc), R.drawable.croissant_illustration), layout, layoutID))
        fastAdapter.notifyAdapterDataSetChanged()
    }
}