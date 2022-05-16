package com.arifwidayana.challangechapter6.view.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.arifwidayana.challangechapter6.R
import com.arifwidayana.challangechapter6.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {
    private var bind : FragmentFavoriteBinding? = null
    private val binding get() = bind!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listFavorite()
        onClick()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bind = null
    }

    private fun onClick() {
        binding.apply {
            ivBackHomepage.setOnClickListener {
                findNavController().navigate(R.id.action_favoriteFragment_to_mainHomepageFragment)
            }
        }
    }

    private fun listFavorite() {

    }

}