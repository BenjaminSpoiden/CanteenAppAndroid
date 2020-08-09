package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.FragmentListener
import be.technifutur.devmob9.projet_cantinapp_android.model.data.DishesType
import kotlinx.android.synthetic.main.loading_placeholder_menu.view.*
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class MenuItemBinder(val resources: Resources, context: Context): ItemBinder<DishesType, MenuItemBinder.MenuItemViewHolder>(){

    private val fragmentListener = context as FragmentListener?

    override fun createViewHolder(parent: ViewGroup?): MenuItemViewHolder {
        return MenuItemViewHolder(inflate(parent!!, R.layout.recyclerview_menu_item))
    }

    override fun bindViewHolder(holder: MenuItemViewHolder?, item: DishesType) {
        when(item) {
            is DishesType.Starters -> {
                holder?.menuName?.text = item.name
                holder?.descriptionMenu?.text = item.description
                holder?.illustrationMenu?.setImageResource(R.drawable.menu_illustration)
            }
            is DishesType.MainCourses -> {
                holder?.menuName?.text = item.name
                holder?.descriptionMenu?.text = item.description
                holder?.illustrationMenu?.setImageResource(R.drawable.menu_illustration)
            }
            is DishesType.Desserts -> {
                holder?.menuName?.text = item.name
                holder?.descriptionMenu?.text = item.description
                holder?.illustrationMenu?.setImageResource(R.drawable.menu_illustration)
            }
        }
    }

    override fun canBindData(item: Any?): Boolean {
        return item is DishesType
    }

    inner class MenuItemViewHolder(v: View): ItemViewHolder<DishesType>(v){
        val menuName: TextView = v.findViewById(R.id.type_menu)
        val descriptionMenu: TextView = v.findViewById(R.id.menu_description)
        val illustrationMenu: ImageView = v.findViewById(R.id.menu_illustration)
        private val detailButton: ImageView = v.findViewById(R.id.menu_detail_button)

        init {
            v.menu_bg.setOnClickListener {
                v.menu_bg.isChecked = !v.menu_bg.isChecked
                if(v.menu_bg.isChecked) {
                    Log.d("ClickEvent", "${menuName.text}, ${descriptionMenu.text}")
                    v.menu_bg.setCardBackgroundColor(resources.getColor(R.color.tameGreen, resources.newTheme()))
                }else {
                    Log.d("ClickEvent", "Unchecked")
                    v.menu_bg.setCardBackgroundColor(Color.WHITE)
                }
            }
            detailButton.setOnClickListener {
                Toast.makeText(v.context, "Test", Toast.LENGTH_SHORT).show()
                fragmentListener?.openDetailFragment()
            }
        }
    }
}