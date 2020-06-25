package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.FragmentListener
import be.technifutur.devmob9.projet_cantinapp_android.model.data.MenuItemModel
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.LAYOUT
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.LAYOUT_ID
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.MenuItemAdapter
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.listeners.ClickEventHook

class MenuRepasFragment: BaseFragment() {
    companion object {
        fun getInstance() = MenuRepasFragment()
    }

    override val title: String
        get() = "Menu"

    private lateinit var menuRecyclerView: RecyclerView
    private val itemAdapter = ItemAdapter<MenuItemAdapter>()
    private val fastAdapter = FastAdapter.with(itemAdapter)
    private var fragmentListener: FragmentListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_menu_repas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuRecyclerView = view.findViewById(R.id.menu_recycler_view)
        menuRecyclerView.apply {
            this.adapter = fastAdapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
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
                fragmentListener?.openDetailFragment()
            }
        })

        fastAdapter.onClickListener = { v, adapter, item, position ->
            Toast.makeText(context, "TEST", Toast.LENGTH_SHORT).show()
            true
        }

    }

    private fun mockDataItemAdapter(layout: Int = LAYOUT, layoutID: Int = LAYOUT_ID) {
        itemAdapter.add(MenuItemAdapter(MenuItemModel("ENTREE", getString(R.string.mock_desc), R.drawable.menu_illustration), layout, layoutID))
        itemAdapter.add(MenuItemAdapter(MenuItemModel("PLAT", getString(R.string.mock_desc), R.drawable.menu_illustration), layout, layoutID))
        itemAdapter.add(MenuItemAdapter(MenuItemModel("DESSERT", getString(R.string.mock_desc), R.drawable.menu_illustration), layout, layoutID))
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentListener) {
            fragmentListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        fragmentListener = null
    }
}