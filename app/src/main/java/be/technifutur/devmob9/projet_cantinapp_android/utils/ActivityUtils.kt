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

class ActivityUtils{

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
fun Fragment.hideKeyboard(){
    view?.let {
        activity?.hideKeyboard(it)
    }
}
fun Activity.hideKeyboard(){
    hideKeyboard(currentFocus ?: View(this))
}
fun Context.hideKeyboard(view: View){
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}
