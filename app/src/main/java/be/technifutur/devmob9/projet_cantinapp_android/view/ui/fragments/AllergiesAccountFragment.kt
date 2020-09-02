package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.hashMapOfAllergies
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.AllergySelectedItem
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.AllergySelectionItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import kotlinx.android.synthetic.main.fragment_account_allergies.*

class AllergiesAccountFragment : BaseFragment() {

    companion object {
        fun getInstance() = AllergiesAccountFragment()
    }

    private lateinit var selectionRecyclerView: RecyclerView
    private lateinit var selectedRecyclerView: RecyclerView

    private val allergySelectionItemAdapter = ItemAdapter<AllergySelectionItem>()
    private val allergySelectionFastAdapter = FastAdapter.with(allergySelectionItemAdapter)

    private val allergySelectedItemAdapter = ItemAdapter<AllergySelectedItem>()
    private val allergySelectedFastAdapter = FastAdapter.with(allergySelectedItemAdapter)

    private val selectedAllergiesHashMap = HashMap<String, Int>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account_allergies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectionRecyclerView = view.findViewById(R.id.recyclerview_allergies_selection)
        selectedRecyclerView = view.findViewById(R.id.recyclerview_allergies_selected)

        isRecyclerViewSelectedEmpty(true)

        selectionRecyclerView.apply {
            this.adapter = allergySelectionFastAdapter
            this.layoutManager = setLinearLayout(LinearLayoutManager.VERTICAL)
            this.layoutManager = setGridLayout(4)
        }
        loadSelectionData()

        selectedRecyclerView.apply {
            this.adapter = allergySelectedFastAdapter
            this.layoutManager = setLinearLayout(LinearLayoutManager.HORIZONTAL)
        }

        allergySelectionFastAdapter.onClickListener = { _, _, item, position ->
            selectedAllergiesHashMap[item.allergySelectionName] = item.allergySelectionIllustration
            allergySelectedItemAdapter.add(AllergySelectedItem(item.allergySelectionName, item.allergySelectionIllustration))
            allergySelectionItemAdapter.remove(position)
            allergySelectionFastAdapter.notifyAdapterDataSetChanged()
            allergySelectedFastAdapter.notifyAdapterDataSetChanged()

            isRecyclerViewSelectedEmpty(false)

            true
        }
    }

    override fun onDestroy() {
        selectionRecyclerView.apply {
            this.adapter = null
            this.layoutManager = null
        }

        selectedRecyclerView.apply {
            this.adapter = null
            this.layoutManager = null
        }
        super.onDestroy()
    }

    private fun loadSelectionData() {
        hashMapOfAllergies.forEach {
            allergySelectionItemAdapter.add(AllergySelectionItem(it.key, it.value))
        }
        allergySelectionFastAdapter.notifyAdapterDataSetChanged()
    }

    private fun isRecyclerViewSelectedEmpty(isEmpty: Boolean){
        if(isEmpty) {
            selectedRecyclerView.visibility = View.GONE
            allergies_selection_placeholder.visibility = View.VISIBLE
        }else {
            selectedRecyclerView.visibility = View.VISIBLE
            allergies_selection_placeholder.visibility = View.GONE
        }
    }

    private fun setLinearLayout(orientation: Int) = LinearLayoutManager(context, orientation, false)
    private fun setGridLayout(spanCount: Int) = GridLayoutManager(context, spanCount)

}