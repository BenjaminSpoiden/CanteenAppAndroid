package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.databinding.FragmentContactBinding
import be.technifutur.devmob9.projet_cantinapp_android.databinding.FragmentContactModule10Binding
import be.technifutur.devmob9.projet_cantinapp_android.databinding.FragmentContactModule10BindingImpl
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.ContactPageViewModel
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.factory.ContactPageVMFactory
import kotlinx.android.synthetic.main.fragment_contact_module_1_0.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class ContactFragmentModule1: BaseFragment(), KodeinAware {

    override val kodein by kodein()
    private val contactPageVMFactory: ContactPageVMFactory by instance<ContactPageVMFactory>()

    private lateinit var contactPageViewModel: ContactPageViewModel

    companion object {
        fun getInstance() = ContactFragmentModule1()
    }

    override val title: String
        get() = "Contact"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentContactModule10Binding>(inflater, R.layout.fragment_contact_module_1_0, container, false)
        contactPageViewModel = ViewModelProvider(this, contactPageVMFactory).get(ContactPageViewModel::class.java)
        val view = binding.root
        binding.contactViewModel = contactPageViewModel
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sendMailButton.setOnClickListener {
            val uriText = "mailto:support@canteenapp.com" /*+ "?subject=" + Uri.encode("some subject text here") + "&body=" + Uri.encode("some text here")*/

            val uri: Uri = Uri.parse(uriText)

            val sendIntent = Intent(Intent.ACTION_SENDTO)
            sendIntent.data = uri
            startActivity(Intent.createChooser(sendIntent, "Send email"))
        }

    }
}