package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments


import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.FragmentListener

abstract class BaseFragment: Fragment() {
    abstract val title: String

    private var listener: FragmentListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.isClickable = true
        context?.let {
            ContextCompat.getColor(it, R.color.backgroundAppColor)
        }?.let {
            view.setBackgroundColor(it)
        }
    }

    override fun onStart() {
        super.onStart()
        listener?.getFragmentTitle(title)
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
}