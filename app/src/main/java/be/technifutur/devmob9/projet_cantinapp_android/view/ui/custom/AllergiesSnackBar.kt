package be.technifutur.devmob9.projet_cantinapp_android.view.ui.custom

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import be.technifutur.devmob9.projet_cantinapp_android.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.custom_snackbar.view.*

class AllergiesSnackBar{
    companion object {
        @SuppressLint("InflateParams")
        fun make(view: View, illustration: Int, text: String, listener: View.OnClickListener?): Snackbar {
            val snackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG)
            val customView = LayoutInflater.from(view.context).inflate(R.layout.custom_snackbar, null)

            customView.allergies_image.setImageResource(illustration)
            customView.allergies_desc.text = text

            snackbar.view.setBackgroundColor(Color.TRANSPARENT)
            val snackbarLayout = snackbar.view as Snackbar.SnackbarLayout
            snackbarLayout.setPadding(0, 0, 0, 0)

            if(listener == null){
                (customView.findViewById<TextView>(R.id.allergies_detail)).setOnClickListener(null)
            }else {
                (customView.findViewById<TextView>(R.id.allergies_detail)).setOnClickListener {
                    listener.onClick(it)
                    snackbar.dismiss()
                }
            }
            snackbarLayout.addView(customView)
            return snackbar
        }
    }
}