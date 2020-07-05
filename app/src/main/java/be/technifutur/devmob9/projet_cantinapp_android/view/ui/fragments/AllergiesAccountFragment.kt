package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.listOfAllergies
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.AllergyTierHighAdapter
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import kotlinx.android.synthetic.main.custom_dialog_allergies.*
import kotlinx.android.synthetic.main.fragment_account_allergies.*

class AllergiesAccountFragment : BaseFragment() {

    companion object {
        fun getInstance() = AllergiesAccountFragment()
    }

    override val title: String
        get() = "Mes Allergies"


    private lateinit var highTierRecyclerView: RecyclerView
    private lateinit var mediumTierRecyclerView: RecyclerView
    private lateinit var lowTierRecyclerView: RecyclerView

    private lateinit var allergyDialog: Dialog

//    private val itemAdapter = ItemAdapter<AllergiesSelectionAdapter>()
//    private val fastAdapter = FastAdapter.with(itemAdapter)

    private val allergyHighTierAdapter = ItemAdapter<AllergyTierHighAdapter>()
    private val allergyHighTierFastAdapter = FastAdapter.with(allergyHighTierAdapter)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_account_allergies, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        allergyDialog = context?.let { Dialog(it) }!!

        highTierRecyclerView = view.findViewById(R.id.high_tier_recyclerView)
        mediumTierRecyclerView = view.findViewById(R.id.mid_tier_recyclerview)
        lowTierRecyclerView = view.findViewById(R.id.low_tier_recyclerview)


        highTierRecyclerView.apply {
            this.adapter = allergyHighTierFastAdapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        loadData()

        allergies_selection.setOnClickListener {
            allergyDialog.setContentView(R.layout.custom_dialog_allergies)
            allergyDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            allergyDialog.show()
        }


    }

    private fun loadData() {
        listOfAllergies.forEach {
            allergyHighTierAdapter.add(AllergyTierHighAdapter(it))
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

    private fun hashMapOf(key: String): List<Int>{
        return emptyList()
    }
}