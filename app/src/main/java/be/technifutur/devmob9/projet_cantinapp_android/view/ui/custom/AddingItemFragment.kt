package be.technifutur.devmob9.projet_cantinapp_android.view.ui.custom

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.ItemSelectedListener
import be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments.MenuRepasFragment
import kotlinx.android.synthetic.main.fragment_adding_item.*

class AddingItemFragment: Fragment(), ItemSelectedListener {

    companion object {
        fun getInstance() = AddingItemFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_adding_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onNumberItemSelected(numberOfItems: Int) {
        adding_item_btn.text = "$numberOfItems"
    }

}