package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import be.technifutur.devmob9.projet_cantinapp_android.R
import kotlinx.android.synthetic.main.fragment_menu_gdpr.*
import kotlinx.android.synthetic.main.fragment_tos.*

class MenuGDPRFragment: BaseFragment() {
    companion object {
        fun getInstance() = MenuGDPRFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu_gdpr, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callback.fragmentTitle("GDPR")
        context?.let { fillConditionText(it) }
    }

    fun fillConditionText (context: Context) {

        val content = context.applicationContext?.assets?.open("gdpr.txt")?.bufferedReader()
            .use { it?.readText() }
        println(content)

        val collWhat = content?.substringAfter("@What data do we collect?@")
            ?.substringBefore("@How do we collect your data?@")

        val collHow = content?.substringAfter("@How do we collect your data?@")
            ?.substringBefore("@How will we use your data?@")

        val useData =
            content?.substringAfter("@How will we use your data?@")?.substringBefore("@Marketing@")

        val market = content?.substringAfter("@Marketing@")
            ?.substringBefore("@What are your data protection rights?@")

        val dpr = content?.substringAfter("@What are your data protection rights?@")

        collectWhatText.text = collWhat
        collectHowText.text = collHow
        dataUseText.text = useData
        marketingText.text = market
        dataProtectionRightsText.text = dpr
    }

    override fun onDestroy() {
        super.onDestroy()
        observeMenuPosition()
    }

}