package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.FragmentListener
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.POSITION_1_ORDER
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.POSITION_2_PAYMENTS
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.POSITION_3_ALLERGIES
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.POSITION_4_SETTINGS
import kotlinx.android.synthetic.main.fragment_account.*

class AccountFragment: BaseFragment() {

    companion object {
        fun getInstance() = AccountFragment()
    }

    override val title: String
        get() = "Profil"

    private var listener: FragmentListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onRowSelected()
    }

    private fun onRowSelected() {
        my_order_row.setOnClickListener {
            listener?.onProfileMenuSelection(POSITION_1_ORDER)
        }
        my_payment_row.setOnClickListener {
            listener?.onProfileMenuSelection(POSITION_2_PAYMENTS)
        }
        my_allergies_row.setOnClickListener {
            listener?.onProfileMenuSelection(POSITION_3_ALLERGIES)
        }
        parameters_row.setOnClickListener {
            listener?.onProfileMenuSelection(POSITION_4_SETTINGS)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentListener){
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}