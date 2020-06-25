package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.MenuItemModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem

class MenuItemAdapter(val menuItemModel: MenuItemModel, override val layoutRes: Int, override val type: Int): AbstractItem<MenuItemAdapter.MenuItemViewHolder>() {

    override fun getViewHolder(v: View): MenuItemViewHolder {
        return MenuItemViewHolder(v)
    }

    inner class MenuItemViewHolder(itemView: View): FastAdapter.ViewHolder<MenuItemAdapter>(itemView) {

        private val typeMenu: TextView = itemView.findViewById(R.id.type_menu)
        private val descriptionMenu: TextView = itemView.findViewById(R.id.menu_description)
        private val illustrationMenu: ImageView = itemView.findViewById(R.id.menu_illustration)
        val detail: ImageView = itemView.findViewById(R.id.menu_detail_button)

        override fun bindView(item: MenuItemAdapter, payloads: List<Any>) {
            typeMenu.text = item.menuItemModel.menuType
            descriptionMenu.text = item.menuItemModel.menuDescription
            item.menuItemModel.menuIllustration?.let { illustrationMenu.setImageResource(it) }

        }

        override fun unbindView(item: MenuItemAdapter) {
            typeMenu.text = null
            descriptionMenu.text = null
            detail.setOnClickListener(null)
        }
    }
}