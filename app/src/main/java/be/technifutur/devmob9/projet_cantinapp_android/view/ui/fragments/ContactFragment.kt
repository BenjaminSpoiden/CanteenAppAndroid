package be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.databinding.FragmentContactBinding
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.ContactPageViewModel
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.factory.ContactPageVMFactory
import kotlinx.android.synthetic.main.fragment_contact.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class ContactFragment: BaseFragment(), KodeinAware {

    override val kodein by kodein()
    private val contactPageVMFactory: ContactPageVMFactory by instance<ContactPageVMFactory>()

    private lateinit var contactPageViewModel: ContactPageViewModel

    companion object {
        fun getInstance() = ContactFragment()
    }

    override val title: String
        get() = "Contact"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentContactBinding>(inflater, R.layout.fragment_contact, container, false)
        contactPageViewModel = ViewModelProvider(this, contactPageVMFactory).get(ContactPageViewModel::class.java)
        val view = binding.root
        binding.contactViewModel = contactPageViewModel
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listOfString = listOf(
            "Item 1",
            "Item 2",
            "Item 3",
            "Item 4",
            "Item 5"
        )

        val listOfString2 = listOf(
            "Item 6",
            "Item 7",
            "Item 5"
        )

        settingUpArrayAdapter(listOfString, person_contact_dropdown)
        settingUpArrayAdapter(listOfString2, type_contact_dropdown)

        contact_send_btn.isEnabled = false


        contactPageViewModel.serviceToContact.observe(viewLifecycleOwner, Observer { service ->
            contactPageViewModel.objectContact.observe(viewLifecycleOwner, Observer { objectContact ->
                contactPageViewModel.message.observe(viewLifecycleOwner, Observer { message ->
                    contact_send_btn.isEnabled = !service.isNullOrEmpty() && !objectContact.isNullOrEmpty() && !message.isNullOrEmpty()
                })
            })
        })

        contactPageViewModel.isCheckboxChecked.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
        })

    }

    private fun settingUpArrayAdapter(list: List<String>, autoCompleteTextView: AutoCompleteTextView) {
        ArrayAdapter<String>(
            requireContext(),
            R.layout.dropdown_item,
            list
        ).also {
            autoCompleteTextView.setAdapter(it)
        }
    }
}