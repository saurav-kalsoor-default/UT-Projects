package com.getdefault.utprojects.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.getdefault.utprojects.databinding.FragmentUserInfoBinding

class UserInfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentUserInfoBinding.inflate(layoutInflater)
        val user = UserInfoFragmentArgs.fromBundle(requireArguments()).user
        binding.user = user

        binding.btnLogout.setOnClickListener {
            findNavController().navigate(UserInfoFragmentDirections.actionUserInfoFragmentToLandingFragment())
        }
        return binding.root
    }
}