package be.technifutur.devmob9.projet_cantinapp_android.view.ui


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.OnboardingData
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.OnboardingAdapter
import com.arindicatorview.ARIndicatorView
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnboardingActivity : AppCompatActivity() {

    val items: ArrayList<OnboardingData> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)


        val recyclerViewOnboarding = recycler_view_onboarding.findViewById<RecyclerView>(R.id.recycler_view_onboarding)
        val bottomLayout = bottom_layout_onboarding.findViewById<RelativeLayout>(R.id.bottom_layout_onboarding)
        val skipLayout: LinearLayout = skip_layout.findViewById(R.id.skip_layout)

        val onboardingAdapter = OnboardingAdapter(items, this)
        recyclerViewOnboarding.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewOnboarding.adapter = onboardingAdapter
        val arIndicatorView: ARIndicatorView = findViewById(R.id.indicator_recyclerview)
//        PagerSnapHelper().attachToRecyclerView(recyclerViewOnboarding)


        fillingOnboardingList("TIME", "loremipsumlenrjenjjejejnfjnfjofneizonfezfnezebdfebdeuirbebzeiebizuerieuziuerbuzeiziuehr", R.drawable.time_logo)
        fillingOnboardingList("INFO", "loremipsumlenrjenjjejejnfjnfjonfezfnezebdfebdeuirbebzeiebizuerieuziuerbuzeiziuehr", R.drawable.info_logo)
        fillingOnboardingList("FRUITS", "loremipsumlenrjenjjejejnfjnfjofneizonfezfnezebdfebdeuirbebzeiebizuerieuziuerbuzeiziuehr", R.drawable.fruits_logo)

        arIndicatorView.attachTo(recyclerViewOnboarding, true)

        animations(recyclerViewOnboarding, recyclerViewOnboarding.context, 1)
        animations(bottomLayout, bottomLayout.context, 2)
        animations(skipLayout, skipLayout.context, 3)

    }

    private fun fillingOnboardingList(title: String, desc: String, logo: Int){
        items.add(OnboardingData(title, desc, logo))
    }

    private fun animations(viewGroup: ViewGroup, context: Context, type: Int){
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

