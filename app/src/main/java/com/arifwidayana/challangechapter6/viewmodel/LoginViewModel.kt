package com.arifwidayana.challangechapter6.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.challangechapter6.repositoy.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repo: UserRepository): ViewModel() {
    fun loginSession(user: String, pass: String, status: Boolean){
        viewModelScope.launch {
            repo.saveSession(user, pass, status)
        }
    }
}