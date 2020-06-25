package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.view.ui.custom.AllergiesSnackBar

class DetailsFragment: BaseFragment() {

    companion object {
        fun getInstance() = DetailsFragment()
    }

    override val title: String
        get() = "Details"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun snackBars(view: View, text: String){
        AllergiesSnackBar.make(view, R.drawable.croissant_illustration, text).show()
    }
}