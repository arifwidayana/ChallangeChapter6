package com.arifwidayana.challangechapter6.view.homepage

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.arifwidayana.challangechapter6.R
import com.arifwidayana.challangechapter6.databinding.FragmentProfileUserBinding
import com.arifwidayana.challangechapter6.model.DatabaseStore
import com.arifwidayana.challangechapter6.model.data.UserEntity
import com.arifwidayana.challangechapter6.model.utils.Constant
import com.arifwidayana.challangechapter6.model.preference.SharedHelper
import com.arifwidayana.challangechapter6.viewmodel.UserViewModel
import com.bumptech.glide.Glide
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File

class ProfileUserFragment : Fragment() {
    private var bind: FragmentProfileUserBinding? = null
    private val binding get() = bind!!
    private var user: DatabaseStore? = null
    private lateinit var shared: SharedHelper
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentProfileUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shared = SharedHelper(requireContext())
        user = DatabaseStore.getData(requireContext())
        userViewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]
        binding.apply {
            val username = shared.getString(Constant.USERNAME)
            when{
                user != null -> getUser(username)
            }

            fetchDataUser()

            ivBackHome.setOnClickListener {
                findNavController().navigate(R.id.action_profileUserFragment_to_mainHomepageFragment)
            }

            cvProfile.setOnClickListener {
                openImagePicker()
            }

            btnEdit.setOnClickListener {
                findNavController().navigate(R.id.action_profileUserFragment_to_editProfileFragment)
                fetchDataUser()
            }

            btnLogout.setOnClickListener {
                shared.clear()
                Toast.makeText(requireContext(), "Logout Success", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_profileUserFragment_to_loginFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bind = null
    }

    private fun openImagePicker() {
        ImagePicker.with(this)
            .crop()
            .saveDir(File(activity?.externalCacheDir, "Image Picker"))
            .compress(1024)
            .maxResultSize(1080, 1080)
            .createIntent { intent ->
                startForProfileImageResult.launch(intent)
            }
    }

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data
            when (resultCode) {
                Activity.RESULT_OK -> {
                    //Image Uri will not be null for RESULT_OK
                    val fileUri = data?.data
                    fileUri?.let { saveImage(it) }

                }
                ImagePicker.RESULT_ERROR -> {
                    Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
                }

            }
        }

    private fun saveImage(uri: Uri) {
        binding.apply {
            ivProfile.setImageURI(uri)
            userViewModel.user.observe(viewLifecycleOwner) {
                val newData = UserEntity(
                    it.id,
                    it.name,
                    uri.toString(),
                    it.email,
                    it.age,
                    it.phone_number,
                    it.username,
                    it.password
                )
                lifecycleScope.launch(Dispatchers.IO){
                    user?.userDao()?.insertUser(newData)
                }
            }
        }
    }

    private fun getUser(username: String?) {
        lifecycleScope.launch(Dispatchers.IO) {
            val data = user?.userDao()?.getUsername(username)
            runBlocking(Dispatchers.Main) {
                data?.let {
                    userViewModel.dataUser(it)
                }
            }
        }
    }

    private fun fetchDataUser() {
        binding.apply {
            userViewModel.user.observe(viewLifecycleOwner){
                tvGetUsername.text = it.username
                Glide.with(root)
                    .load(it.profile)
                    .into(ivProfile)
                tvGetName.text = it.name
                tvGetEmail.text = it.email
                tvGetAge.text = it.age.toString()
                tvGetPhone.text = it.phone_number
            }
        }
    }
}