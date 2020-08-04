package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.FragmentListener
import be.technifutur.devmob9.projet_cantinapp_android.model.data.OrdersModel
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.OrdersItemAdapter
import kotlinx.android.synthetic.main.fragment_orders.*

class OrdersFragment : BaseFragment() {
    companion object {
        fun getInstance() = OrdersFragment()
    }
    override val title: String
        get() = "Panier"

    private lateinit var orderRecyclerView: RecyclerView
    private lateinit var ordersItemAdapter: OrdersItemAdapter

    private var listener: FragmentListener? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_orders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        orderRecyclerView = view.findViewById(R.id.order_recyclerview)

        payment_checkout_btn.setOnClickListener {
            listener?.openCheckoutFragment()
        }

        val checkedDrawable = requireContext().resources.getDrawable(R.drawable.ic_check, resources.newTheme())
        val uncheckedDrawable = requireContext().resources.getDrawable(R.drawable.ic_unchecked, resources.newTheme())


        a_emporte_cb.setOnCheckedChangeListener { buttonView, isChecked ->
            if (a_emporte_cb.isChecked) {
                a_emporte_cb.setCompoundDrawablesWithIntrinsicBounds(checkedDrawable, null, null, null)
                a_emporte_cb.setTextColor(Color.WHITE)
            }else {
                a_emporte_cb.setCompoundDrawablesWithIntrinsicBounds(uncheckedDrawable, null, null, null)
                a_emporte_cb.setTextColor(resources.getColor(R.color.lightGreen, resources.newTheme()))
            }
        }
    }

    override fun onStart() {
        super.onStart()
        ordersItemAdapter = OrdersItemAdapter(mockInitData())
        ordersItemAdapter.notifyDataSetChanged()
        orderRecyclerView.apply {
            this.adapter = ordersItemAdapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun mockInitData() = listOf(
        OrdersModel(isHeader = true, headerName = "23/07"),
        OrdersModel(null, "Boulet Frite", 4, 0),
        OrdersModel(null, "Soupe d'oignon", 6, 8),
        OrdersModel(null, "Viande de Cathy", 4, 99),
        OrdersModel(isHeader = true, headerName = "24/07"),
        OrdersModel(null, "Boulet Frite", 4, 0),
        OrdersModel(null, "Soupe d'oignon", 6, 8),
        OrdersModel(null, "Viande de Cathy", 4, 99),
        OrdersModel(isHeader = true, headerName = "25/07"),
        OrdersModel(null, "Boulet Frite", 4, 0),
        OrdersModel(null, "Soupe d'oignon", 6, 8),
        OrdersModel(null, "Viande de Cathy", 4, 99)
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onStop() {
        super.onStop()
        orderRecyclerView.apply {
            this.adapter = null
            this.layoutManager = null
        }
    }
}