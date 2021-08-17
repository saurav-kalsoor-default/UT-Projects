package com.getdefault.utprojects.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.getdefault.utprojects.R
import com.getdefault.utprojects.databinding.FragmentLoginBinding
import com.getdefault.utprojects.isEmailValid
import com.getdefault.utprojects.isPasswordValid
import com.getdefault.utprojects.viewmodels.LoginViewModel
import com.getdefault.utprojects.viewmodels.LoginViewModelProvider

class LoginFragment : Fragment() {

    private lateinit var binding : FragmentLoginBinding
    private lateinit var viewModel : LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)

        val application = requireNotNull(this.activity).application
        val viewModelFactory = LoginViewModelProvider(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
        binding.viewModel = viewModel

        binding.btnUserLogin.setOnClickListener {
            initiateLogin()
        }

        return binding.root
    }

    private fun initiateLogin() {
        val email = binding.tilLoginEmail.editText?.text.toString().lowercase()
        val password = binding.tilLoginPwd.editText?.text.toString().lowercase()
        var areCredentialsValid = true

        if(isEmailValid(email))
            binding.tilLoginEmail.error = null
        else{
            binding.tilLoginEmail.error = getString(R.string.invalid_email)
            areCredentialsValid = false
        }

        if(isPasswordValid(password))
            binding.tilLoginPwd.error = null
        else{
            binding.tilLoginPwd.error = getString(R.string.invalid_password)
            areCredentialsValid = false
        }

        if(!areCredentialsValid)
            return

        val user = viewModel.login(email, password)
        user?.let {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToUserInfoFragment(it))
            return
        }
        Toast.makeText(activity, R.string.login_error, Toast.LENGTH_SHORT).show()
    }
}