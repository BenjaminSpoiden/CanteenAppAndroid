package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.FragmentListener
import be.technifutur.devmob9.projet_cantinapp_android.model.data.MenuItemModel
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.SandwichItem
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
    private val itemAdapter = ItemAdapter<SandwichItem>()
    private val fastAdapter = FastAdapter.with(itemAdapter)
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
        sandwichRecyclerView.apply {
            this.adapter = fastAdapter
            this.layoutManager = GridLayoutManager(context, 2)
        }
        mockDataItemAdapter()

        fastAdapter.addEventHook(object: ClickEventHook<SandwichItem>() {
            override fun onBind(viewHolder: RecyclerView.ViewHolder): View? {
                return if(viewHolder is SandwichItem.ViewHolder){
                    viewHolder.detail
                }else {
                    null
                }
            }

            override fun onClick(v: View, position: Int, fastAdapter: FastAdapter<SandwichItem>, item: SandwichItem) {
                fragmentListener?.openDetailFragment()
            }
        })

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

    private fun mockDataItemAdapter() {
        itemAdapter.add(SandwichItem(MenuItemModel("DAGOBERT", getString(R.string.sandwich_desc), R.drawable.sandwich_illustration)))
        itemAdapter.add(SandwichItem(MenuItemModel("THON", getString(R.string.sandwich_desc), R.drawable.sandwich_illustration)))
        itemAdapter.add(SandwichItem(MenuItemModel("JAMBOM FROMAGE", getString(R.string.sandwich_desc), R.drawable.sandwich_illustration)))
        itemAdapter.add(SandwichItem(MenuItemModel("DAGOBERT", getString(R.string.sandwich_desc), R.drawable.sandwich_illustration)))
        itemAdapter.add(SandwichItem(MenuItemModel("THON", getString(R.string.sandwich_desc), R.drawable.sandwich_illustration)))
        itemAdapter.add(SandwichItem(MenuItemModel("JAMBOM FROMAGE", getString(R.string.sandwich_desc), R.drawable.sandwich_illustration)))
        fastAdapter.notifyAdapterDataSetChanged()
    }
}