package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.PlaceholderModel
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class PlaceholderBinder: ItemBinder<PlaceholderModel, PlaceholderBinder.PlaceholderViewHolder>(){

    override fun createViewHolder(parent: ViewGroup?): PlaceholderViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.recyclerview_placeholder, parent, false)
        return PlaceholderViewHolder(view)
    }

    override fun canBindData(item: Any?): Boolean {
        return item is PlaceholderModel
    }

    override fun bindViewHolder(holder: PlaceholderViewHolder?, item: PlaceholderModel?) {
        holder?.placeholderText?.text = item?.placeholderText
    }

    inner class PlaceholderViewHolder(v: View): ItemViewHolder<PlaceholderModel>(v){
        val placeholderText: TextView = v.findViewById(R.id.placeholder_text)
    }
}