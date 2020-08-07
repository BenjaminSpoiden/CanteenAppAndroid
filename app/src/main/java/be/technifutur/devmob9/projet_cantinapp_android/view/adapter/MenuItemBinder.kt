package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import be.technifutur.DishesType
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.MenuItemModel
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class MenuItemBinder: ItemBinder<MenuItemModel, MenuItemBinder.MenuItemViewHolder>(){

    override fun createViewHolder(parent: ViewGroup?): MenuItemViewHolder {
        return MenuItemViewHolder(inflate(parent!!, R.layout.recyclerview_menu_item))
    }

    override fun bindViewHolder(holder: MenuItemViewHolder?, item: MenuItemModel?) {
        holder?.menuName?.text = item?.menuItemName
        holder?.descriptionMenu?.text = item?.menuDescription
        item?.menuIllustration?.let {
            holder?.illustrationMenu?.setImageResource(it)
        }
    }

    override fun canBindData(item: Any?): Boolean {
        return item is MenuItemModel
    }

    inner class MenuItemViewHolder(v: View): ItemViewHolder<MenuItemModel>(v){
        val menuName: TextView = v.findViewById(R.id.type_menu)
        val descriptionMenu: TextView = v.findViewById(R.id.menu_description)
        val illustrationMenu: ImageView = v.findViewById(R.id.menu_illustration)
    }
}