package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.listOfAllergies
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.AllergiesDetailItem
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.AllergiesDetailItemLeftLayout
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.AllergiesDetailItemRightLayout
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class AllergiesInfoFragment : BaseFragment() {

    companion object {
        fun getInstance() = AllergiesInfoFragment()
    }

    private lateinit var allergiesRecyclerView: RecyclerView
    private val itemAdapter = ItemAdapter<AllergiesDetailItem>()
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
        callback.fragmentTitle("Info Allergènes")
        allergiesRecyclerView = view.findViewById(R.id.allergie_info_recyclerview)

        allergiesRecyclerView.apply {
            this.adapter = fastAdapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
        setupMockAllergies()

//        allergiesRecyclerView.layoutManager?.scrollToPosition(6)
    }


    private fun setupMockAllergies() {
        listOfAllergies.forEach {
            if ((listOfAllergies.indexOf(it)%2).equals(0)) {
                itemAdapter.add(AllergiesDetailItemLeftLayout(it))
            } else {
                itemAdapter.add(AllergiesDetailItemRightLayout(it))
            }
        }
        fastAdapter.notifyAdapterDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        allergiesRecyclerView.apply {
            this.adapter = null
            this.layoutManager = null
        }
        observeMenuPosition()
    }
}