package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.FragmentListener
import be.technifutur.devmob9.projet_cantinapp_android.model.data.OrdersModel
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.OrdersItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import kotlinx.android.synthetic.main.fragment_orders.*

class OrdersFragment : BaseFragment() {

    companion object {
        fun getInstance() = OrdersFragment()
    }
    override val title: String
        get() = "Panier"

    private lateinit var orderRecyclerView: RecyclerView
    private val itemAdapter = ItemAdapter<OrdersItem>()
    private val fastAdapter = FastAdapter.with(itemAdapter)
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
    }

    override fun onStart() {
        super.onStart()
        orderRecyclerView.apply {
            this.adapter = fastAdapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
        mockData()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentListener){
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

    private fun mockData(){
        for(i in 0..10) {
            itemAdapter.add(OrdersItem(OrdersModel(null, "Boulet Frite", 4, "2000 Kcal", 0)))
        }
        fastAdapter.notifyAdapterDataSetChanged()
    }
}