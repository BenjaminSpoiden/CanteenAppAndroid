package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import be.technifutur.devmob9.projet_cantinapp_android.GlideApp
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.FragmentListener
import be.technifutur.devmob9.projet_cantinapp_android.model.data.Sandwich
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.PicturesManager
import com.google.android.material.card.MaterialCardView
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class SandwichItemBinder(context: Context): ItemBinder<Sandwich, SandwichItemBinder.SandwichViewHolder>() {

    companion object {
        var onItemClick: ((SandwichViewHolder) -> Unit)? = null
    }

    private val fragmentListener = context as FragmentListener?

    override fun createViewHolder(parent: ViewGroup?): SandwichViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.recyclerview_sandwich_item, parent, false)
        return SandwichViewHolder(view)
    }

    override fun canBindData(item: Any?): Boolean {
        return item is Sandwich
    }

    @SuppressLint("SetTextI18n")
    override fun bindViewHolder(holder: SandwichViewHolder?, item: Sandwich?) {
        holder?.sandwichCard?.setCheckedIconResource(R.drawable.ic_check)
        holder?.sandwichName?.text = item?.name
        holder?.sandwichDesc?.text = item?.description
        holder?.sandwichPrice?.text = "${item?.price},00 €"
        GlideApp.with(holder?.itemView!!)
            .load(PicturesManager().fetchDishesPictures(item?.picture_path))
            .error(R.drawable.no_picture_found)
            .centerCrop()
            .into(holder.sandwichIllustration)
    }

    override fun initViewHolder(holder: SandwichViewHolder?) {
        super.initViewHolder(holder)
        holder?.sandwichCard?.setOnClickListener {
            onItemClick?.invoke(holder)
        }

        holder?.sandwichDetail?.setOnClickListener {
            fragmentListener?.openMenuDetail(holder.item)
        }
    }
    inner class SandwichViewHolder(v: View): ItemViewHolder<Sandwich>(v) {
        val sandwichName: TextView = v.findViewById(R.id.type_sandwich)
        val sandwichDesc: TextView = v.findViewById(R.id.sandwich_description)
        val sandwichIllustration: ImageView = v.findViewById(R.id.sandwich_illustration)
        val sandwichCard: MaterialCardView = v.findViewById(R.id.sandwich_menu_bg)
        val sandwichPrice: TextView = v.findViewById(R.id.sandwich_price)
        val sandwichDetail: ImageView = v.findViewById(R.id.sandwich_detail_btn)
    }
}