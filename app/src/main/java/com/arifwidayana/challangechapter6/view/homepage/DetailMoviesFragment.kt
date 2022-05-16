package com.arifwidayana.challangechapter6.view.homepage

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.arifwidayana.challangechapter6.R
import com.arifwidayana.challangechapter6.databinding.FragmentDetailMoviesBinding
import com.arifwidayana.challangechapter6.model.utils.Constant
import com.arifwidayana.challangechapter6.viewmodel.HomeViewModel
import com.bumptech.glide.Glide

class DetailMoviesFragment : Fragment() {
    private var bind : FragmentDetailMoviesBinding? = null
    private val binding get() = bind!!
    private val movieViewModel: HomeViewModel by viewModels()
    private val args by navArgs<DetailMoviesFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentDetailMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            val id = args.id
            val listGenre = arrayListOf<String>()

            ivBack.setOnClickListener {
                findNavController().navigate(R.id.action_detailMoviesFragment_to_mainHomepageFragment)
            }

            movieViewModel.apply {
                loading.observe(viewLifecycleOwner){
                    when {
                        it -> pbLoad.visibility = View.VISIBLE
                        else -> pbLoad.visibility = View.GONE
                    }
                }

                dataDetailMovies.observe(viewLifecycleOwner){ it ->
                    it.genres.forEach {
                        listGenre.add(it.name)
                    }
                    Glide.with(root)
                        .load("${Constant.BASE_IMAGE}${it.posterPath}")
                        .into(ivPoster)

                    tvStatus.text = it.status
                    tvTitle.text = it.title
                    tvGenre.text = "Genre: $listGenre"
                    tvRating.text = "Rating: ${it.voteAverage}"
                    tvReleaseDate.text = "Release Date: ${it.releaseDate}"
                    tvOverview.text = it.overview
                }

                detailMovies(id)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bind = null
    }
}