package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.FragmentListener
import be.technifutur.devmob9.projet_cantinapp_android.model.data.MenuItemModel
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.CroissantItem
import com.google.android.material.card.MaterialCardView
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
    private val itemAdapter = ItemAdapter<CroissantItem>()
    private val fastAdapter = FastAdapter.with(itemAdapter)
    private var fragmentListener: FragmentListener? = null

    private var isSelected: Boolean = false

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
            this.layoutManager = GridLayoutManager(context, 2)
        }
        mockDataItemAdapter()

        fastAdapter.addEventHook(object: ClickEventHook<CroissantItem>() {
            override fun onBind(viewHolder: RecyclerView.ViewHolder): View? {
                return if(viewHolder is CroissantItem.CroissantViewHolder) {
                    viewHolder.detail
                } else {
                    null
                }
            }
            override fun onClick(
                v: View,
                position: Int,
                fastAdapter: FastAdapter<CroissantItem>,
                item: CroissantItem
            ) {
                Toast.makeText(context, "Clicked on detail: $position", Toast.LENGTH_SHORT).show()
                fragmentListener?.openDetailFragment()
            }
        })

        fastAdapter.onClickListener = { v, adapter, item, position ->
            val check = v?.findViewById<ImageView>(R.id.selected_croissant_item)
            val croissantCardView = v?.findViewById<MaterialCardView>(R.id.croissant_menu_bg)

            if(check != null && croissantCardView != null) {
                this.isSelected = !this.isSelected
                isMenuItemSelected(isSelected, check, croissantCardView)
            }

            true
        }
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

    private fun isMenuItemSelected(isSelected: Boolean, check: ImageView, background: MaterialCardView) {
        if(isSelected) {
            check.visibility = View.VISIBLE
            background.setCardBackgroundColor(Color.parseColor("#DBF9D8"))

        }else {
            check.visibility = View.INVISIBLE
            background.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
        }
    }


    private fun mockDataItemAdapter() {
        for (i in 1..10) {
            itemAdapter.add(CroissantItem(MenuItemModel("CROISSANT", getString(R.string.croissant_desc), R.drawable.sandwich_illustration)))
        }
        fastAdapter.notifyAdapterDataSetChanged()
    }
}