package be.technifutur.devmob9.projet_cantinapp_android.view.ui.custom

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import be.technifutur.devmob9.projet_cantinapp_android.R
import com.google.android.material.snackbar.ContentViewCallback

class CustomSnackBar @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttribute: Int = 0
): ConstraintLayout(context, attributeSet, defStyleAttribute), ContentViewCallback {

    val allergiesImage: ImageView
    val message: TextView

    init {
        View.inflate(context, R.layout.custom_snackbar, this)
        this.allergiesImage = findViewById(R.id.allergies_image)
        this.message = findViewById(R.id.message)
        clipToPadding = false
    }

    override fun animateContentIn(delay: Int, duration: Int) {
        val scaleX = ObjectAnimator.ofFloat(allergiesImage, View.SCALE_X, 0f, 1f)
        val scaleY = ObjectAnimator.ofFloat(allergiesImage, View.SCALE_Y, 0f, 1f)
        val animatorSet = AnimatorSet().apply {
            interpolator = OvershootInterpolator()
            setDuration(500)
            playTogether(scaleX, scaleY)
        }
        animatorSet.start()
    }

    override fun animateContentOut(delay: Int, duration: Int) {

    }
}