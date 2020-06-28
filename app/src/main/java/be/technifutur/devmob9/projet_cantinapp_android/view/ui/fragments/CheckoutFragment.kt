package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.technifutur.devmob9.projet_cantinapp_android.R
import kotlinx.android.synthetic.main.fragment_checkout.*

class CheckoutFragment: BaseFragment() {
    companion object {
        fun getInstance() = CheckoutFragment()
    }
    override val title: String
        get() = "Payment"

    private var isClicked: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_checkout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bank_type_payment.setOnClickListener {
            TransitionManager.beginDelayedTransition(payment_card_view)
            if(isClicked) {
                isClicked = false
                register_account.visibility = View.VISIBLE

            }else {
                isClicked = true
                register_account.visibility = View.GONE
            }
        }
    }
}