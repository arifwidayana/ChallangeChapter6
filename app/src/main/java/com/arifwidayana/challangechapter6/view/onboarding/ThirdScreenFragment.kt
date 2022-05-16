package com.arifwidayana.challangechapter6.view.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.arifwidayana.challangechapter6.R
import com.arifwidayana.challangechapter6.databinding.FragmentThirdScreenBinding
import com.arifwidayana.challangechapter6.model.utils.Constant
import com.arifwidayana.challangechapter6.model.preference.SharedHelper

class ThirdScreenFragment : Fragment() {
    private var bind : FragmentThirdScreenBinding? = null
    private val binding get() = bind!!
    private lateinit var shared: SharedHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentThirdScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shared = SharedHelper(requireContext())
        binding.btnStart.setOnClickListener {
            findNavController().navigate(R.id.action_onBoarding_to_loginFragment)
            onBoarding()
        }
    }

    private fun onBoarding() {
        shared.put(Constant.FINISHED, true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bind = null
    }
}