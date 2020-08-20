package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.OrdersModel
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.TYPE_HEADER
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.TYPE_ITEM
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.recyclerview_order_item.view.*
import java.lang.IllegalArgumentException

class OrdersItemAdapter(private val ordersItemList: MutableList<OrdersModel>): RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        if(getItemViewType(position) == TYPE_HEADER) {
            ordersItemList[position].headerName?.let {
                (holder as OrdersHeaderViewHolder).bind(it, position)
            }
        } else if (getItemViewType(position) == TYPE_ITEM) {
            (holder as OrdersItemViewHolder).bind(ordersItemList, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            TYPE_HEADER -> {
                val headerView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_header, parent, false)
                OrdersHeaderViewHolder(headerView)
            }
            TYPE_ITEM -> {
                val itemsView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_order_item, parent, false)
                OrdersItemViewHolder(itemsView).apply {
                    itemsView.order_delete_item.setOnClickListener {
                        Log.d("Order", "Deleted item at: ${this.adapterPosition} for ${ordersItemList[this.adapterPosition]}")
                        ordersItemList.removeAt(this.adapterPosition)
                        this@OrdersItemAdapter.notifyDataSetChanged()
                    }
                }
            }
            else -> throw IllegalArgumentException("Invalid View Type")
        }
    }

    override fun getItemCount(): Int = ordersItemList.size

    override fun getItemViewType(position: Int): Int {
        return if (ordersItemList[position].isHeader){
            TYPE_HEADER
        } else {
            TYPE_ITEM
        }
    }

    inner class OrdersHeaderViewHolder(itemView: View): BaseViewHolder<String>(itemView) {
        private val sectionName: TextView = itemView.findViewById(R.id.header_menu_text)
        override fun bind(holder: String, position: Int) {
            sectionName.text = holder
        }
    }

    inner class OrdersItemViewHolder(itemView: View): BaseViewHolder<List<OrdersModel>>(itemView) {
        private val orderIllustration: ImageView = itemView.findViewById(R.id.order_illustration)
        private val orderName: TextView = itemView.findViewById(R.id.order_name)
        private val orderPrice: TextView = itemView.findViewById(R.id.order_price)
        private val orderNumberOfItems: EditText = itemView.findViewById(R.id.order_number)

        @SuppressLint("SetTextI18n")
        override fun bind(holder: List<OrdersModel>, position: Int) {
            val orderSectionsModel = ordersItemList[position]

            orderIllustration.setImageResource(R.drawable.menu_illustration)
            orderName.text = orderSectionsModel.orderName
            orderPrice.text = "${orderSectionsModel.orderPrice},00 €"
            orderNumberOfItems.setText(orderSectionsModel.orderNumberOfItems.toString())
        }
    }
}