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
import be.technifutur.devmob9.projet_cantinapp_android.model.data.MenuItemModel
import be.technifutur.devmob9.projet_cantinapp_android.utils.cardState
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.GenericAdapter
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.viewholders.SandwichViewHolder
import kotlinx.android.synthetic.main.recyclerview_sandwich_item.view.*

class MenuSandwichFragment: BaseFragment() {
    companion object {
        fun getInstance() = MenuSandwichFragment()
    }
    override val title: String
        get() = "Sandwich"

    private var sandwichRecyclerView: RecyclerView? = null
    private lateinit var sandwichAdapter: GenericAdapter<MenuItemModel>

    private var fragmentListener: FragmentListener? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_menu_sandwich, container, false)
    }

    /**
     * Faire une sorte d'extension pour centraliser le code qui se ressemble
     * Potentiel section comme sur iOs
     */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sandwichRecyclerView = view.findViewById(R.id.sandwich_recycler_view)
    }

    override fun onStart() {
        super.onStart()
        sandwichAdapter = object : GenericAdapter<MenuItemModel>(mockList()) {
            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return SandwichViewHolder(view).apply {
                    view.sandwich_menu_bg.setOnClickListener {
                        view.sandwich_menu_bg.isChecked = !view.sandwich_menu_bg.isChecked
                        cardState(view.sandwich_menu_bg.isChecked, view.sandwich_menu_bg)
                    }
                    view.sandwich_detail_btn.setOnClickListener {
                        Log.d("Click", "Clicked on detail at ${mockList()[this.adapterPosition]}")
                        fragmentListener?.openDetailFragmentWithDetails(mockList()[this.adapterPosition])
                    }
                }
            }

            override fun getLayoutID(position: Int, data: MenuItemModel): Int {
                return R.layout.recyclerview_sandwich_item
            }
        }
        sandwichRecyclerView?.apply {
            this.adapter = sandwichAdapter
            this.layoutManager = GridLayoutManager(context, 2)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentListener){
            fragmentListener = context
        }

    }

    override fun onDetach() {
        super.onDetach()
        fragmentListener = null
    }

    private fun mockList(): List<MenuItemModel> {
        val list = ArrayList<MenuItemModel>()

        for (i in 0..20) {
            list.add(MenuItemModel("DAGOBERT $i", getString(R.string.sandwich_desc), R.drawable.sandwich_illustration))
        }
        return list
    }

    override fun onDestroyView() {
        sandwichRecyclerView?.apply {
            this.adapter = null
            this.layoutManager = null
        }
        sandwichRecyclerView = null
        super.onDestroyView()
    }
}