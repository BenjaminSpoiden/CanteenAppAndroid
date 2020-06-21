package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import be.technifutur.devmob9.projet_cantinapp_android.R

open class BaseFragment: Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.isClickable = true
//        context?.let {
//            ContextCompat.getColor(it, R.color.backgroundAppColor)
//        }?.let {
//            view.setBackgroundColor(it)
//        }
    }
}