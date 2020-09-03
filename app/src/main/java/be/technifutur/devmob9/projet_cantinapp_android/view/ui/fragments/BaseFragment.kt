package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.MenuPositionViewModel

abstract class BaseFragment: Fragment() {

    internal lateinit var callback: OnSettingFragmentTitle
    private val menuPositionViewModel by activityViewModels<MenuPositionViewModel>()

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

    fun observeMenuPosition() {
        menuPositionViewModel.menuPosition.observe(requireActivity()) {
            Log.d(Constants.FIREBASE_TAG, "from contact: $it")
            when(it) {
                0 -> callback.fragmentTitle("Repas")
                1 -> callback.fragmentTitle("Sandwich")
                2 -> callback.fragmentTitle("Condiments")
            }
            menuPositionViewModel.menuPosition.removeSource(menuPositionViewModel.menuPosition)
            menuPositionViewModel.menuPosition.removeObservers(requireActivity())
        }
    }
}