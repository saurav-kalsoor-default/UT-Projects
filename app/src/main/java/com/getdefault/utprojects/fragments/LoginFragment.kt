package com.getdefault.utprojects.fragments

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.getdefault.utprojects.R
import com.getdefault.utprojects.User
import com.getdefault.utprojects.databinding.FragmentLoginBinding
import com.getdefault.utprojects.viewmodels.LoginViewModel
import com.getdefault.utprojects.viewmodels.LoginViewModelProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.FileReader
import java.lang.Exception
import java.util.*

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

        if(viewModel.isEmailValid(email))
            binding.tilLoginEmail.error = null
        else{
            binding.tilLoginEmail.error = getString(R.string.invalid_email)
            areCredentialsValid = false
        }

        if(viewModel.isPasswordValid(password))
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
        Toast.makeText(activity, "Email and Password do not match", Toast.LENGTH_SHORT).show()
    }
}