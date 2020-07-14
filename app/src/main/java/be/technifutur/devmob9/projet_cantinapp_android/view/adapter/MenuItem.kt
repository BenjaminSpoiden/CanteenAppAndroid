package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.MenuItemModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem

class MenuItem(val menuItemModel: MenuItemModel, override val layoutRes: Int, override val type: Int): AbstractItem<MenuItem.MenuItemViewHolder>() {

    override fun getViewHolder(v: View): MenuItemViewHolder {
        return MenuItemViewHolder(v)
    }

    inner class MenuItemViewHolder(itemView: View): FastAdapter.ViewHolder<MenuItem>(itemView) {

        private val typeMenu: TextView = itemView.findViewById(R.id.type_menu)
        private val descriptionMenu: TextView = itemView.findViewById(R.id.menu_description)
        private val illustrationMenu: ImageView = itemView.findViewById(R.id.menu_illustration)
        val detail: ImageView = itemView.findViewById(R.id.menu_detail_button)

        override fun bindView(item: MenuItem, payloads: List<Any>) {
            typeMenu.text = item.menuItemModel.menuType
            descriptionMenu.text = item.menuItemModel.menuDescription
            item.menuItemModel.menuIllustration?.let { illustrationMenu.setImageResource(it) }

        }

        override fun unbindView(item: MenuItem) {
            typeMenu.text = null
            descriptionMenu.text = null
        }
    }
}