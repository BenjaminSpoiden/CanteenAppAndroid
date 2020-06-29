package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.AccountItemModel
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.ProfileItemAdapter
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class AccountFragment: BaseFragment() {

    companion object {
        fun getInstance() = AccountFragment()
    }

    override val title: String
        get() = "Profil"

    private lateinit var recyclerView: RecyclerView
    private val itemAdapter = ItemAdapter<ProfileItemAdapter>()
    private val fastAdapter = FastAdapter.with(itemAdapter)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.profil_rv)
        recyclerView.apply {
            this.adapter = fastAdapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
        dataInflater()
    }

    override fun onDestroy() {
        recyclerView.apply {
            this.adapter = null
            this.layoutManager = null
        }
        super.onDestroy()
    }

    private fun dataInflater() {
        itemAdapter.add(ProfileItemAdapter(AccountItemModel(R.drawable.ic_shopping_basket_black_24dp, "Mes commandes")))
        itemAdapter.add(ProfileItemAdapter(AccountItemModel(R.drawable.ic_credit_card_black_24dp, "Mes Paiements")))
        itemAdapter.add(ProfileItemAdapter(AccountItemModel(R.drawable.ic_gluten_free, "Mes allergies")))
        itemAdapter.add(ProfileItemAdapter(AccountItemModel(R.drawable.ic_settings_black_24dp, "Paramètres")))
    }

}