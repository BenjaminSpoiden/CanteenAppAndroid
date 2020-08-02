package be.technifutur.devmob9.projet_cantinapp_android.view.adapter.order

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import kotlinx.android.synthetic.main.recyclerview_order_item.view.*

class OrdersSectionAdapter(private val ordersSectionsList: List<OrdersSections>): RecyclerView.Adapter<OrdersSectionAdapter.OrderSectionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderSectionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.section__order_row, parent, false)
        return OrderSectionViewHolder(view)
    }

    override fun getItemCount(): Int = ordersSectionsList.size

    override fun onBindViewHolder(holder: OrderSectionViewHolder, position: Int) {
        val ordersSection = ordersSectionsList[position]
        val items = ordersSection.sectionItems

        val childRecyclerAdapter = OrdersChildAdapter(items)

        holder.sectionName.text = ordersSection.sectionName

        holder.sectionRecyclerView.apply {
            this.adapter = childRecyclerAdapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    inner class OrderSectionViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val sectionName: TextView = v.findViewById(R.id.header_text)
        val sectionRecyclerView: RecyclerView = v.findViewById(R.id.child_order_recyclerview)
    }
}