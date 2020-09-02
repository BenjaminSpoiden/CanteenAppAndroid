package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

/**
 * FAIRE COMME TELEGRAM !
 * VERY IMPORTANT
 */

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import be.technifutur.devmob9.projet_cantinapp_android.R

abstract class BaseFragment: Fragment() {

    internal lateinit var callback: OnSettingFragmentTitle

    fun setTitleListener(callback: OnSettingFragmentTitle) {
        this.callback = callback
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.isClickable = true
        context?.let {
            ContextCompat.getColor(it, R.color.backgroundAppColor)
        }?.let {
            view.setBackgroundColor(it)
        }
    }

    interface OnSettingFragmentTitle {
        fun fragmentTitle(title: String)
    }
}