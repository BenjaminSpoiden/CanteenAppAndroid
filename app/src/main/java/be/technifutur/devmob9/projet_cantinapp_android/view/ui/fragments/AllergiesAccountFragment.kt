package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.listOfAllergies
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.AllergyTierHighItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class AllergiesAccountFragment : BaseFragment() {

    companion object {
        fun getInstance() = AllergiesAccountFragment()
    }

    override val title: String
        get() = "Mes Allergies"

    private lateinit var highTierRecyclerView: RecyclerView


//    private val itemAdapter = ItemAdapter<AllergiesSelectionAdapter>()
//    private val fastAdapter = FastAdapter.with(itemAdapter)

    private val allergyHighTierAdapter = ItemAdapter<AllergyTierHighItem>()
    private val allergyHighTierFastAdapter = FastAdapter.with(allergyHighTierAdapter)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account_allergies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        highTierRecyclerView = view.findViewById(R.id.high_tier_recyclerView)

        highTierRecyclerView.apply {
            this.adapter = allergyHighTierFastAdapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        loadData()
    }

    private fun loadData() {
        listOfAllergies.forEach {
            allergyHighTierAdapter.add(AllergyTierHighItem(it))
        }
        allergyHighTierFastAdapter.notifyAdapterDataSetChanged()
    }

    override fun onDestroy() {
        highTierRecyclerView.apply {
            this.adapter = null
            this.layoutManager = null
        }
        super.onDestroy()
    }
}