package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.OrdersModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem

class OrdersItem(val ordersModel: OrdersModel): AbstractItem<OrdersItem.OrdersViewHolder>(){

    override val layoutRes: Int
        get() = R.layout.recyclerview_order_item
    override val type: Int
        get() = R.id.order_items_id

    override fun getViewHolder(v: View): OrdersViewHolder = OrdersViewHolder(v)


    inner class OrdersViewHolder(v: View): FastAdapter.ViewHolder<OrdersItem>(v) {
        private val orderIllustration: ImageView = v.findViewById(R.id.order_illustration)
        private val orderName: TextView = v.findViewById(R.id.order_name)
        private val orderPrice: TextView = v.findViewById(R.id.order_price)
        private val orderKCal: TextView = v.findViewById(R.id.order_kcal)
        private val orderNumberOfItems: TextView = v.findViewById(R.id.order_number)


        @SuppressLint("SetTextI18n")
        override fun bindView(item: OrdersItem, payloads: List<Any>) {
            orderIllustration.setImageResource(R.drawable.menu_illustration)
            orderName.text = item.ordersModel.orderName
            orderPrice.text = "${item.ordersModel.orderPrice},00 €"
            orderKCal.text = item.ordersModel.orderKCal
            orderNumberOfItems.text = item.ordersModel.orderNumberOfItems.toString()
        }

        override fun unbindView(item: OrdersItem) {
            orderName.text = null
            orderPrice.text = null
            orderKCal.text = null
            orderNumberOfItems.text = null
        }
    }
}