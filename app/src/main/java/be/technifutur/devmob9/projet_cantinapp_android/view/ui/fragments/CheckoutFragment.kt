package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.FragmentListener
import be.technifutur.devmob9.projet_cantinapp_android.utils.closeKeyboard
import kotlinx.android.synthetic.main.fragment_checkout.*

class CheckoutFragment: BaseFragment() {
    companion object {
        fun getInstance() = CheckoutFragment()
    }
    override val title: String
        get() = "Payment"


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_checkout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        closeKeyboard(view)

        checkout_cart_holder.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Ceci est un titre")
                .setMessage("Ceci est un message")
                .setPositiveButton("OK") { dialog, which ->
                    Toast.makeText(context, "Clicked on OK", Toast.LENGTH_SHORT).show()
                    dialog.cancel()
                }
                .show()
        }
    }
}