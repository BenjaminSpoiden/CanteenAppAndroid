package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.MenuItemModel
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.TYPE_HEADER
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.TYPE_ITEM
import kotlinx.android.synthetic.main.recyclerview_menu_item.view.*
import java.lang.IllegalArgumentException

class DataAdapter(private var adapterDataList: List<Any>, private var name: String): RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when(viewType) {
            TYPE_HEADER -> {
                val headerView = LayoutInflater.from(parent.context).inflate(R.layout.section_menu_row, parent, false)
                HeaderViewHolder(headerView)
            }
            TYPE_ITEM -> {
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_menu_item, parent, false)
                ChildViewHolder(itemView).also {
                    itemView.menu_bg.setOnClickListener { v->
                        v.menu_bg.isChecked = !v.menu_bg.isChecked

                        if(v.menu_bg.isChecked) {
                            Log.d("ClickEvent", "Checked")
                        }else {
                            Log.d("ClickEvent", "Unchecked")
                        }
                    }
                }
            }

            else -> throw IllegalArgumentException("Invalid View Type")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val elements = adapterDataList[position]

        if(getItemViewType(position) == TYPE_HEADER) {
            (holder as HeaderViewHolder).bind(name)
        }else if (getItemViewType(position) == TYPE_ITEM) {
            (holder as ChildViewHolder).bind(elements as MenuItemModel)
        }
    }

    override fun getItemCount(): Int {
        return adapterDataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if(position == 0) {
            TYPE_HEADER
        } else {
            TYPE_ITEM
        }
    }


    inner class HeaderViewHolder(itemView: View): BaseViewHolder<String>(itemView) {
        private val sectionMenuName: TextView = itemView.findViewById(R.id.header_menu_text)

        override fun bind(holder: String) {
            sectionMenuName.text = "name"
        }
    }

    inner class ChildViewHolder(itemView: View): BaseViewHolder<MenuItemModel>(itemView) {
        private val menuName: TextView = itemView.findViewById(R.id.type_menu)
        private val descriptionMenu: TextView = itemView.findViewById(R.id.menu_description)
        private val illustrationMenu: ImageView = itemView.findViewById(R.id.menu_illustration)

        override fun bind(holder: MenuItemModel) {
            menuName.text = holder.menuItemName
            descriptionMenu.text = holder.menuDescription
            holder.menuIllustration?.let { illustrationMenu.setImageResource(it) }
        }
    }
}