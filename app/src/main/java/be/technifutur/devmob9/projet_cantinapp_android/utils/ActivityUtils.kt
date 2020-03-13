package be.technifutur.devmob9.projet_cantinapp_android.utils
import android.content.Context
import android.graphics.Rect
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout

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
fun Context.toast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
