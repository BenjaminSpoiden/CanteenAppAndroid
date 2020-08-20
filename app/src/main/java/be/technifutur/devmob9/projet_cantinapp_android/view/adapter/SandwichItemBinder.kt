package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import be.technifutur.devmob9.projet_cantinapp_android.GlideApp
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.Sandwich
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.PicturesManager
import com.google.android.material.card.MaterialCardView
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class SandwichItemBinder(private val onItemClick: (SandwichViewHolder) -> Unit): ItemBinder<Sandwich, SandwichItemBinder.SandwichViewHolder>() {

    override fun createViewHolder(parent: ViewGroup?): SandwichViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.recyclerview_sandwich_item, parent, false)
        return SandwichViewHolder(view)
    }

    override fun canBindData(item: Any?): Boolean {
        return item is Sandwich
    }

    override fun bindViewHolder(holder: SandwichViewHolder?, item: Sandwich?) {
        holder?.sandwichName?.text = item?.name
        holder?.sandwichDesc?.text = item?.description
        GlideApp.with(holder?.itemView!!)
            .load(PicturesManager().fetchDishesPictures(item?.picture_path))
            .error(R.drawable.no_picture_found)
            .centerCrop()
            .into(holder.sandwichIllustration)
    }

    override fun initViewHolder(holder: SandwichViewHolder?) {
        super.initViewHolder(holder)
        holder?.sandwichCard?.setOnClickListener {
            onItemClick(holder)
        }

    }
    inner class SandwichViewHolder(v: View): ItemViewHolder<Sandwich>(v) {
        val sandwichName: TextView = v.findViewById(R.id.type_sandwich)
        val sandwichDesc: TextView = v.findViewById(R.id.sandwich_description)
        val sandwichIllustration: ImageView = v.findViewById(R.id.sandwich_illustration)
        val sandwichCard: MaterialCardView = v.findViewById(R.id.sandwich_menu_bg)
    }
}