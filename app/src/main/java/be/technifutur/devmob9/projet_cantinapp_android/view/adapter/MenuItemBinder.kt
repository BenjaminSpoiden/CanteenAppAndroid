package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.FragmentListener
import be.technifutur.devmob9.projet_cantinapp_android.model.data.DishesType
import com.google.android.material.card.MaterialCardView
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class MenuItemBinder(context: Context, private val onItemClick: (MenuItemViewHolder) -> Unit): ItemBinder<DishesType, MenuItemBinder.MenuItemViewHolder>(){

    private val fragmentListener = context as FragmentListener?

    override fun createViewHolder(parent: ViewGroup?): MenuItemViewHolder {
        return MenuItemViewHolder(inflate(parent!!, R.layout.recyclerview_menu_item))
    }

    @SuppressLint("SetTextI18n")
    override fun bindViewHolder(holder: MenuItemViewHolder?, item: DishesType) {
        when(item) {
            is DishesType.Starters -> {
                holder?.menuName?.text = item.name
                holder?.descriptionMenu?.text = item.description
                holder?.illustrationMenu?.setImageResource(R.drawable.menu_illustration)
                holder?.menuPrice?.text = "${item.price},00 €"
            }
            is DishesType.MainCourses -> {
                holder?.menuName?.text = item.name
                holder?.descriptionMenu?.text = item.description
                holder?.illustrationMenu?.setImageResource(R.drawable.menu_illustration)
                holder?.menuPrice?.text = "${item.price},00 €"
            }
            is DishesType.Desserts -> {
                holder?.menuName?.text = item.name
                holder?.descriptionMenu?.text = item.description
                holder?.illustrationMenu?.setImageResource(R.drawable.menu_illustration)
                holder?.menuPrice?.text = "${item.price},00 €"
            }
        }
    }

    override fun canBindData(item: Any?): Boolean {
        return item is DishesType
    }

    override fun initViewHolder(holder: MenuItemViewHolder?) {
        super.initViewHolder(holder)
        holder?.menuCard?.setOnClickListener {
            onItemClick(holder)
        }
        holder?.detailButton?.setOnClickListener {
            fragmentListener?.openDetailFragment()
        }
    }

    inner class MenuItemViewHolder(v: View): ItemViewHolder<DishesType>(v){
        val menuName: TextView = v.findViewById(R.id.type_menu)
        val descriptionMenu: TextView = v.findViewById(R.id.menu_description)
        val illustrationMenu: ImageView = v.findViewById(R.id.menu_illustration)
        val detailButton: ImageView = v.findViewById(R.id.menu_detail_button)
        val menuCard: MaterialCardView = v.findViewById(R.id.menu_bg)
        val menuPrice: TextView = v.findViewById(R.id.menu_price)
    }
}