package com.arifwidayana.challangechapter6.view.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arifwidayana.challangechapter6.adapter.OnBoardingAdapter
import com.arifwidayana.challangechapter6.databinding.FragmentOnBoardingBinding

class OnBoardingFragment : Fragment() {
    private var bind : FragmentOnBoardingBinding? = null
    private val binding get() = bind!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listBoard = arrayListOf(
            FirstScreenFragment(),
            SecondScreenFragment(),
            ThirdScreenFragment()
        )

        val adapterBoard = OnBoardingAdapter(
            listBoard,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.vpOnBoarding.adapter = adapterBoard
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bind = null
    }
}