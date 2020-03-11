package be.technifutur.devmob9.projet_cantinapp_android.view.ui

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.transition.Transition
import android.transition.TransitionManager
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import be.technifutur.devmob9.projet_cantinapp_android.R
import kotlinx.android.synthetic.main.credentials_login_fragment.*
import kotlinx.android.synthetic.main.credentials_login_fragment.view.*


class CredentialsFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.credentials_login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val constraintRoot: MotionLayout = view.findViewById(R.id.sign_in_root)
        val constraintOld: ConstraintSet = ConstraintSet()
        val constraintNew: ConstraintSet = ConstraintSet()


//        constraintOld.clone(sign_in_root)
//        constraintNew.clone(context, R.layout.credential_login_fragment_transition)
//        TransitionManager.beginDelayedTransition(sign_in_root)
//
//        if(isKeyboardActive(sign_in_root)){
//            constraintNew.applyTo(sign_in_root)
//            Toast.makeText(context, "Oui", Toast.LENGTH_SHORT).show()
//        }else{
//            constraintOld.applyTo(sign_in_root)
//            Toast.makeText(context, "Non", Toast.LENGTH_SHORT).show()
//        }
//
//        view.login_button.setOnClickListener {
//            Toast.makeText(context, "Oui", Toast.LENGTH_SHORT).show()
//        }

    }
    private fun getScreenHeight(context: Context): Int {
        Toast.makeText(context, "getScreenHeight", Toast.LENGTH_SHORT).show()
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }

    private fun isKeyboardActive(constraintLayout: ConstraintLayout): Boolean{
        var isActive: Boolean = false
        constraintLayout.viewTreeObserver.addOnGlobalLayoutListener {
            val rectangle = Rect()
            constraintLayout.getWindowVisibleDisplayFrame(rectangle)

            val screenHeight = constraintLayout.rootView.height
            val keypadHeight = screenHeight - rectangle.bottom

            isActive = keypadHeight > screenHeight * 0.15
        }
        return isActive
    }

}