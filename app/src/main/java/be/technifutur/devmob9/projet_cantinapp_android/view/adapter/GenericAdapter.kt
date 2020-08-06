package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.items.AbstractItem


abstract class GenericAdapter<T>(private var genericItemList: List<T>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    abstract fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder

    abstract fun getLayoutID(position: Int, data: T): Int

    fun updateItems(items: MutableList<T>) {
        genericItemList = items
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        getViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false), viewType)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Binder<T>).bind(genericItemList[position])
    }

    override fun getItemCount(): Int {
        return genericItemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutID(position, genericItemList[position])
    }

    internal interface Binder<T> {
        fun bind(data: T)
    }
}