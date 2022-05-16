package com.arifwidayana.challangechapter6.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnBoardingAdapter(list: ArrayList<Fragment>, fm: FragmentManager, ls: Lifecycle) : FragmentStateAdapter(fm,ls) {
    private val listFrag = list
    override fun getItemCount(): Int {
        return listFrag.size
    }

    override fun createFragment(position: Int): Fragment {
        return listFrag[position]
    }
}