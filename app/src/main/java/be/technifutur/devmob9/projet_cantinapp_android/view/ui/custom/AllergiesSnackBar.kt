package be.technifutur.devmob9.projet_cantinapp_android.view.ui.custom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.utils.findSuitableParent
import com.google.android.material.snackbar.BaseTransientBottomBar
import java.lang.IllegalArgumentException

class AllergiesSnackBar(parent: ViewGroup, content: CustomSnackBar): BaseTransientBottomBar<AllergiesSnackBar>(parent, content, content) {

    init {
        getView().setBackgroundColor(ContextCompat.getColor(view.context, android.R.color.transparent))
        getView().setPadding(0, 0, 0, 0)
    }

    companion object {
        fun make(view: View, illustration: Int, text: String): AllergiesSnackBar {
            val parent = view.findSuitableParent() ?: throw IllegalArgumentException("No suitable parent found from given view")

            val customView = LayoutInflater.from(view.context).inflate(
                R.layout.custom_snackbar_inflater,
                parent,
                false
            ) as CustomSnackBar

            customView.allergiesImage.setImageResource(illustration)
            customView.message.text = text

            return AllergiesSnackBar(parent, customView)
        }
    }
}