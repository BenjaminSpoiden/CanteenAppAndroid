package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.utils.closeKeyboard
import kotlinx.android.synthetic.main.custom_dialog_allergies.*
import kotlinx.android.synthetic.main.fragment_checkout.*

class CheckoutFragment: BaseFragment() {

    companion object {
        fun getInstance() = CheckoutFragment()
    }

    private lateinit var allergyDialog: Dialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_checkout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callback.fragmentTitle("Enregistrer un compte")
        closeKeyboard(view)

        allergyDialog = context?.let { Dialog(it) }!!

        customAlertDialog()
    }

    private fun customAlertDialog() {

        checkout_cart_holder.setOnClickListener {
            allergyDialog.setContentView(R.layout.custom_dialog_allergies)
            allergyDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            allergyDialog.card_payment.setOnClickListener {
                Toast.makeText(context, "Test", Toast.LENGTH_SHORT).show()
                allergyDialog.dismiss()
            }
            allergyDialog.card_delete.setOnClickListener {
                Toast.makeText(context, "Test", Toast.LENGTH_SHORT).show()
                allergyDialog.dismiss()
            }
            allergyDialog.card_frozen.setOnClickListener {
                Toast.makeText(context, "Test", Toast.LENGTH_SHORT).show()
                allergyDialog.dismiss()
            }
            allergyDialog.card_show.setOnClickListener {
                Toast.makeText(context, "Test", Toast.LENGTH_SHORT).show()
                allergyDialog.dismiss()
            }
            allergyDialog.show()
        }
    }

    override fun onDestroyView() {
        callback.fragmentTitle("Panier")
        super.onDestroyView()
    }
}