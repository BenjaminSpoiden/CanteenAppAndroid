package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.FragmentListener
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.ItemSelectedListener
import be.technifutur.devmob9.projet_cantinapp_android.model.data.MenuItemModel
import be.technifutur.devmob9.projet_cantinapp_android.utils.cardState
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.GenericAdapter
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.viewholders.CroissantViewHolder
import kotlinx.android.synthetic.main.recyclerview_croissant_item.view.*

class MenuCroissantFragment: BaseFragment() {
    companion object {
        fun getInstance() = MenuCroissantFragment()
    }

    override val title: String
        get() = "Croissant"

    private lateinit var croissantRecyclerView: RecyclerView
    private lateinit var croissantAdapter: GenericAdapter<MenuItemModel>
    private var fragmentListener: FragmentListener? = null
    private var itemSelectedListener: ItemSelectedListener? = null


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

    }

    override fun onStart() {
        super.onStart()
        croissantAdapter = object : GenericAdapter<MenuItemModel>(mockDataItemAdapter()) {
            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return CroissantViewHolder(view).apply {
                    view.croissant_menu_bg.setOnClickListener {
                        view.croissant_menu_bg.isChecked = !view.croissant_menu_bg.isChecked
                        cardState(view.croissant_menu_bg.isChecked, view.croissant_menu_bg)
                    }
                    view.croissant_detail_btn.setOnClickListener {
                        Log.d("CLICKED", "Clicked on info at ${mockDataItemAdapter()[this.adapterPosition]}")
                    }
                }
            }
            override fun getLayoutID(position: Int, data: MenuItemModel): Int {
                return R.layout.recyclerview_croissant_item
            }
        }

        croissantRecyclerView.apply {
            this.adapter = croissantAdapter
            this.layoutManager = GridLayoutManager(context, 2)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentListener) {
            fragmentListener = context
        }
        if(context is ItemSelectedListener) {
            itemSelectedListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        croissantRecyclerView.apply {
            this.adapter = null
            this.layoutManager = null
        }
        fragmentListener = null
        itemSelectedListener = null
    }

    private fun mockDataItemAdapter(): List<MenuItemModel> {
        val list = ArrayList<MenuItemModel>()
        for (i in 1..10) {
            list.add(MenuItemModel("CROISSANT", menuDescription = getString(R.string.croissant_desc), menuIllustration = R.drawable.sandwich_illustration))
        }
        return list
    }
}