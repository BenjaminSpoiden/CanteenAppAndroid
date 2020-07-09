package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.MenuItemModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem

class SandwichItemAdapter(val menuItemModel: MenuItemModel): AbstractItem<SandwichItemAdapter.ViewHolder>() {
    override val layoutRes: Int
        get() = R.layout.recyclerview_sandwich_item
    override val type: Int
        get() = R.id.recycler_view_sandwich_item_id

    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)


    inner class ViewHolder(v: View): FastAdapter.ViewHolder<SandwichItemAdapter>(v){
        private val typeMenu: TextView = itemView.findViewById(R.id.type_sandwich)
        private val descriptionMenu: TextView = itemView.findViewById(R.id.sandwich_description)
        private val illustrationMenu: ImageView = itemView.findViewById(R.id.sandwich_illustration)
        val detail: ImageView = itemView.findViewById(R.id.sandwich_detail_btn)

        override fun bindView(item: SandwichItemAdapter, payloads: List<Any>) {
            typeMenu.text = item.menuItemModel.menuType
            descriptionMenu.text = item.menuItemModel.menuDescription
            item.menuItemModel.menuIllustration?.let { illustrationMenu.setImageResource(it) }
        }

        override fun unbindView(item: SandwichItemAdapter) {
            typeMenu.text = null
            descriptionMenu.text = null
        }
    }
}