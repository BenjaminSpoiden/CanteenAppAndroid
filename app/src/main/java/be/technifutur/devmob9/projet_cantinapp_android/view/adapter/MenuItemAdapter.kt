package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.FragmentListener
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.ItemSelectedListener
import be.technifutur.devmob9.projet_cantinapp_android.model.data.MenuItemModel
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.TYPE_HEADER
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.TYPE_ITEM
import kotlinx.android.synthetic.main.recyclerview_menu_item.view.*
import java.lang.IllegalArgumentException

class MenuItemAdapter(private val itemsList: List<MenuItemModel>, context: Context): RecyclerView.Adapter<BaseViewHolder<*>>() {

    private val openDetailFragmentListener = context as FragmentListener
    private val itemSelectedListener = context as ItemSelectedListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when(viewType) {
            TYPE_HEADER -> {
                val headerView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_header, parent, false)
                HeaderViewHolder(headerView)
            }
            TYPE_ITEM -> {
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_menu_item, parent, false)
                ChildViewHolder(itemView).also {
                    itemView.menu_bg.setOnClickListener { v->
                        v.menu_bg.isChecked = !v.menu_bg.isChecked
                        if(v.menu_bg.isChecked) {
                            Log.d("ClickEvent", "Checked")
                            itemSelectedListener.onNumberItemSelected(1)
                        }else {
                            Log.d("ClickEvent", "Unchecked")
                            itemSelectedListener.onNumberItemSelected(0)
                        }
                    }
                    itemView.menu_detail_button.setOnClickListener {_ ->
                        Toast.makeText(parent.context, "Clicked at position: ${it.adapterPosition}", Toast.LENGTH_SHORT).show()
                        openDetailFragmentListener.openDetailFragment()
                    }
                }
            }
            else -> throw IllegalArgumentException("Invalid View Type")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        if(getItemViewType(position) == TYPE_HEADER) {
            itemsList[position].headerName?.let {
                (holder as HeaderViewHolder).bind(it, position)
            }

        }else if (getItemViewType(position) == TYPE_ITEM) {
            (holder as ChildViewHolder).bind(itemsList, position)
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if(itemsList[position].isHeader) {
            TYPE_HEADER
        } else {
            TYPE_ITEM
        }
    }

    inner class HeaderViewHolder(itemView: View): BaseViewHolder<String>(itemView) {
        private val sectionMenuName: TextView = itemView.findViewById(R.id.header_menu_text)

        override fun bind(holder: String, position: Int) {
            sectionMenuName.text = holder
        }

        override fun unbind(holder: String, position: Int) {
            sectionMenuName.text = null
        }
    }

    inner class ChildViewHolder(itemView: View): BaseViewHolder<List<MenuItemModel>>(itemView) {
        private val menuName: TextView = itemView.findViewById(R.id.type_menu)
        private val descriptionMenu: TextView = itemView.findViewById(R.id.menu_description)
        private val illustrationMenu: ImageView = itemView.findViewById(R.id.menu_illustration)

        override fun bind(holder: List<MenuItemModel>, position: Int) {
            menuName.text = holder[position].menuItemName
            descriptionMenu.text = holder[position].menuDescription
            holder[position].menuIllustration?.let { illustrationMenu.setImageResource(it) }
        }

        override fun unbind(holder: List<MenuItemModel>, position: Int) {
            menuName.text = null
            descriptionMenu.text = null
        }
    }
}