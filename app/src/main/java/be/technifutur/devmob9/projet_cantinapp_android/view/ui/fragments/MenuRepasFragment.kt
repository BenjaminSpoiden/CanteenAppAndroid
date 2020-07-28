package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.FragmentListener
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.ItemSelectedListener
import be.technifutur.devmob9.projet_cantinapp_android.model.data.MenuItemModel
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.LAYOUT
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.LAYOUT_ID
import be.technifutur.devmob9.projet_cantinapp_android.utils.fragmentTransaction
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.MenuItem
import be.technifutur.devmob9.projet_cantinapp_android.view.ui.custom.AddingItemFragment
import com.google.android.material.card.MaterialCardView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.listeners.ClickEventHook
import kotlinx.android.synthetic.main.fragment_menu_repas.*

class MenuRepasFragment: BaseFragment(){

    companion object {
        fun getInstance() = MenuRepasFragment()
    }

    override val title: String
        get() = "Menu"

    private lateinit var menuRecyclerView: RecyclerView
    private val itemAdapter = ItemAdapter<MenuItem>()
    private val fastAdapter = FastAdapter.with(itemAdapter)

    private var fragmentListener: FragmentListener? = null
    private var itemSelectedListener: ItemSelectedListener? = null

    private val getInstanceAddingItemFragment = AddingItemFragment.getInstance()

    private var isSelected: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_menu_repas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentTransaction(getInstanceAddingItemFragment, R.id.display_selected_item_fragment)

        menuRecyclerView = view.findViewById(R.id.menu_recycler_view)
        menuRecyclerView.apply {
            this.adapter = fastAdapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        mockDataItemAdapter()

        fastAdapter.addEventHook(object: ClickEventHook<MenuItem>() {
            override fun onBind(viewHolder: RecyclerView.ViewHolder): View? {
                return if(viewHolder is MenuItem.MenuItemViewHolder) {
                    viewHolder.detail
                } else {
                    null
                }
            }
            override fun onClick(v: View, position: Int, fastAdapter: FastAdapter<MenuItem>, item: MenuItem) {
                Toast.makeText(context, "Clicked on detail: $position", Toast.LENGTH_SHORT).show()
                fragmentListener?.openDetailFragment()
            }
        })

        fastAdapter.onClickListener = { v, _, _, position ->
            val check = v?.findViewById<ImageView>(R.id.selected_menu_item)
            val menuCardView = v?.findViewById<MaterialCardView>(R.id.menu_bg)
            if (check != null && menuCardView != null) {
                this.isSelected = !this.isSelected
                isMenuItemSelected(isSelected, check, menuCardView)
            }
            itemSelectedListener?.onNumberItemSelected(position)
            display_selected_item_fragment.visibility = View.VISIBLE
            Toast.makeText(context, "TEST", Toast.LENGTH_SHORT).show()
            true
        }

    }

    private fun mockDataItemAdapter(layout: Int = LAYOUT, layoutID: Int = LAYOUT_ID) {
        itemAdapter.add(MenuItem(MenuItemModel("ENTREE", getString(R.string.mock_desc), R.drawable.menu_illustration), layout, layoutID))
        itemAdapter.add(MenuItem(MenuItemModel("PLAT", getString(R.string.mock_desc), R.drawable.menu_illustration), layout, layoutID))
        itemAdapter.add(MenuItem(MenuItemModel("DESSERT", getString(R.string.mock_desc), R.drawable.menu_illustration), layout, layoutID))
    }

    private fun isMenuItemSelected(isSelected: Boolean, check: ImageView, background: MaterialCardView) {
        if(isSelected) {
            check.visibility = View.VISIBLE
            background.setCardBackgroundColor(Color.parseColor("#DBF9D8"))

        }else {
            check.visibility = View.GONE
            background.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentListener) {
            fragmentListener = context
        }
        itemSelectedListener = getInstanceAddingItemFragment
    }

    override fun onDetach() {
        super.onDetach()
        fragmentListener = null
        itemSelectedListener = null
    }

    override fun onDestroy() {
        super.onDestroy()
        menuRecyclerView.apply {
            this.adapter = null
            this.layoutManager = null
        }
    }
}