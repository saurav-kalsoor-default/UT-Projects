package com.getdefault.utprojects.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.getdefault.utprojects.*
import com.getdefault.utprojects.databinding.FragmentSignupBinding

class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(layoutInflater)

        binding.btnUserSignup.setOnClickListener {
            initiateSignup()
        }

        return binding.root
    }

    private fun initiateSignup() {
        val name = binding.tilSignupName.editText?.text.toString()
        val email = binding.tilSignupEmail.editText?.text.toString()
        val password = binding.tilSignupPwd.editText?.text.toString()
        val age = binding.tilSignupAge.editText?.text.toString()
        val phone = binding.tilSignupPhone.editText?.text.toString()
        var areCredentialsValid = true

        if(name.isEmpty()){
            binding.tilSignupName.error = getString(R.string.empty_text)
            areCredentialsValid = false
        }else
            binding.tilSignupName.error = null

        if(age.isEmpty()){
            binding.tilSignupAge.error = getString(R.string.empty_text)
            areCredentialsValid = false
        }else
            binding.tilSignupAge.error = null

        if(!isPhoneValid(phone)){
            binding.tilSignupPhone.error = getString(R.string.empty_text)
            areCredentialsValid = false
        }else
            binding.tilSignupPhone.error = null

        if(!isPasswordValid(password)){
            binding.tilSignupPwd.error = getString(R.string.invalid_password)
            areCredentialsValid = false
        }else
            binding.tilSignupPwd.error = null

        if(isEmailValid(email)){
            binding.tilSignupEmail.error = null
        }else{
            binding.tilSignupEmail.error = getString(R.string.invalid_email)
            areCredentialsValid = false
        }


        if(areCredentialsValid){
            val user = User(name, email, password, phone, age)
            signInUser(user)
        }

    }

    private fun signInUser(user : User) {
        Toast.makeText(activity, "Sign In Successful!", Toast.LENGTH_SHORT).show()
        findNavController().navigate(SignupFragmentDirections.actionSignupFragmentToUserInfoFragment(user))
    }

}