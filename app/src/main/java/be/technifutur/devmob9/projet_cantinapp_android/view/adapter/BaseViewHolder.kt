package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(holder: T, position: Int)
    abstract fun unbind(holder: T, position: Int)
}