package be.technifutur.devmob9.projet_cantinapp_android.view

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.Food
import be.technifutur.devmob9.projet_cantinapp_android.model.data.MenuItemModel
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class MenuHeaderBinder: ItemBinder<Food, MenuHeaderBinder.MenuHeaderViewHolder>() {

    override fun createViewHolder(parent: ViewGroup?): MenuHeaderViewHolder {
        return MenuHeaderViewHolder(inflate(parent!!, R.layout.recyclerview_header))
    }

    override fun bindViewHolder(holder: MenuHeaderViewHolder?, item: Food?) {
        holder?.headerText?.text = item?.name
    }

    override fun canBindData(item: Any?): Boolean {
        return item is Food
    }

    inner class MenuHeaderViewHolder(v: View): ItemViewHolder<Food>(v) {
        val headerText: TextView = v.findViewById(R.id.header_menu_text)
    }
}