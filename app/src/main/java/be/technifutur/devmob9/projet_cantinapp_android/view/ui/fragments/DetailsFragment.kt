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
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.listOfAllergies
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.AllergiesItem
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
    private val itemAdapter = ItemAdapter<AllergiesItem>()
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
        listOfAllergies.forEach {
            itemAdapter.add(AllergiesItem(AllergiesModel(it)))
        }
    }
}