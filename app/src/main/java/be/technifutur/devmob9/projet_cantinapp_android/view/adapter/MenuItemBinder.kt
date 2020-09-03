package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.GlideApp
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.FragmentListener
import be.technifutur.devmob9.projet_cantinapp_android.model.data.DishesType
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.PicturesManager
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.FIREBASE_TAG
import com.google.android.material.card.MaterialCardView
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class MenuItemBinder(context: Context): ItemBinder<DishesType, MenuItemBinder.MenuItemViewHolder>(){

    companion object {
        var onItemClick: ((MenuItemViewHolder) -> Unit)? = null
    }

    private val fragmentListener = context as FragmentListener?

    override fun createViewHolder(parent: ViewGroup?): MenuItemViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.recyclerview_menu_item, parent, false)
        return MenuItemViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun bindViewHolder(holder: MenuItemViewHolder?, item: DishesType) {
        holder?.menuCard?.setCheckedIconResource(R.drawable.ic_check)
        when(item) {
            is DishesType.Starters -> {
                holder?.menuName?.text = item.name
                holder?.descriptionMenu?.text = item.description
                holder?.menuPrice?.text = "${item.price},00 €"
                GlideApp.with(holder?.itemView!!)
                    .load(PicturesManager().fetchDishesPictures(item.picture_path))
                    .error(R.drawable.no_picture_found)
                    .centerCrop()
                    .into(holder.menuIllustration)
                holder.progressBar.visibility = View.GONE
            }
            is DishesType.MainCourses -> {
                holder?.menuName?.text = item.name
                holder?.descriptionMenu?.text = item.description
                holder?.menuPrice?.text = "${item.price},00 €"
                GlideApp.with(holder?.itemView!!)
                    .load(PicturesManager().fetchDishesPictures(item.picture_path))
                    .error(R.drawable.no_picture_found)
                    .centerCrop()
                    .into(holder.menuIllustration)
                holder.progressBar.visibility = View.GONE
            }
            is DishesType.Desserts -> {
                holder?.menuName?.text = item.name
                holder?.descriptionMenu?.text = item.description
                holder?.menuPrice?.text = "${item.price},00 €"
                GlideApp.with(holder?.itemView!!)
                    .load(PicturesManager().fetchDishesPictures(item.picture_path))
                    .error(R.drawable.no_picture_found)
                    .centerCrop()
                    .into(holder.menuIllustration)
                holder.progressBar.visibility = View.GONE
            }
        }
    }

    override fun canBindData(item: Any?): Boolean {
        return item is DishesType
    }

    override fun initViewHolder(holder: MenuItemViewHolder?) {
        super.initViewHolder(holder)
        holder?.menuCard?.setOnClickListener {
            onItemClick?.invoke(holder)
        }

        holder?.detailButton?.setOnClickListener {
            fragmentListener?.openMenuDetail(holder.item)
        }
    }

    inner class MenuItemViewHolder(v: View): ItemViewHolder<DishesType>(v){
        val menuName: TextView = v.findViewById(R.id.type_menu)
        val descriptionMenu: TextView = v.findViewById(R.id.menu_description)
        val detailButton: ImageView = v.findViewById(R.id.menu_detail_button)
        val menuCard: MaterialCardView = v.findViewById(R.id.menu_bg)
        val menuPrice: TextView = v.findViewById(R.id.menu_price)
        val menuIllustration: ImageView = v.findViewById(R.id.menu_illustration)
        val progressBar: ProgressBar = v.findViewById(R.id.loader)
    }
}