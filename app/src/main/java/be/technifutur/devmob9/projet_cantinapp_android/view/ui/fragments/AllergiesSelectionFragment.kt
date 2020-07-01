package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.listOfAllergies
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.AllergiesSelectionAdapter
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class AllergiesSelectionFragment : BaseFragment() {

    companion object {
        fun getInstance() = AllergiesSelectionFragment()
    }

    override val title: String
        get() = "Mes Allergies"

    private lateinit var recyclerView: RecyclerView
    private val itemAdapter = ItemAdapter<AllergiesSelectionAdapter>()
    private val fastAdapter = FastAdapter.with(itemAdapter)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_allergies_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.allergies_selection)
        recyclerView.apply {
            this.adapter = fastAdapter
            this.layoutManager = GridLayoutManager(context, 2)
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        loadData()
    }

    private fun loadData() {
        listOfAllergies.forEach {
            itemAdapter.add(AllergiesSelectionAdapter(it))
        }
        fastAdapter.notifyAdapterDataSetChanged()
    }

    override fun onDestroy() {
        recyclerView.apply {
            this.adapter = null
            this.layoutManager = null
        }
        super.onDestroy()
    }
}