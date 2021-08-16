package com.getdefault.utprojects.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.getdefault.utprojects.databinding.FragmentLandingBinding
import java.lang.Exception

class LandingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLandingBinding.inflate(layoutInflater)

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(LandingFragmentDirections.actionLandingFragmentToLoginFragment())
        }

        binding.btnSignup.setOnClickListener {
            findNavController().navigate(LandingFragmentDirections.actionLandingFragmentToSignupFragment())
        }
        return binding.root
    }
}