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
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.order.OrdersSectionAdapter
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.order.OrdersSections
import kotlinx.android.synthetic.main.fragment_orders.*
import java.util.ArrayList

class OrdersFragment : BaseFragment() {
    companion object {
        fun getInstance() = OrdersFragment()
    }
    override val title: String
        get() = "Panier"

    private lateinit var orderRecyclerView: RecyclerView
    private lateinit var ordersSectionAdapter: OrdersSectionAdapter

//    private val itemAdapter = ItemAdapter<OrdersItem>()
//    private val fastAdapter = FastAdapter.with(itemAdapter)

    private var listener: FragmentListener? = null

    private var sectionOrdersArrayList = ArrayList<OrdersSections>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_orders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        orderRecyclerView = view.findViewById(R.id.order_recyclerview)
        ordersSectionAdapter = OrdersSectionAdapter(sectionOrdersArrayList)
        ordersSectionAdapter.notifyDataSetChanged()


        payment_checkout_btn.setOnClickListener {
            listener?.openCheckoutFragment()
        }
    }

    override fun onStart() {
        super.onStart()

        val listOfDates = listOf("23/07", "24/07", "25/07")
        listOfDates.forEach {
            mockInitData(it, listMockOrderModel())
        }

        orderRecyclerView.apply {
            this.adapter = ordersSectionAdapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

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

    private fun hashMapOfData(sectionName: String, sectionItems: List<OrdersModel>): HashMap<String, List<OrdersModel>> {
        return hashMapOf(
            sectionName to sectionItems
        )
    }

    private fun mockInitData(sectionName: String, listOfModel: List<OrdersModel>) {
        val sections = hashMapOfData(sectionName, listOfModel)
        sections.forEach {
            sectionOrdersArrayList.add(OrdersSections(it.key, it.value))
        }
    }

    private fun listMockOrderModel(): List<OrdersModel> {
        val listOfOrders = ArrayList<OrdersModel>()
        listOfOrders.add(OrdersModel(null, "Boulet Frite", 4, 0))
        listOfOrders.add(OrdersModel(null, "Soupe d'oignon", 4, 1))
        listOfOrders.add(OrdersModel(null, "Viande de Cathy", 4, 99))
        return listOfOrders
    }

//    private fun mockData(){
//        listMockOrderModel().forEach {
//            itemAdapter.add(OrdersItem(it))
//        }
//        fastAdapter.notifyAdapterDataSetChanged()
//    }
}