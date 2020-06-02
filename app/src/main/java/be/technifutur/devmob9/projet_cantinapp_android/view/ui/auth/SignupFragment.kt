package be.technifutur.devmob9.projet_cantinapp_android.view.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.databinding.CredentialsSignupFragmentBinding
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.AuthListener
import be.technifutur.devmob9.projet_cantinapp_android.utils.ActivityUtils
import be.technifutur.devmob9.projet_cantinapp_android.utils.fragmentTransaction
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.AuthViewModel
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.factory.AuthViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class SignupFragment: Fragment(), AuthListener, KodeinAware {

    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance<AuthViewModelFactory>()
    private lateinit var viewModel: AuthViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<CredentialsSignupFragmentBinding>(inflater, R.layout.credentials_signup_fragment, container,false)
        viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        val view: View = binding.root
        binding.signupViewModel = viewModel
        viewModel.authListener = this


        val backToLoginButton: Button = view.findViewById(R.id.backToLogin_button)
        backToLoginButton.setOnClickListener {
            fragmentTransaction(LoginFragment(), R.id.fragment_container)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val constraintRoot: MotionLayout = view.findViewById(R.id.signup_root)
        ActivityUtils().switchLayoutAnimationKeyboard(constraintRoot = constraintRoot)
    }


    override fun onStarted() {
        Toast.makeText(context, "Started", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess(username: String) {
        Toast.makeText(context, "Success, $username", Toast.LENGTH_SHORT).show()
    }

    override fun onError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}