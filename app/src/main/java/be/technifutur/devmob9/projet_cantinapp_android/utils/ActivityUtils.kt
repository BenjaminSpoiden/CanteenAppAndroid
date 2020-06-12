package be.technifutur.devmob9.projet_cantinapp_android.utils
import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment

object ActivityUtils{
    fun switchLayoutAnimationKeyboard(constraintRoot: MotionLayout){
        var isActive: Boolean
        constraintRoot.viewTreeObserver.addOnGlobalLayoutListener {
            val rectangle = Rect()
            constraintRoot.getWindowVisibleDisplayFrame(rectangle)

            val screenHeight = constraintRoot.rootView.height
            val keypadHeight = screenHeight - rectangle.bottom

            isActive = keypadHeight > screenHeight * 0.15

            if(isActive){
                constraintRoot.transitionToEnd()
            }else{
                constraintRoot.transitionToStart()
            }
        }
    }
}
