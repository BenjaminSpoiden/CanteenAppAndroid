package be.technifutur.devmob9.projet_cantinapp_android.view.adapter.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.MenuItemModel
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.GenericAdapter

class SandwichViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),
    GenericAdapter.Binder<MenuItemModel> {

    private val typeMenu: TextView = itemView.findViewById(R.id.type_sandwich)
    private val descriptionMenu: TextView = itemView.findViewById(R.id.sandwich_description)
    private val illustrationMenu: ImageView = itemView.findViewById(R.id.sandwich_illustration)

    override fun bind(data: MenuItemModel) {
        typeMenu.text = data.menuItemName
        descriptionMenu.text = data.menuDescription
        data.menuIllustration?.let { illustrationMenu.setImageResource(it) }
    }
}