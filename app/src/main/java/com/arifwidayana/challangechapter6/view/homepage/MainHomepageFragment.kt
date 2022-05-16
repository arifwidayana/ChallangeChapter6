package com.arifwidayana.challangechapter6.view.homepage

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.arifwidayana.challangechapter6.R
import com.arifwidayana.challangechapter6.adapter.ListPlayingMoviesAdapter
import com.arifwidayana.challangechapter6.databinding.FragmentMainHomepageBinding
import com.arifwidayana.challangechapter6.model.DatabaseStore
import com.arifwidayana.challangechapter6.model.pojo.listmovies.ResultListPlaying
import com.arifwidayana.challangechapter6.model.utils.Constant
import com.arifwidayana.challangechapter6.model.preference.SharedHelper
import com.arifwidayana.challangechapter6.viewmodel.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainHomepageFragment : Fragment() {
    private var bind : FragmentMainHomepageBinding? = null
    private val binding get() = bind!!
    private var user: DatabaseStore? = null
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var shared: SharedHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentMainHomepageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shared = SharedHelper(requireContext())
        user = DatabaseStore.getData(requireContext())

        eventClick()
        loadingGetData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bind = null
    }

    private fun eventClick() {
        binding.apply {
            ivFavorite.setOnClickListener {
                findNavController().navigate(R.id.action_mainHomepageFragment_to_favoriteFragment)
            }

            ivProfile.setOnClickListener {
                findNavController().navigate(R.id.action_mainHomepageFragment_to_profileUserFragment)
            }

        }
    }

    @SuppressLint("SetTextI18n")
    private fun loadingGetData() {
        binding.apply {
//            homeViewModel.getPref.observe(viewLifecycleOwner) {
//                tvShowName.text = "Hi, ${it.username}"
//            }
            CoroutineScope(Dispatchers.Main).launch {
                val getName = user?.userDao()?.getUsername(shared.getString(Constant.USERNAME).toString())
                tvShowName.text = "Hi, ${getName?.name}"
            }

            homeViewModel.apply {
                loading.observe(viewLifecycleOwner){
                    when {
                        it -> {
                            pbMovies.visibility = View.VISIBLE
                        }
                        else -> {
                            pbMovies.visibility = View.GONE
                            clDisplay.visibility = View.VISIBLE
                        }
                    }
                }

                dataNowPlayingMovies.observe(viewLifecycleOwner){
                    fetchNowPlayingMovies(it.results)
                }

                dataPopularMovies.observe(viewLifecycleOwner){
                    fetchPopularMovies(it.results)
                }

                dataUpComingMovies.observe(viewLifecycleOwner){
                    fetchUpComingMovies(it.results)
                }

                dataTopRatedMovies.observe(viewLifecycleOwner){
                    fetchTopRated(it.results)
                }
            }
        }
    }

    private fun fetchNowPlayingMovies(results: List<ResultListPlaying>) {
        val adapter = ListPlayingMoviesAdapter{
            binding.apply {
                val parcel = MainHomepageFragmentDirections.actionMainHomepageFragmentToDetailMoviesFragment()
                parcel.id = it.id
                findNavController().navigate(parcel)
            }
        }
        adapter.submitList(results)
        binding.rvMovieNowPlaying.adapter = adapter
    }

    private fun fetchPopularMovies(results: List<ResultListPlaying>) {
        val adapter = ListPlayingMoviesAdapter{
            binding.apply {
                val parcel = MainHomepageFragmentDirections.actionMainHomepageFragmentToDetailMoviesFragment()
                parcel.id = it.id
                findNavController().navigate(parcel)
            }
        }
        adapter.submitList(results)
        binding.rvMoviePopular.adapter = adapter
    }

    private fun fetchUpComingMovies(results: List<ResultListPlaying>) {
        val adapter = ListPlayingMoviesAdapter{
            binding.apply {
                val parcel = MainHomepageFragmentDirections.actionMainHomepageFragmentToDetailMoviesFragment()
                parcel.id = it.id
                findNavController().navigate(parcel)
            }
        }
        adapter.submitList(results)
        binding.rvMovieUpComing.adapter = adapter
    }

    private fun fetchTopRated(results: List<ResultListPlaying>) {
        val adapter = ListPlayingMoviesAdapter{
            binding.apply {
                val parcel = MainHomepageFragmentDirections.actionMainHomepageFragmentToDetailMoviesFragment()
                parcel.id = it.id
                findNavController().navigate(parcel)
            }
        }
        adapter.submitList(results)
        binding.rvMovieTopRated.adapter = adapter
    }
}