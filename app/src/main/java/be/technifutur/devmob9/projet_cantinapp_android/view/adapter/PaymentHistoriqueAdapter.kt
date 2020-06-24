package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.view.View
import android.widget.TextView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.PaymentHistoriqueModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem


class PaymentHistoriqueAdapter(val paymentHistoriqueModel: PaymentHistoriqueModel): AbstractItem<PaymentHistoriqueAdapter.PaymentHistoriqueViewHolder>(){

    override val layoutRes: Int
        get() = R.layout.recyclerview_payment_historique_item
    override val type: Int
        get() = R.id.payment_historique_item_id


    override fun getViewHolder(v: View): PaymentHistoriqueViewHolder = PaymentHistoriqueViewHolder(v)


    inner class PaymentHistoriqueViewHolder(itemView: View): FastAdapter.ViewHolder<PaymentHistoriqueAdapter>(itemView) {

        val day: TextView = itemView.findViewById(R.id.day)
        val orderDetail: TextView = itemView.findViewById(R.id.commande_detail)
        val typeOfPayment: TextView = itemView.findViewById(R.id.type_of_payment)
        val price: TextView = itemView.findViewById(R.id.price)

        override fun bindView(item: PaymentHistoriqueAdapter, payloads: List<Any>) {
            day.text = item.paymentHistoriqueModel.day
            orderDetail.text = item.paymentHistoriqueModel.orderDetail
            typeOfPayment.text = item.paymentHistoriqueModel.typeOfPayment
            price.text = item.paymentHistoriqueModel.price
        }

        override fun unbindView(item: PaymentHistoriqueAdapter) {
            day.text = null
            orderDetail.text = null
            typeOfPayment.text = null
            price.text = null
        }
    }
}