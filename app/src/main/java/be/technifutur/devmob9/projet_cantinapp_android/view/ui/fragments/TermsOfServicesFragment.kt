package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.technifutur.devmob9.projet_cantinapp_android.R
import kotlinx.android.synthetic.main.fragment_tos.*


class TermsOfServicesFragment: BaseFragment() {

    companion object {
        fun getInstance() = TermsOfServicesFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
        return inflater.inflate(R.layout.fragment_tos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callback.fragmentTitle("Conditions d'utilisation")
        context?.let { fillConditionText(it) }


    }

    fun fillConditionText (context: Context) {

        val content =  context.applicationContext?.assets?.open("tos.txt")?.bufferedReader().use { it?.readText() }
        println(content)

        val intro = content?.substringAfter("@introduction@")?.substringBefore("@Intellectual property@")

        val intProp = content?.substringAfter("@Intellectual property@")?.substringBefore("@Restrictions@")

        val restrict = content?.substringAfter("@Restrictions@")?.substringBefore("@Warranty and limitation of liability@")

        val warLimLiab = content?.substringAfter("@Warranty and limitation of liability@")?.substringBefore("@Contact@")

        val contact = content?.substringAfter("@Contact@")

        introductionText.text = intro
        intellectualpropertyText.text = intProp
        restrictionsText.text = restrict
        warrantyLimitationLiabilityText.text = warLimLiab
        contactText.text = contact

    }

    override fun onDestroy() {
        super.onDestroy()
        observeMenuPosition()
    }
}