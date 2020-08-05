package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.MenuItemModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import kotlin.math.log

@Deprecated("Implemented Generic Adapter")
class SandwichItem(val menuItemModel: MenuItemModel): AbstractItem<SandwichItem.ViewHolder>() {
    override val layoutRes: Int
        get() = R.layout.recyclerview_sandwich_item
    override val type: Int
        get() = R.id.recycler_view_sandwich_item_id

    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)


    inner class ViewHolder(v: View): FastAdapter.ViewHolder<SandwichItem>(v){
        private val typeMenu: TextView = itemView.findViewById(R.id.type_sandwich)
        private val descriptionMenu: TextView = itemView.findViewById(R.id.sandwich_description)
        private val illustrationMenu: ImageView = itemView.findViewById(R.id.sandwich_illustration)
        val detail: ImageView = itemView.findViewById(R.id.sandwich_detail_btn)

        override fun bindView(item: SandwichItem, payloads: List<Any>) {
            typeMenu.text = item.menuItemModel.menuItemName
            descriptionMenu.text = item.menuItemModel.menuDescription
            item.menuItemModel.menuIllustration?.let { illustrationMenu.setImageResource(it) }
            Log.d("BINDING", "Bind")
        }

        override fun unbindView(item: SandwichItem) {
            typeMenu.text = null
            descriptionMenu.text = null
            Log.d("BINDING", "unBind")
        }
    }
}