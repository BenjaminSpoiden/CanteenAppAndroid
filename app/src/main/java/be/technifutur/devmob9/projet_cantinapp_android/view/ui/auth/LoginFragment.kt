package be.technifutur.devmob9.projet_cantinapp_android.view.ui.auth

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.databinding.CredentialsLoginFragmentBinding
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.AuthListener
import be.technifutur.devmob9.projet_cantinapp_android.utils.ActivityUtils
import be.technifutur.devmob9.projet_cantinapp_android.utils.fragmentTransaction
import be.technifutur.devmob9.projet_cantinapp_android.view.ui.MainMenuFragment
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.AuthViewModel
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.AuthViewModelFactory
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class LoginFragment: Fragment(), AuthListener, KodeinAware {

    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance()
    private lateinit var viewModel: AuthViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<CredentialsLoginFragmentBinding>(inflater, R.layout.credentials_login_fragment, container, false)
        viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        val view: View = binding.root
        binding.loginViewModel = viewModel
        viewModel.authListener = this


        //Allow to check if the user is logged in, if so we directly load the MainMenuFragment
        viewModel.userLoggedIn.observe(viewLifecycleOwner, Observer {
            if(it){
                fragmentTransaction(MainMenuFragment(), R.id.fragment_container)
            }
        })
        val registerButton: Button = view.findViewById(R.id.register_button)
        registerButton.setOnClickListener {
            fragmentTransaction(SignupFragment(), R.id.fragment_container)
        }
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

    override fun onSuccess(username: String) {
        Toast.makeText(context, "Success, $username", Toast.LENGTH_SHORT).show()
    }

    override fun onError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}