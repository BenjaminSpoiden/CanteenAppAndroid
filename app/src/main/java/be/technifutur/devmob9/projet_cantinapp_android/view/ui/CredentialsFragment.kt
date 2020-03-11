package be.technifutur.devmob9.projet_cantinapp_android.view.ui

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import be.technifutur.devmob9.projet_cantinapp_android.R


class CredentialsFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.credentials_login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rect = view.findViewById<ImageView>(R.id.ui_rect)
        val logoPlaceholder = view.findViewById<ImageView>(R.id.logo_placeholder)
        val logo = view.findViewById<ImageView>(R.id.logo)
        val loginText = view.findViewById<TextView>(R.id.login_text)
        val lp = ConstraintLayout.LayoutParams(loginText.getLayoutParams())
        val holder = view.findViewById<ImageView>(R.id.credentials_holder)
        var isKeyboardOn: Boolean
        val constraintsSet = ConstraintSet()
        val constraintLayout = view.findViewById<ConstraintLayout>(R.id.sign_in_root)



        constraintLayout.viewTreeObserver.addOnGlobalLayoutListener {
            val rectangle = Rect()
            constraintLayout.getWindowVisibleDisplayFrame(rectangle)

            val screenHeight = constraintLayout.rootView.height
            val keypadHeight = screenHeight - rectangle.bottom

            if (keypadHeight > screenHeight * 0.15) {
                Toast.makeText(context, "VISIBLE KEYBOARD", Toast.LENGTH_SHORT).show()
//                val transition: TransitionDrawable = rect.background as TransitionDrawable
//                transition.startTransition(500)
                if(context != null){
                    rect.layoutParams.height = (getScreenHeight(context!!) * 0.20).toInt()
                    logoPlaceholder.visibility = View.GONE
                    logo.visibility = View.GONE
                    constraintsSet.clone(constraintLayout)
//                    rect.setBackgroundColor(Color.parseColor("#020024"))
                    constraintsSet.connect(R.id.login_text, ConstraintSet.START, R.id.ui_rect, ConstraintSet.START)
                    constraintsSet.connect(R.id.login_text, ConstraintSet.TOP, R.id.ui_rect, ConstraintSet.TOP)
                    constraintsSet.connect(R.id.login_text, ConstraintSet.BOTTOM, R.id.ui_rect, ConstraintSet.BOTTOM)
                    constraintsSet.connect(R.id.login_text, ConstraintSet.END, R.id.ui_rect, ConstraintSet.END)
                    constraintsSet.applyTo(constraintLayout)

                }
            } else {
                Toast.makeText(context, "NO KEYBOARD", Toast.LENGTH_SHORT).show()
                rect.layoutParams.height = (getScreenHeight(context!!) / 1.15).toInt()
                logoPlaceholder.visibility = View.VISIBLE
                logo.visibility = View.VISIBLE
//                rect.setBackgroundResource(R.drawable.credentials_gradient_page)
                constraintsSet.clone(constraintLayout)
                constraintsSet.connect(R.id.login_text, ConstraintSet.START, R.id.credentials_holder_background, ConstraintSet.START)
                constraintsSet.connect(R.id.login_text, ConstraintSet.TOP, R.id.credentials_holder_background, ConstraintSet.TOP)
                constraintsSet.connect(R.id.login_text, ConstraintSet.BOTTOM, R.id.credentials_holder_background, ConstraintSet.BOTTOM)
                constraintsSet.connect(R.id.login_text, ConstraintSet.END, R.id.credentials_holder_background, ConstraintSet.END)
                constraintsSet.applyTo(constraintLayout)
                val transition: TransitionDrawable = rect.background as TransitionDrawable
                transition.startTransition(400)

            }
        }

    }

//    fun adaptScreenHeight(isKeyboardVisible: Boolean){
//        val rect = view?.findViewById<ImageView>(R.id.ui_rect)
//        //Toast.makeText(this, "adaptScreenHeight", Toast.LENGTH_SHORT).show()
//        if (rect != null){
//            if(isKeyboardVisible){
//                rect.layoutParams?.height = (context?.let {
//                    getScreenHeight(it)
//                }?.times(0.50))?.toInt()
//            }else {
//                rect.layoutParams?.height = (context?.let {
//                    getScreenHeight(it)
//                }?.div(0.50))?.toInt()
//            }
//        }
//    }
    private fun getScreenHeight(context: Context): Int {
        Toast.makeText(context, "getScreenHeight", Toast.LENGTH_SHORT).show()
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }

}