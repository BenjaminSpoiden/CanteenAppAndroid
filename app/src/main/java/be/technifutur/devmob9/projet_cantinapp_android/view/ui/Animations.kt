package be.technifutur.devmob9.projet_cantinapp_android.view.ui

import android.content.Context
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import be.technifutur.devmob9.projet_cantinapp_android.R

class Animations {

    public fun animations(viewGroup: ViewGroup, context: Context, type: Int){
        val animationController: LayoutAnimationController
        when(type){
            1 -> {
                animationController = AnimationUtils.loadLayoutAnimation(context,
                    R.anim.layout_fall_down
                )
                viewGroup.layoutAnimation = animationController
                viewGroup.startLayoutAnimation()
            }

            2 -> {
                animationController = AnimationUtils.loadLayoutAnimation(context,
                    R.anim.layout_slide_from_bottom
                )
                viewGroup.layoutAnimation = animationController
                viewGroup.startLayoutAnimation()
            }

            3 -> {
                animationController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_slide_from_top)
                viewGroup.layoutAnimation = animationController
                viewGroup.startLayoutAnimation()
            }
        }
    }
}