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
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.SandwichItemAdapter
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.listeners.ClickEventHook
import com.mikepenz.fastadapter.listeners.EventHook

class MenuSandwichFragment: BaseFragment() {
    companion object {
        fun getInstance() = MenuSandwichFragment()
    }
    override val title: String
        get() = "Sandwich"

    private lateinit var sandwichRecyclerView: RecyclerView
    private val itemAdapter = ItemAdapter<SandwichItemAdapter>()
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

        fastAdapter.addEventHook(object: ClickEventHook<SandwichItemAdapter>() {
            override fun onBind(viewHolder: RecyclerView.ViewHolder): View? {
                return if(viewHolder is SandwichItemAdapter.ViewHolder){
                    viewHolder.detail
                }else {
                    null
                }
            }

            override fun onClick(v: View, position: Int, fastAdapter: FastAdapter<SandwichItemAdapter>, item: SandwichItemAdapter) {
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
        itemAdapter.add(SandwichItemAdapter(MenuItemModel("DAGOBERT", getString(R.string.sandwich_desc), R.drawable.sandwich_illustration)))
        itemAdapter.add(SandwichItemAdapter(MenuItemModel("THON", getString(R.string.sandwich_desc), R.drawable.sandwich_illustration)))
        itemAdapter.add(SandwichItemAdapter(MenuItemModel("JAMBOM FROMAGE", getString(R.string.sandwich_desc), R.drawable.sandwich_illustration)))
        itemAdapter.add(SandwichItemAdapter(MenuItemModel("DAGOBERT", getString(R.string.sandwich_desc), R.drawable.sandwich_illustration)))
        itemAdapter.add(SandwichItemAdapter(MenuItemModel("THON", getString(R.string.sandwich_desc), R.drawable.sandwich_illustration)))
        itemAdapter.add(SandwichItemAdapter(MenuItemModel("JAMBOM FROMAGE", getString(R.string.sandwich_desc), R.drawable.sandwich_illustration)))
        fastAdapter.notifyAdapterDataSetChanged()
    }
}