package be.technifutur.devmob9.projet_cantinapp_android.view.adapter.order

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

class OrdersChildAdapter(private val ordersItemList: List<OrdersModel>): RecyclerView.Adapter<OrdersChildAdapter.OrderChildViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderChildViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_order_item, parent, false)
        return OrderChildViewHolder(view)
    }

    override fun getItemCount(): Int = ordersItemList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: OrderChildViewHolder, position: Int) {
        val orderSectionsModel = ordersItemList[position]

        holder.orderIllustration.setImageResource(R.drawable.menu_illustration)
        holder.orderName.text = orderSectionsModel.orderName
        holder.orderPrice.text = "${orderSectionsModel.orderPrice},00 €"
        holder.orderNumberOfItems.setText(orderSectionsModel.orderNumberOfItems.toString())

    }

    inner class OrderChildViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val orderIllustration: ImageView = v.findViewById(R.id.order_illustration)
        val orderName: TextView = v.findViewById(R.id.order_name)
        val orderPrice: TextView = v.findViewById(R.id.order_price)
        val orderNumberOfItems: EditText = v.findViewById(R.id.order_number)
    }
}