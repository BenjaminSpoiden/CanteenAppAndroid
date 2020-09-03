package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.view.get
import androidx.fragment.app.replace
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.FragmentListener
import be.technifutur.devmob9.projet_cantinapp_android.utils.BottomNavigationScreens
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.POSITION_1_PAYMENTS
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.POSITION_2_ALLERGIES
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.POSITION_3_SETTINGS
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_home.*

class AccountFragment: BaseFragment() {

    companion object {
        fun getInstance() = AccountFragment()
    }

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
        callback.fragmentTitle("Profil")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            parentFragmentManager.popBackStack()
        }
    }

    private fun onRowSelected() {
        my_payment_row.setOnClickListener {
            listener?.onProfileMenuSelection(POSITION_1_PAYMENTS)
        }
        my_allergies_row.setOnClickListener {
            listener?.onProfileMenuSelection(POSITION_2_ALLERGIES)
        }
        parameters_row.setOnClickListener {
            listener?.onProfileMenuSelection(POSITION_3_SETTINGS)
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
        observeMenuPosition()
    }
}