package be.technifutur.devmob9.projet_cantinapp_android.view.ui



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
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
        val skipButton = findViewById<Button>(R.id.skip_button)

        val onboardingAdapter = OnboardingAdapter(items, this)
        recyclerViewOnboarding.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewOnboarding.adapter = onboardingAdapter
        val arIndicatorView: ARIndicatorView = findViewById(R.id.indicator_recyclerview)



        fillingOnboardingList("TIME", "loremipsumlenrjenjjejejnfjnfjofneizonfezfnezebdfebdeuirbebzeiebizuerieuziuerbuzeiziuehr", R.drawable.time_logo)
        fillingOnboardingList("INFO", "loremipsumlenrjenjjejejnfjnfjonfezfnezebdfebdeuirbebzeiebizuerieuziuerbuzeiziuehr", R.drawable.info_logo)
        fillingOnboardingList("FRUITS", "loremipsumlenrjenjjejejnfjnfjofneizonfezfnezebdfebdeuirbebzeiebizuerieuziuerbuzeiziuehr", R.drawable.fruits_logo)

        arIndicatorView.attachTo(recyclerViewOnboarding, true)


        Animations().animations(recyclerViewOnboarding, recyclerViewOnboarding.context, 1)
        Animations().animations(bottomLayout, bottomLayout.context, 2)
        Animations().animations(container, skipButton.context, 3)

    }

    private fun fillingOnboardingList(title: String, desc: String, logo: Int){
        items.add(OnboardingData(title, desc, logo))
    }
}

