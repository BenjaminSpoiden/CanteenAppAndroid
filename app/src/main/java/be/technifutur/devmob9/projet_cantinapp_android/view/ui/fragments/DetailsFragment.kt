package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.animation.LayoutTransition
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.AllergiesModel
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.listOfAllergies
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.AllergiesItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment: BaseFragment() {

    companion object {
        fun getInstance() = DetailsFragment()
    }

    private lateinit var allergiesRecyclerView: RecyclerView
    private val itemAdapter = ItemAdapter<AllergiesItem>()
    private val fastAdapter = FastAdapter.with(itemAdapter)

    private var allergiesInfoBoxToggle: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callback.fragmentTitle("Details")
        allergiesRecyclerView = view.findViewById(R.id.allergies_recyclerview)
        allergiesRecyclerView.apply {
            this.adapter = fastAdapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        setupMockAllergies()

        fastAdapter.onClickListener = { _, _, item, position ->
            allergies_image.setImageResource(item.allergiesModel.allergyIllustration)
            container.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)

            togglingVisibility()

            allergies_detail.setOnClickListener {
                Toast.makeText(context, "$position", Toast.LENGTH_SHORT).show()
            }

            true
        }
    }

    private fun togglingVisibility() {
        if(allergiesInfoBoxToggle) {
            allergy_infobox_container.visibility = View.VISIBLE
            allergiesInfoBoxToggle = false
        }else {
            allergy_infobox_container.visibility = View.GONE
            allergiesInfoBoxToggle = true
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
            itemAdapter.add(AllergiesItem(it))
        }
    }
}