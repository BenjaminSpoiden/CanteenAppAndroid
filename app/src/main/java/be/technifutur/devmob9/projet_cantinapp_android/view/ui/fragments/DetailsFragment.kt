package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
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
        allergiesRecyclerView.apply {
            this.adapter = fastAdapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        allergiesRecyclerView.apply {
            this.adapter = null
            this.layoutManager = null
        }
    }

    private fun snackBars(view: View, text: String){
        AllergiesSnackBar.make(view, R.drawable.croissant_illustration, text).show()
    }
}