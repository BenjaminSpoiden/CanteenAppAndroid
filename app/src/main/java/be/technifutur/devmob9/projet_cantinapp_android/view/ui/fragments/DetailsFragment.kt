package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.AllergiesModel
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.AllergiesItemAdapter
import be.technifutur.devmob9.projet_cantinapp_android.view.ui.custom.AllergiesSnackBar
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class DetailsFragment: BaseFragment() {

    companion object {
        fun getInstance() = DetailsFragment()
    }

    override val title: String
        get() = "Details"

    private lateinit var allergiesRecyclerView: RecyclerView
    private val itemAdapter = ItemAdapter<AllergiesItemAdapter>()
    private val fastAdapter = FastAdapter.with(itemAdapter)

    private lateinit var constraintLayout: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        allergiesRecyclerView = view.findViewById(R.id.allergies_recyclerview)
        constraintLayout = view.findViewById(R.id.allergies_layout)

        allergiesRecyclerView.apply {
            this.adapter = fastAdapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        setupMockAllergies()

        fastAdapter.onClickListener = { v, _, item, position ->
            if (v != null) {
                AllergiesSnackBar.make(v, item.allergiesModel.allergyIllustration, "Contient cet allergène", View.OnClickListener {
                    Toast.makeText(context, "You clicked on detail at position ${position + 1}", Toast.LENGTH_SHORT).show()
                }).show()
            }
            true
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        allergiesRecyclerView.apply {
            this.adapter = null
            this.layoutManager = null
        }
    }
    private fun setupMockAllergies() {
        itemAdapter.add(AllergiesItemAdapter(AllergiesModel(R.drawable.ic_gluten)))
        itemAdapter.add(AllergiesItemAdapter(AllergiesModel(R.drawable.ic_celery)))
        itemAdapter.add(AllergiesItemAdapter(AllergiesModel(R.drawable.ic_almond)))
        itemAdapter.add(AllergiesItemAdapter(AllergiesModel(R.drawable.ic_crustaceans)))
        itemAdapter.add(AllergiesItemAdapter(AllergiesModel(R.drawable.ic_egg)))
        itemAdapter.add(AllergiesItemAdapter(AllergiesModel(R.drawable.ic_fish)))
        itemAdapter.add(AllergiesItemAdapter(AllergiesModel(R.drawable.ic_lupin)))
        itemAdapter.add(AllergiesItemAdapter(AllergiesModel(R.drawable.ic_milk)))
        itemAdapter.add(AllergiesItemAdapter(AllergiesModel(R.drawable.ic_mollusc)))
        itemAdapter.add(AllergiesItemAdapter(AllergiesModel(R.drawable.ic_mustard)))
        itemAdapter.add(AllergiesItemAdapter(AllergiesModel(R.drawable.ic_sesame)))
        itemAdapter.add(AllergiesItemAdapter(AllergiesModel(R.drawable.ic_soybean)))
        itemAdapter.add(AllergiesItemAdapter(AllergiesModel(R.drawable.ic_sulfide)))
    }
}