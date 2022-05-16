package com.arifwidayana.challangechapter6.view.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.arifwidayana.challangechapter6.R
import com.arifwidayana.challangechapter6.databinding.FragmentFirstScreenBinding

class FirstScreenFragment : Fragment() {
    private var bind : FragmentFirstScreenBinding? = null
    private val binding get() = bind!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentFirstScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val firstScreen = activity?.findViewById<ViewPager2>(R.id.vp_on_boarding)
        binding.tvNext.setOnClickListener {
            firstScreen?.currentItem = 1
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bind = null
    }
}