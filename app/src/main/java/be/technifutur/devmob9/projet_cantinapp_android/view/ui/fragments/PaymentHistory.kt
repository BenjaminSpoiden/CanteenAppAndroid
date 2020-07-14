package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.PaymentHistoriqueModel
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.PaymentHistoriqueItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class PaymentHistory : BaseFragment() {

    companion object {
        fun getInstance() = PaymentHistory()
    }

    override val title: String
        get() = "Historique de Payment"

    private lateinit var recyclerView: RecyclerView
    private val itemAdapter = ItemAdapter<PaymentHistoriqueItem>()
    private val fastAdapter = FastAdapter.with(itemAdapter)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_payment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.payment_historique_recycler_view)
        recyclerView.apply {
            this.adapter = fastAdapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            this.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

    override fun onStart() {
        super.onStart()
        mockData()
    }

    private fun mockData(){
        for (i in 0..20){
            itemAdapter.add(PaymentHistoriqueItem(PaymentHistoriqueModel("8 Septembre", "Boulet Frite, Salade, Dessert", "VISA", "8,40€")))
        }
        fastAdapter.notifyAdapterDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        recyclerView.adapter = null
        recyclerView.layoutManager = null
    }
}
