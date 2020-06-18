package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.MenuItemModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem

class SandwichItemAdapter(val menuItemModel: MenuItemModel): AbstractItem<SandwichItemAdapter.SandwichItemViewHolder>() {

    override val layoutRes: Int
        get() = R.layout.recyclerview_sandwich_item
    override val type: Int
        get() = R.id.recycler_view_sandwich_item_id

    override fun getViewHolder(v: View): SandwichItemViewHolder {
        return SandwichItemViewHolder(v)
    }

    inner class SandwichItemViewHolder(itemView: View): FastAdapter.ViewHolder<SandwichItemAdapter>(itemView) {
        private val typeSandwich: TextView = itemView.findViewById(R.id.type_sandwich)
        private val descriptionSandwich: TextView = itemView.findViewById(R.id.sandwich_description)
        private val illustrationSandwich: ImageView = itemView.findViewById(R.id.sandwich_illustration)

        override fun bindView(item: SandwichItemAdapter, payloads: List<Any>) {
            typeSandwich.text = item.menuItemModel.menuType
            descriptionSandwich.text = item.menuItemModel.menuDescription
            item.menuItemModel.menuIllustration?.let {
                illustrationSandwich.setImageResource(it)
            }
        }

        override fun unbindView(item: SandwichItemAdapter) {
            typeSandwich.text = null
            descriptionSandwich.text = null
        }

    }

}