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
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.DataAdapter
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.MenuItem
//import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.menus.MenuSectionAdapter
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.menus.MenuSections
import be.technifutur.devmob9.projet_cantinapp_android.view.ui.activities.MainActivity
import com.google.android.material.card.MaterialCardView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.listeners.ClickEventHook
import java.util.ArrayList

class MenuRepasFragment: BaseFragment(){

    companion object {
        fun getInstance() = MenuRepasFragment()
    }

    override val title: String
        get() = "Menu"

    private lateinit var menuRecyclerView: RecyclerView
//    private lateinit var menuSectionsAdapter: MenuSectionAdapter

    private lateinit var dataAdapter: DataAdapter

//    private val itemAdapter = ItemAdapter<MenuItem>()
//    private val fastAdapter = FastAdapter.with(itemAdapter)

//    private var fragmentListener: FragmentListener? = null
    private var itemSelectedListener: ItemSelectedListener? = null

//    private var isSelected: Boolean = false

    private var sectionMenuArrayList = ArrayList<MenuSections>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_menu_repas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menuRecyclerView = view.findViewById(R.id.menu_recycler_view)
//        menuSectionsAdapter = MenuSectionAdapter(sectionMenuArrayList, requireContext())
//        menuSectionsAdapter.notifyDataSetChanged()

        dataAdapter = DataAdapter(mockInitModelData(), "Name")
        dataAdapter.notifyDataSetChanged()


//        fastAdapter.addEventHook(object: ClickEventHook<MenuItem>() {
//            override fun onBind(viewHolder: RecyclerView.ViewHolder): View? {
//                return if(viewHolder is MenuItem.MenuItemViewHolder) {
//                    viewHolder.detail
//                } else {
//                    null
//                }
//            }
//            override fun onClick(v: View, position: Int, fastAdapter: FastAdapter<MenuItem>, item: MenuItem) {
//                Toast.makeText(context, "Clicked on detail: $position", Toast.LENGTH_SHORT).show()
//                fragmentListener?.openDetailFragment()
//            }
//        })
//
//        fastAdapter.onClickListener = { v, _, _, position ->
//            val check = v?.findViewById<ImageView>(R.id.selected_menu_item)
//            val menuCardView = v?.findViewById<MaterialCardView>(R.id.menu_bg)
//
//            if (check != null && menuCardView != null) {
//                this.isSelected = !this.isSelected
//                isMenuItemSelected(isSelected, check, menuCardView)
//            }
////            itemSelectedListener?.onNumberItemSelected(position)
//            Toast.makeText(context, "TEST", Toast.LENGTH_SHORT).show()
//            true
//        }
    }

    override fun onStart() {
        super.onStart()

        val listOfType = listOf("Entrée", "Plat", "Dessert")
        listOfType.forEach {
            mockInitData(it, mockInitModelData())
        }

        menuRecyclerView.apply {
            this.adapter = dataAdapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun hashMapOfData(sectionName: String, sectionItems: List<MenuItemModel>): HashMap<String, List<MenuItemModel>> {
        return hashMapOf(
            sectionName to sectionItems
        )
    }

    private fun mockInitData(sectionName: String, listOfModel: List<MenuItemModel>) {
        val section = hashMapOfData(sectionName, listOfModel)

        section.forEach {
            sectionMenuArrayList.add(MenuSections(it.key, it.value))
        }
    }

    private fun mockInitModelData(): List<MenuItemModel> {
        return listOf(
            MenuItemModel("Soupe de Cyprine", getString(R.string.mock_desc), R.drawable.menu_illustration),
            MenuItemModel("Boulette de Viande", getString(R.string.mock_desc), R.drawable.menu_illustration),
            MenuItemModel("Gateau de Fromage", getString(R.string.mock_desc), R.drawable.menu_illustration)
        )
    }

//    private fun mockDataItemAdapter(layout: Int = LAYOUT, layoutID: Int = LAYOUT_ID) {
//        itemAdapter.add(MenuItem(MenuItemModel("ENTREE", getString(R.string.mock_desc), R.drawable.menu_illustration), layout, layoutID))
//        itemAdapter.add(MenuItem(MenuItemModel("PLAT", getString(R.string.mock_desc), R.drawable.menu_illustration), layout, layoutID))
//        itemAdapter.add(MenuItem(MenuItemModel("DESSERT", getString(R.string.mock_desc), R.drawable.menu_illustration), layout, layoutID))
//    }

//    private fun isMenuItemSelected(isSelected: Boolean, check: ImageView, background: MaterialCardView) {
//        if(isSelected) {
//            check.visibility = View.VISIBLE
//            background.setCardBackgroundColor(Color.parseColor("#DBF9D8"))
//
//        }else {
//            check.visibility = View.INVISIBLE
//            background.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
//        }
//    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if(context is FragmentListener) {
//            fragmentListener = context
//        }
////        itemSelectedListener = getInstanceAddingItemFragment
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        fragmentListener = null
////        itemSelectedListener = null
//    }

    override fun onDestroy() {
        super.onDestroy()
        menuRecyclerView.apply {
            this.adapter = null
            this.layoutManager = null
        }
    }
}