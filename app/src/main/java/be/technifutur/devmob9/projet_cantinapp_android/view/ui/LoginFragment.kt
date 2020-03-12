package be.technifutur.devmob9.projet_cantinapp_android.view.ui

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.databinding.CredentialsLoginFragmentBinding
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.AuthListener
import be.technifutur.devmob9.projet_cantinapp_android.utils.ActivityUtils
import be.technifutur.devmob9.projet_cantinapp_android.utils.toast
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.LoginViewModel

class LoginFragment: Fragment(), AuthListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<CredentialsLoginFragmentBinding>(inflater, R.layout.credentials_login_fragment, container, false)
        val viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        val view: View = binding.root

        binding.viewModel = viewModel
        viewModel.authListener = this
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val constraintRoot: MotionLayout = view.findViewById(R.id.sign_in_root)
        ActivityUtils().switchLayoutAnimationKeyboard(constraintRoot = constraintRoot)
    }

    override fun onStarted() {
        Toast.makeText(context, "Started", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess() {
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }

    override fun onError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}