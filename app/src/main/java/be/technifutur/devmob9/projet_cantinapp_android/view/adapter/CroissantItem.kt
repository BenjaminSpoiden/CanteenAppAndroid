package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.MenuItemModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem

@Deprecated("Implemented Generic Adapter")
class CroissantItem(val menuItemModel: MenuItemModel): AbstractItem<CroissantItem.CroissantViewHolder>() {

    override val layoutRes: Int
        get() = R.layout.recyclerview_croissant_item
    override val type: Int
        get() = R.id.recycler_view_croissant_item_id

    override fun getViewHolder(v: View): CroissantViewHolder = CroissantViewHolder(v)

    inner class CroissantViewHolder(v: View): FastAdapter.ViewHolder<CroissantItem>(v){
        private val typeMenu: TextView = itemView.findViewById(R.id.type_croissant)
        private val descriptionMenu: TextView = itemView.findViewById(R.id.croissant_description)
        private val illustrationMenu: ImageView = itemView.findViewById(R.id.croissant_illustration)
        val detail: ImageView = itemView.findViewById(R.id.croissant_detail_btn)

        override fun bindView(item: CroissantItem, payloads: List<Any>) {
            typeMenu.text = item.menuItemModel.menuItemName
            descriptionMenu.text = item.menuItemModel.menuDescription
            item.menuItemModel.menuIllustration?.let { illustrationMenu.setImageResource(it) }
        }

        override fun unbindView(item: CroissantItem) {
            typeMenu.text = null
            descriptionMenu.text = null
        }
    }
}