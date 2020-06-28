package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.AllergiesModel
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.AllergiesDetailAdapter
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.AllergiesItemAdapter
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class AllergiesInfoFragment : BaseFragment() {

    companion object {
        fun getInstance() = AllergiesInfoFragment()
    }

    override val title: String
        get() = "Allergies"

    private lateinit var allergiesRecyclerView: RecyclerView
    private val itemAdapter = ItemAdapter<AllergiesDetailAdapter>()
    private val fastAdapter = FastAdapter.with(itemAdapter)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_allergies_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        allergiesRecyclerView = view.findViewById(R.id.allergie_info_recyclerview)

        allergiesRecyclerView.apply {
            this.adapter = fastAdapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
        setupMockAllergies()
    }


    private fun setupMockAllergies() {
        itemAdapter.add(AllergiesDetailAdapter(AllergiesModel(R.drawable.ic_gluten)))
        itemAdapter.add(AllergiesDetailAdapter(AllergiesModel(R.drawable.ic_celery)))
        itemAdapter.add(AllergiesDetailAdapter(AllergiesModel(R.drawable.ic_almond)))
        itemAdapter.add(AllergiesDetailAdapter(AllergiesModel(R.drawable.ic_crustaceans)))
        itemAdapter.add(AllergiesDetailAdapter(AllergiesModel(R.drawable.ic_egg)))
        itemAdapter.add(AllergiesDetailAdapter(AllergiesModel(R.drawable.ic_fish)))
        itemAdapter.add(AllergiesDetailAdapter(AllergiesModel(R.drawable.ic_lupin)))
        itemAdapter.add(AllergiesDetailAdapter(AllergiesModel(R.drawable.ic_milk)))
        itemAdapter.add(AllergiesDetailAdapter(AllergiesModel(R.drawable.ic_mollusc)))
        itemAdapter.add(AllergiesDetailAdapter(AllergiesModel(R.drawable.ic_mustard)))
        itemAdapter.add(AllergiesDetailAdapter(AllergiesModel(R.drawable.ic_sesame)))
        itemAdapter.add(AllergiesDetailAdapter(AllergiesModel(R.drawable.ic_soybean)))
        itemAdapter.add(AllergiesDetailAdapter(AllergiesModel(R.drawable.ic_sulfide)))
    }

    override fun onDestroy() {
        super.onDestroy()
        allergiesRecyclerView.apply {
            this.adapter = null
            this.layoutManager = null
        }
    }
}