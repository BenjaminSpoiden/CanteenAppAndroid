package be.technifutur.devmob9.projet_cantinapp_android.utils

import android.graphics.Color
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import be.technifutur.devmob9.projet_cantinapp_android.R
import com.google.android.material.card.MaterialCardView

fun Fragment.cardState(isSelected: Boolean, materialCardView: MaterialCardView) {
    if(isSelected) {
        Log.d("ClickEvent", "Checked")
        materialCardView.setCardBackgroundColor(resources.getColor(R.color.tameGreen, resources.newTheme()))
    }else {
        Log.d("ClickEvent", "Unchecked")
        materialCardView.setCardBackgroundColor(Color.WHITE)
    }
}

fun Any.colorSelection(colorString: String) = Color.parseColor(colorString)
