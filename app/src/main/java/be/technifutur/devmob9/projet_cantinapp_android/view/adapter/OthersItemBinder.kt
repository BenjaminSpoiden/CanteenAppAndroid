package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import be.technifutur.devmob9.projet_cantinapp_android.GlideApp
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.FragmentListener
import be.technifutur.devmob9.projet_cantinapp_android.model.data.OthersType
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.PicturesManager
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class OthersItemBinder(context: Context): ItemBinder<OthersType, OthersItemBinder.OthersViewHolder>() {

    private val fragmentListener = context as FragmentListener?

    override fun createViewHolder(parent: ViewGroup?): OthersViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.recyclerview_others_item, parent, false)
        return OthersViewHolder(view)
    }

    override fun canBindData(item: Any?): Boolean {
        return item is OthersType
    }

    override fun initViewHolder(holder: OthersViewHolder?) {
        super.initViewHolder(holder)

    }

    override fun bindViewHolder(holder: OthersViewHolder?, item: OthersType) {
        when(item) {
            is OthersType.Croissants -> {
                holder?.othersName?.text = item.name
                holder?.othersDescription?.text = item.description
                GlideApp.with(holder?.itemView!!)
                    .load(PicturesManager().fetchDishesPictures(item.picture_path))
                    .placeholder(R.drawable.menu_illustration)
                    .error(R.drawable.no_picture_found)
                    .centerCrop()
                    .into(holder.othersIllustration)
            }
            is OthersType.Dressings -> {
                holder?.othersName?.text = item.name
                holder?.othersDescription?.text = item.description
                GlideApp.with(holder?.itemView!!)
                    .load(PicturesManager().fetchDishesPictures(item.picture_path))
                    .error(R.drawable.no_picture_found)
                    .centerCrop()
                    .into(holder.othersIllustration)
            }
            is OthersType.Drinks -> {
            holder?.othersName?.text = item.name
            holder?.othersDescription?.text = item.description
            GlideApp.with(holder?.itemView!!)
                .load(PicturesManager().fetchDishesPictures(item.picture_path))
                .error(R.drawable.no_picture_found)
                .centerCrop()
                .into(holder.othersIllustration)
            }
            is OthersType.Fruits -> {
                holder?.othersName?.text = item.name
                holder?.othersDescription?.text = item.description
                GlideApp.with(holder?.itemView!!)
                    .load(PicturesManager().fetchDishesPictures(item.picture_path))
                    .error(R.drawable.no_picture_found)
                    .centerCrop()
                    .into(holder.othersIllustration)
            }
            is OthersType.Yoghurts -> {
                holder?.othersName?.text = item.name
                holder?.othersDescription?.text = item.description
                GlideApp.with(holder?.itemView!!)
                    .load(PicturesManager().fetchDishesPictures(item.picture_path))
                    .error(R.drawable.no_picture_found)
                    .centerCrop()
                    .into(holder.othersIllustration)
            }
        }
    }

    inner class OthersViewHolder(v: View): ItemViewHolder<OthersType>(v) {
        val othersName: TextView = v.findViewById(R.id.type_croissant)
        val othersDescription: TextView = v.findViewById(R.id.croissant_description)
        val othersIllustration: ImageView = v.findViewById(R.id.croissant_illustration)
    }
}